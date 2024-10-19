package com.primetech.primetech_store.jwt;

import com.primetech.primetech_store.jwt.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Extendemos de OncePerRequestFilter para asegurar que el filtro se ejecute una vez por cada solicitud HTTP,
@Component
@RequiredArgsConstructor
public class JwtAuthentication extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String TOKEN = getTokenFromRequest(request);
        final String email;

        if (TOKEN == null) {
//            sendErrorResponse(response, "Por favor inicie sesión");
            filterChain.doFilter(request, response);
            return;
        }

        email = jwtService.getEmailFromToken(TOKEN);
        // Si no se puede extraer el email, retorna un mensaje
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            sendErrorResponse(response, "Token inválido o expirado");
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Si el token es válido, establece la autenticación
        if (jwtService.isTokenValid(TOKEN, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            sendErrorResponse(response, "Token inválido o expirado");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Crea el JSON de respuesta
        String jsonResponse = String.format("{\"mensajeLogin\": \"%s\"}", mensaje);
        response.getWriter().write(jsonResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

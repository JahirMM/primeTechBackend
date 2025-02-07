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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// Extendemos de OncePerRequestFilter para asegurar que el filtro se ejecute una vez por cada solicitud HTTP,
@Component
@RequiredArgsConstructor
public class JwtAuthentication extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final List<String> ALWAYS_PUBLIC_URLS = List.of(
            "/prime-tech/api/v1/auth/**",
            "/userImage/**",
            "/productImage/**",
            "/prime-tech/api/v1/filter-data/**",
            "/prime-tech/api/v1/seller-information/**"
    );

    private static final List<String> GET_ONLY_PUBLIC_URLS = List.of(
            "/prime-tech/api/v1/camera/**",
            "/prime-tech/api/v1/battery/**",
            "/prime-tech/api/v1/screen/**",
            "/prime-tech/api/v1/mobile-device/**",
            "/prime-tech/api/v1/laptop/**",
            "/prime-tech/api/v1/sim-card/**",
            "/prime-tech/api/v1/products/**",
            "/prime-tech/api/v1/product-image/**",
            "/prime-tech/api/v1/review/**",
            "/prime-tech/api/v1/offers/**",
            "/prime-tech/api/v1/categories/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean isAlwaysPublicUrl = ALWAYS_PUBLIC_URLS.stream().anyMatch(publicUrl -> pathMatcher.match(publicUrl, path));
        boolean isGetOnlyPublicUrl = "GET".equalsIgnoreCase(method) && GET_ONLY_PUBLIC_URLS.stream().anyMatch(publicUrl -> pathMatcher.match(publicUrl, path));

        if (isAlwaysPublicUrl || isGetOnlyPublicUrl) {
            filterChain.doFilter(request, response);
            return;
        }

        final String TOKEN = getTokenFromRequest(request);
        final String email;

        if (TOKEN == null) {
            sendErrorResponse(response, "Please log in");
            return;
        }

        email = jwtService.getEmailFromToken(TOKEN);
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            sendErrorResponse(response, "Invalid or expired token");
            return;
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(TOKEN, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                sendErrorResponse(response, "Invalid or expired token");
                return;
            }
        } catch (RuntimeException exception) {
            sendErrorResponse(response, "User not found");
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = String.format("{\"message\": \"%s\"}", mensaje);
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

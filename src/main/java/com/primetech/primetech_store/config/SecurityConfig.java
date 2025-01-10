package com.primetech.primetech_store.config;

import com.primetech.primetech_store.jwt.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

// Esta clase se utilizará para la configuración. Contendrá métodos anotados con @Bean,
// los cuales se emplearán para configurar los objetos necesarios.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthentication jwtAuthentication;
    private final AuthenticationProvider authProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private static final List<String> ALWAYS_PUBLIC_URLS = List.of(
            "/prime-tech/api/v1/auth/**",
            "/userImage/**",
            "/productImage/**"
    );

    // Rutas públicas solo para solicitudes GET
    private static final List<String> GET_ONLY_PUBLIC_URLS = List.of(
            "/prime-tech/api/v1/products/**",
            "/prime-tech/api/v1/camera/**",
            "/prime-tech/api/v1/battery/**",
            "/prime-tech/api/v1/screen/**",
            "/prime-tech/api/v1/mobile-device/**",
            "/prime-tech/api/v1/laptop/**",
            "/prime-tech/api/v1/sim-card/**",
            "/prime-tech/api/v1/product-image/**",
            "/prime-tech/api/v1/review/**",
            "/prime-tech/api/v1/offers/**"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> {
                    ALWAYS_PUBLIC_URLS.forEach(url -> authRequest.requestMatchers(url).permitAll());
                    GET_ONLY_PUBLIC_URLS.forEach(url -> authRequest.requestMatchers(HttpMethod.GET, url).permitAll());
                    authRequest.anyRequest().authenticated();
                })
                .exceptionHandling(exception -> exception.authenticationEntryPoint(customAuthenticationEntryPoint))
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

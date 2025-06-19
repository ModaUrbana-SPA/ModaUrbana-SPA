package com.example.cl.com.ModaUrbanaSPA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // Permitir acceso libre al endpoint de login
                        .requestMatchers("/doc/swagger-ui/**",
                         "/doc/swagger-ui/index.html",
                          "/doc/swagger-ui/swagger-ui.css",
                          "/doc/swagger-ui/swagger-ui-bundle.js",
                          "/doc/swagger-ui/swagger-ui-standalone-preset.js",
                          "/doc/swagger-ui/index.css",
                          "/doc/swagger-ui/swagger-initializer.js",
                          "/doc/swagger-ui/favicon-32x32.png",
                          "/v3/api-docs/swagger-config",
                          "/v3/api-docs").permitAll() // Permitir acceso libre al endpoint de swagger
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

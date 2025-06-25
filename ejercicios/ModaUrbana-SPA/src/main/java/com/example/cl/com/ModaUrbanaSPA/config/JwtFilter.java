package com.example.cl.com.ModaUrbanaSPA.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    public JwtFilter() {
        System.out.println(" JwtFilter inicializado");// Esto es para verificar que se inyecta correctamente ya que
                                                      // hubieron problemas al integrarlo
    }

    // Inyectamos el JwtUtil para validar y extraer información del token JWT
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("Ruta interceptada por JwtFilter: " + path);

        if (path.startsWith("/api/auth/login")) {
            System.out.println(" Login o auth libre, no se aplica filtro");
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaims(token);
                String nombreUsuario = claims.getSubject();
                String rol = claims.get("rol", String.class);

                var auth = new UsernamePasswordAuthenticationToken(
                        nombreUsuario, null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase())));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}

// Este filtro intercepta las solicitudes HTTP y verifica el token JWT en la
// cabecera Authorization.
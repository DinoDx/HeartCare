package c15.dev.utils;

import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author: Leopoldo Todisco, Carlo Venditto.
 * Creato il: 24/01/2023.
 * Questa classe indica un filtro per l'autenticazione via Token.
 * Si estende OncePerRequestFilter,
 * dunque a ogni Request si entra in questo filtro.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null
                && SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {
            var userDetails = (UtenteRegistrato) this.userDetailsService
                                                .loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        response.setHeader("Access-Control-Allow-Headers",
                "Authorization, "
                        + "OPTIONS, "
                        + "Origin, "
                        + "X-Requested-With,"
                        + "Content-Type,"
                        + "Access-Control-Request-Headers, "
                        + "Accept, "
                        + "Access-Control-Request-Method");

        filterChain.doFilter(request, response);
    }
}

package com.anilcan.kartaca.config;

import com.anilcan.kartaca.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        log.info("doFilterInternal Processing");

        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            final String authorization = request.getHeader("Authorization");//null geliyor!

            if (authorization != null && authorization.startsWith("Bearer ")) {
                log.info("debug if 2");

                final String token = authorization.substring(7);//Bearer..... token starts from 7th index
                final Claims claims = jwtService.getClaims(token);
                if (claims.getExpiration().after(new Date())) {
                    log.info("debug if 3");

                    final String username = claims.getSubject();
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                                  null,
                                                                                                                  userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}

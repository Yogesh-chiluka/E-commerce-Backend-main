package com.backend.Ecommerce.Backend.response.security.jwt;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import com.backend.Ecommerce.Backend.response.security.user.ShopUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ShopUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException{

                                        try {
                                            String jwt = parseJwt(request);
                                            if(StringUtils.hasText(jwt) && jwtUtils.validateToken(jwt)){
    
                                                String username = jwtUtils.getUsernameFromToken(jwt);
                                                
                                                logger.debug("JWT Token: {}", jwt);
                logger.debug("Username from token: {}", username);


                                                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                                                logger.debug("User Authorities: {}", userDetails.getAuthorities());
                                                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                                                SecurityContextHolder.getContext().setAuthentication(auth);
                                            }
                                        } catch (JwtException e) {
                                            logger.error("JWT validation error: {}", e.getMessage());
                                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                            response.getWriter().write(e.getMessage() +" : Invalid or expired token, you may login and try again! ");
                                            return;
                                        } catch (Exception e) {
                                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                            response.getWriter().write(e.getMessage());
                                            return;
                                        }

                                        filterChain.doFilter(request, response);
                                    }

    private String parseJwt(HttpServletRequest request){
        
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
             return headerAuth.substring(7);
        }

        return null;
    }
}

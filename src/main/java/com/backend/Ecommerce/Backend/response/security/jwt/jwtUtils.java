package com.backend.Ecommerce.Backend.response.security.jwt;

import java.util.*;

import io.jsonwebtoken;


 
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.GrantedAuthority;

import com.backend.Ecommerce.Backend.response.security.user.ShopUserDetails;

public class jwtUtils {

    private String jwtSecret;
    private int expirationTime;


    public String generateTokenForUser(Authentication authentication){

        ShopUserDetails userPrincipal = (ShopUserDetails) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .claim("id", userPrincipal.getId())
                .claim("roles", roles)
                .setIssued(new Date())
                .setExpiration(new Date((new Date().getTime() +expirationTime)))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    Public String getUsernameFromToken(String token) {

        return jwts.parserBuider()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token){

        try{
            jwts.parserBuider()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token);

            return true;

        }   catch (ExpriredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new JwtException(e.getMessage());
        }
    }

    
}
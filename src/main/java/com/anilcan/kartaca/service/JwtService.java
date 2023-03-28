package com.anilcan.kartaca.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String key;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateToken(String userName) {
        return Jwts.builder()
                   .setSubject(userName)
                   .setExpiration(new Date(System.currentTimeMillis() + expiration))
                   .signWith(getKey(), SignatureAlgorithm.HS512)
                   .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

}

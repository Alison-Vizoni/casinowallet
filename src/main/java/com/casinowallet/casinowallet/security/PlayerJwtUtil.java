package com.casinowallet.casinowallet.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

@Component
public class PlayerJwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    public String generateToken(Map<String, String> claims) {
        return Jwts.builder()
                .claims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret.getBytes())
                .compact();
    }
}

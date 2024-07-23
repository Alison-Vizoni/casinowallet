package com.casinowallet.casinowallet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

@Component
public class PlayerJwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static SecretKey key;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(this.secret.getBytes());
    }

    public static String generateToken(Map<String, String> claims) {
        return Jwts.builder()
                .claims(claims)
                .signWith(key)
                .compact();
    }

    public static Boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims getClaims(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            return null;
        }
    }
}

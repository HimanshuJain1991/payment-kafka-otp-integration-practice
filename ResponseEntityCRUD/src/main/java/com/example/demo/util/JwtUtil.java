package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;




@Component
public class JwtUtil {

    private final String secret = "mysecretkeymysecretkeymysecretkey123"; // min 32 chars

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    // Generate Token
    public String generateToken(String username,String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key) // ✅ FIXED
                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {
        return Jwts.parserBuilder() // ✅ FIXED
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Check Expiry
    public boolean isTokenExpired(String token) {
        Date expiry = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiry.before(new Date());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())   // same key used in generateToken
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // ✅ ADD THIS METHOD HERE
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
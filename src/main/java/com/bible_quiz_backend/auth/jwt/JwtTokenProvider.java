package com.bible_quiz_backend.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final long accessTokenExpirationSeconds;
    private final long refreshTokenExpirationSeconds;
    private final Key secretKey;

    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration) {
        this.accessTokenExpirationSeconds = accessTokenExpiration;
        this.refreshTokenExpirationSeconds = refreshTokenExpiration;
        this.secretKey = new SecretKeySpec(java.util.Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS256.getJcaName());
    }

    public String createAccessToken(Long memberId) {
        return createToken(memberId, accessTokenExpirationSeconds, "ACCESS");
    }

    public String createRefreshToken(Long memberId) {
        return createToken(memberId, refreshTokenExpirationSeconds, "REFRESH");
    }

    private String createToken(Long memberId, long expirationSeconds, String type) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("type", type)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationSeconds * 1000)) // 7200 초
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getMemberId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }
}

package com.bible_quiz_backend.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final String TOKEN_PREFIX = "Bearer ";

    private static final int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();

    // filter가 적용되지 않는 path
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();

        // 인증 관련 (토큰 발급 전)
        if (path.startsWith("/login")
                || path.startsWith("/oauth2")
                || path.equals("/api/auth/token")) {
            return true;
        }

        // CORS preflight
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 정적 리소스 (성능 최적화)
        if (path.equals("/favicon.ico")
                || path.equals("/error")
                || path.startsWith("/static/")
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/images/")) {
            return true;
        }

        return false;
    }

    // 토큰 검증 후 SecurityContextHolder에 인증정보 설정
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getHeader("Authorization");

        if (!StringUtils.hasText(token) || !token.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            String jwtToken = token.substring(TOKEN_PREFIX_LENGTH);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            User userDetails = new User(claims.getSubject(), "", Collections.emptyList());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.warn("Failed to parse JWT token", e);
            servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().write("invalid token");
        }
    }
}

package com.bible_quiz_backend.auth.oauth2.cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.net.URI;

@Profile("prod")
@Component
public class ProdCookieProvider implements CookieProvider {

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public ResponseCookie createRefreshTokenCookie(String refreshToken, String domain) {
        URI uri = URI.create(domain);
        String host = uri.getHost();
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true) // js로 읽기 불가
                .secure(true) // 운영환경은 https 사용
                .sameSite("None") // 크로스 사이트/서브도메인 간 요청에도 쿠키가 전송됨
                .path("/") // 해당 도메인 내 모든 경로에 쿠키를 전송
                .domain(host)
                .maxAge(refreshTokenExpiration)
                .build();
    }

    /**
     * refreshToken 존재 여부만 나타내는 표시용 쿠키 생성 (non-HttpOnly)
     * 프론트엔드에서 refreshToken 쿠키의 존재를 직접 확인할 수 없기 때문에 필요
     */
    @Override
    public ResponseCookie createRefreshPresenceCookie(String domain) {
        return ResponseCookie.from("rtp", "1")
                .httpOnly(false) // JS에서 읽기 가능
                .secure(true)
                .sameSite("None")
                .path("/")
                .domain(domain)
                .maxAge(refreshTokenExpiration)
                .build();
    }
}

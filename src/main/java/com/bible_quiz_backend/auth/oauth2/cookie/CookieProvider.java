package com.bible_quiz_backend.auth.oauth2.cookie;

import org.springframework.http.ResponseCookie;

public interface CookieProvider {
    ResponseCookie createRefreshTokenCookie(String refreshToken, String domain);

    ResponseCookie createRefreshPresenceCookie(String domain);
}

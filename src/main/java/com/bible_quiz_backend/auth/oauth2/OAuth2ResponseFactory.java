package com.bible_quiz_backend.auth.oauth2;

import com.bible_quiz_backend.auth.oauth2.response.GoogleOAuth2Response;
import com.bible_quiz_backend.auth.oauth2.response.KakaoOAuth2Response;
import com.bible_quiz_backend.auth.oauth2.response.NaverOAuth2Response;
import com.bible_quiz_backend.auth.oauth2.response.OAuth2Response;

import java.util.Map;

public class OAuth2ResponseFactory {
    public static OAuth2Response of(String provider, Map<String, Object> attributes) {
        return switch (provider.toLowerCase()) {
            case "google" -> new GoogleOAuth2Response(attributes);
            case "kakao" -> new KakaoOAuth2Response(attributes);
            case "naver" -> new NaverOAuth2Response(attributes);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }
}

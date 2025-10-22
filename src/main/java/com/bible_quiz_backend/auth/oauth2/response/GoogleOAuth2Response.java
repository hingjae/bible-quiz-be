package com.bible_quiz_backend.auth.oauth2.response;

import java.util.Map;

public class GoogleOAuth2Response implements OAuth2Response {
    private final Map<String, Object> attributes;

    public GoogleOAuth2Response(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getUsername() {
        return (String) attributes.get("email");
    }
}

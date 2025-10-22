package com.bible_quiz_backend.auth.oauth2.response;

public interface OAuth2Response {
    String getProvider();

    String getProviderId();

    String getUsername();
}

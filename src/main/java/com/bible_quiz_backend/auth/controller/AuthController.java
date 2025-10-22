package com.bible_quiz_backend.auth.controller;

import com.bible_quiz_backend.auth.dto.TokenResponse;
import com.bible_quiz_backend.auth.service.TokenService;
import com.bible_quiz_backend.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    /**
     * refreshToken 으로 accessToken 발급 메서드.
     * @param refreshToken
     * @return
     */
    @PostMapping("/token")
    public ApiResponse<TokenResponse> getAccessToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        String newAccessToken = tokenService.getNewAccessToken(refreshToken);

        return ApiResponse.ok(new TokenResponse(newAccessToken));
    }
}

package com.bible_quiz_backend.Member.domain;

import com.bible_quiz_backend.common.config.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String providerId;

    private String refreshToken;

    @Builder
    public Member(String username, AuthProvider authProvider, String providerId) {
        this.username = username;
        this.authProvider = authProvider;
        this.providerId = providerId;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void assertValidRefreshToken(String refreshToken) {
        if (!Objects.equals(this.refreshToken, refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}

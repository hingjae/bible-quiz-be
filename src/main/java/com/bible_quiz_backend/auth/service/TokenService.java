package com.bible_quiz_backend.auth.service;

import com.bible_quiz_backend.Member.domain.Member;
import com.bible_quiz_backend.Member.repository.MemberRepository;
import com.bible_quiz_backend.auth.jwt.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // refreshToken 검증 후 accessToken 반환
    @Transactional(readOnly = true)
    public String getNewAccessToken(String refreshToken) {
        if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long memberId = jwtTokenProvider.getMemberId(refreshToken);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(EntityNotFoundException::new);

        member.assertValidRefreshToken(refreshToken);

        return jwtTokenProvider.createAccessToken(memberId);
    }
}

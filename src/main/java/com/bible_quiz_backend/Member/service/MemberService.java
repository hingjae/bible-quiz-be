package com.bible_quiz_backend.Member.service;

import com.bible_quiz_backend.Member.domain.AuthProvider;
import com.bible_quiz_backend.Member.domain.Member;
import com.bible_quiz_backend.Member.repository.MemberRepository;
import com.bible_quiz_backend.auth.oauth2.response.OAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // provider + providerId로 member조회 후 없으면 새로 생성.
    @Transactional
    public Member findOrCreateMember(OAuth2Response oAuth2Response) {
        Optional<Member> memberOptional = memberRepository.findByAuthProviderAndProviderId(
                AuthProvider.valueOf(oAuth2Response.getProvider().toUpperCase()),
                oAuth2Response.getProviderId());

        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }

        Member member = Member.builder()
                .username(oAuth2Response.getUsername())
                .authProvider(AuthProvider.valueOf(oAuth2Response.getProvider().toUpperCase()))
                .providerId(oAuth2Response.getProviderId())
                .build();

        return memberRepository.save(member);
    }

    // 로그인 후 refreshToken 업데이트
    @Transactional
    public void updateRefreshToken(Long memberId, String refreshToken) {
        Member member = memberRepository.getReferenceById(memberId);

        member.updateRefreshToken(refreshToken);
    }
}

package com.bible_quiz_backend.Member.repository;

import com.bible_quiz_backend.Member.domain.AuthProvider;
import com.bible_quiz_backend.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAuthProviderAndProviderId(AuthProvider authProvider, String providerId);
}

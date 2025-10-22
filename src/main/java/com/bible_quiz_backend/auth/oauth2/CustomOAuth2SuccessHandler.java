package com.bible_quiz_backend.auth.oauth2;

import com.bible_quiz_backend.Member.domain.Member;
import com.bible_quiz_backend.Member.service.MemberService;
import com.bible_quiz_backend.auth.jwt.JwtTokenProvider;
import com.bible_quiz_backend.auth.oauth2.cookie.CookieProvider;
import com.bible_quiz_backend.auth.oauth2.response.OAuth2Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final CookieProvider cookieProvider;

    @Value("${jwt.cookie-secure}")
    private boolean cookieSecure;

    @Value("${app.frontend.domain}")
    private String frontEndUrl;

    /**
     * 소셜 로그인 인증 후 리프레시 토큰 DB에 업데이트
     * 리프레시 토큰만 쿠키에 넣어서 /login/success 경로로 redirect
     * /login/success 경로로 redirect
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("authentication : {}", authentication);

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oauthToken.getPrincipal();
        String provider = oauthToken.getAuthorizedClientRegistrationId();

        OAuth2Response oAuth2Response = OAuth2ResponseFactory.of(provider, oAuth2User.getAttributes());

        Member member = memberService.findOrCreateMember(oAuth2Response);

        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        memberService.updateRefreshToken(member.getId(), refreshToken);

        ResponseCookie cookie = cookieProvider.createRefreshTokenCookie(refreshToken, frontEndUrl);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        ResponseCookie presenceCookie = cookieProvider.createRefreshPresenceCookie(frontEndUrl); // js로 refreshToken 존재 여부 확인용 쿠키
        response.addHeader(HttpHeaders.SET_COOKIE, presenceCookie.toString());

        getRedirectStrategy().sendRedirect(request, response, frontEndUrl + "/login/success");
    }
}

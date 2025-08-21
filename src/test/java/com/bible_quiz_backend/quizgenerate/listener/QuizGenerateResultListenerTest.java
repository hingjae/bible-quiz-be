package com.bible_quiz_backend.quizgenerate.listener;

import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessage;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateResponse;
import com.bible_quiz_backend.quizgenerate.service.QuizGenerateService;
import com.bible_quiz_backend.topic.service.DailyTopicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuizGenerateResultListenerTest {

    @Mock
    private QuizGenerateService quizGenerateService;

    @Mock
    private DailyTopicService dailyTopicService;

    @InjectMocks
    private QuizGenerateResultListener quizGenerateResultListener;

    @Captor
    private ArgumentCaptor<QuizGenerateMessage> captor;

    @Test
    @DisplayName("SQS 의 퀴즈 메시지를 수신한 후 객체로 변환한다.")
    void receiveMessage() {
        String message = """
                {"request_id": "5e2358ad-8425-4c59-b647-077896778a81",
                "topic_id": 1,
                "quizzes": [{"question": "야곱이 라헬을 아내로 얻기 위해 라반을 몇 년 동안 섬겼습니까?", "options": ["3년", "5년", "7년", "10년"], "correct_answer": "7년", "correct_answer_reason": "야곱은 라헬을 더 사랑하여 그녀를 얻기 위해 라반을 7년 동안 섬겼습니다.", "reference": "창29:18-20"},
                {"question": "야곱이 처음으로 결혼한 사람은 누구입니까?", "options": ["라헬", "레아", "빌하", "실바"], "correct_answer": "레아", "correct_answer_reason": "라반이 야곱에게 레아를 먼저 주었기 때문에 야곱은 처음으로 레아와 결혼했습니다.", "reference": "창29:23-25"},
                {"question": "유다가 아버지 이스라엘에게 베냐민을 데리고 가야 한다고 주장한 이유는 무엇입니까?", "options": ["곡식을 더 많이 얻기 위해", "애굽 사람의 경고 때문", "베냐민이 보고 싶어서", "이스라엘의 명령 때문"], "correct_answer": "애굽 사람의 경고 때문", "correct_answer_reason": "애굽의 관리가 베냐민이 함께 오지 않으면 그들의 얼굴을 보지 못할 것이라고 경고했기 때문입니다.", "reference": "창43:3-5"}]}
                """;

        quizGenerateResultListener.receiveMessage(message);

        verify(quizGenerateService).saveAllFromMessage(captor.capture());
        verify(dailyTopicService).save(1L);

        QuizGenerateMessage actual = captor.getValue();
        assertThat(actual.quizzes()).hasSize(3);
        assertThat(actual.quizzes()).extracting(QuizGenerateResponse::question)
                .containsExactly(
                        "야곱이 라헬을 아내로 얻기 위해 라반을 몇 년 동안 섬겼습니까?",
                        "야곱이 처음으로 결혼한 사람은 누구입니까?",
                        "유다가 아버지 이스라엘에게 베냐민을 데리고 가야 한다고 주장한 이유는 무엇입니까?"
                );
    }

    // 테스트에서 중요한 건 "객체가 호출되었는지", 그리고 **"값이 잘 매핑되었는지"**이지, 꼭 같은 객체 참조일 필요는 없다.
}
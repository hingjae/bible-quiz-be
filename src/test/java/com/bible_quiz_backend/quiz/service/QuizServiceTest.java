package com.bible_quiz_backend.quiz.service;

import com.bible_quiz_backend.quiz.domain.Quiz;
import com.bible_quiz_backend.quiz.repository.QuizRepository;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessage;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateResponse;
import com.bible_quiz_backend.quizgenerate.mapper.QuizGenerateMapper;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;
    @Mock
    private QuizGenerateMapper quizGenerateMapper;
    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private QuizService quizService;

    @Captor
    private ArgumentCaptor<List<Quiz>> captor;

    @Test
    @DisplayName("SQS로 수신한 메시지를 List<Quiz>로 변환해서 저장한다.")
    void saveAll_FromMessage_success() {
        QuizGenerateMessage message = new QuizGenerateMessage("request-id-1234", 1L, getQuizzes());

        List<Quiz> mapped = List.of(
                Quiz.builder().question("야곱이 꿈에서 본 양 떼를 탄 숫양은 어떤 모습이었습니까?").build(),
                Quiz.builder().question("하나님이 야곱에게 돌아가라고 명하신 곳은 어디입니까?").build(),
                Quiz.builder().question("라헬이 라반의 집에서 도둑질한 것은 무엇입니까?").build()
        );
        Topic topic = Topic.builder().build();
        when(quizGenerateMapper.toQuizzes(topic, message)).thenReturn(mapped);
        when(topicRepository.getReferenceById(1L)).thenReturn(topic);

        quizService.saveAllFromMessage(message);

        verify(quizRepository).saveAll(captor.capture());

        assertThat(captor.getValue()).hasSize(3)
                .extracting(Quiz::getQuestion)
                .containsExactly(
                        "야곱이 꿈에서 본 양 떼를 탄 숫양은 어떤 모습이었습니까?",
                        "하나님이 야곱에게 돌아가라고 명하신 곳은 어디입니까?",
                        "라헬이 라반의 집에서 도둑질한 것은 무엇입니까?"
                );
    }

    private List<QuizGenerateResponse> getQuizzes() {
        return List.of(
                new QuizGenerateResponse("야곱이 꿈에서 본 양 떼를 탄 숫양은 어떤 모습이었습니까?", List.of("얼룩무늬 있는 것", "흰색 양", "검은색 양", "회색 양"), "얼룩무늬 있는 것", "창세기 31:10에 따르면 야곱은 꿈에서 양 떼를 탄 숫양이 얼룩무늬 있는 것과 점 있는 것과 아롱진 것이었다고 보았습니다.", "창31:10"),
                new QuizGenerateResponse("하나님이 야곱에게 돌아가라고 명하신 곳은 어디입니까?", List.of("벧엘", "세겜", "가나안", "애굽"), "벧엘", "하나님은 야곱에게 벧엘로 돌아가서 거기서 제단을 쌓으라고 명하셨습니다.", "창35:1"),
                new QuizGenerateResponse("라헬이 라반의 집에서 도둑질한 것은 무엇입니까?", List.of("드라빔", "금", "은", "보석"), "드라빔", "창세기 31:19에 따르면 라헬은 그의 아버지의 드라빔을 도둑질했습니다.", "창31:19")
        );
    }
}
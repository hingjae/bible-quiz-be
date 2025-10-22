package com.bible_quiz_backend.quiz.service;

import com.bible_quiz_backend.quiz.controller.dto.QuizResponseList;
import com.bible_quiz_backend.quiz.controller.dto.QuizSearch;
import com.bible_quiz_backend.quiz.domain.Quiz;
import com.bible_quiz_backend.quiz.mapper.QuizMapper;
import com.bible_quiz_backend.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizResponseList findByParam(QuizSearch quizSearch) {
        PageRequest pageRequest = PageRequest.of(0, quizSearch.size(), Sort.by(Sort.Direction.DESC, "id"));
        List<Quiz> quizzes = quizRepository.findAllByTopic_Id(quizSearch.topicId(), pageRequest);
        return quizMapper.toQuizResponseList(quizzes);
    }
}

package com.bible_quiz_backend.quiz.repository;

import com.bible_quiz_backend.quiz.domain.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByTopic_Id(Long topicId, Pageable pageable);
}

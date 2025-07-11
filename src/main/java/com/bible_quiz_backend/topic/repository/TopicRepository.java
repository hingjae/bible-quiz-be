package com.bible_quiz_backend.topic.repository;

import com.bible_quiz_backend.topic.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);
}

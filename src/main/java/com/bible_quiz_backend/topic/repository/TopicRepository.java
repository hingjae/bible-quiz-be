package com.bible_quiz_backend.topic.repository;

import com.bible_quiz_backend.topic.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByQuestion(String question);

    @Query(value = """
        select *
        from topic t
        where t.testament =:testament
        order by rand()
        limit :limit
    """, nativeQuery = true)
    List<Topic> findRandomTopicsByTestament(@Param("testament") String testament, @Param("limit") int limit);
}

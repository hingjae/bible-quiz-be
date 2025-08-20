package com.bible_quiz_backend.topic.repository;

import com.bible_quiz_backend.topic.domain.DailyTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DailyTopicRepository extends JpaRepository<DailyTopic, Long> {
    @Query("""
        select dt
        from DailyTopic dt
        join fetch dt.topic
        where dt.date = :date
    """)
    List<DailyTopic> findByDate(@Param("date") LocalDate date);
}

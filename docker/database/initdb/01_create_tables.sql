use quizdb;

CREATE TABLE topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(255),
    question VARCHAR(255),
    book_file_name VARCHAR(255),
    created_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE quiz (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic_id BIGINT,
    question VARCHAR(1000) NOT NULL,
    options JSON NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    correct_answer_reason VARCHAR(1000) NOT NULL,
    reference VARCHAR(50),
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_quiz_topic FOREIGN KEY (topic_id) REFERENCES topic(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
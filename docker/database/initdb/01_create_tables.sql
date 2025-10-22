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

CREATE TABLE daily_topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic_id BIGINT NOT NULL,
    date DATE NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_daily_topic_topic FOREIGN KEY (topic_id) REFERENCES topic (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    auth_provider VARCHAR(50) NOT NULL,
    provider_id VARCHAR(255) NOT NULL,
    refresh_token VARCHAR(512),
    created_at DATETIME NOT NULL,
    UNIQUE KEY uq_provider (auth_provider, provider_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

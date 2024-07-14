CREATE TABLE user_profiles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    profile_id BIGINT       NOT NULL,
    CONSTRAINT fk_users_profile_id FOREIGN KEY (profile_id) REFERENCES user_profiles (id)
);

CREATE TABLE topics
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(100) NOT NULL,
    message       VARCHAR(100) NOT NULL,
    status        BOOLEAN      NOT NULL,
    creation_date DATETIME     NOT NULL,
    update_date   DATETIME     NOT NULL,
    author_id     BIGINT       NOT NULL,
    CONSTRAINT fk_topics_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE answers
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    message       VARCHAR(100) NOT NULL,
    topic_id      BIGINT       NOT NULL,
    creation_date DATETIME     NOT NULL,
    update_date   DATETIME     NOT NULL,
    author_id     BIGINT       NOT NULL,
    solution      BOOLEAN      NOT NULL,
    CONSTRAINT fk_answers_topic_id FOREIGN KEY (topic_id) REFERENCES topics (id),
    CONSTRAINT fk_answers_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);

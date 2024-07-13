CREATE TABLE user_profiles
(
    id   BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    profile_id BIGINT       NOT NULL,
    CONSTRAINT fk_users_profile_id FOREIGN KEY (profile_id) REFERENCES user_profiles (id)
);

CREATE TABLE topics
(
    id            BIGINT               NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(100)         NOT NULL,
    message       VARCHAR(100)         NOT NULL,
    status        BOOLEAN DEFAULT TRUE NOT NULL,
    creation_date DATETIME             NOT NULL,
    update_date   DATETIME             NOT NULL,
    author_id     BIGINT               NOT NULL,
    CONSTRAINT fk_topics_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE answers
(
    id            BIGINT                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    message       VARCHAR(100)          NOT NULL,
    topic_id      BIGINT                NOT NULL,
    creation_date DATETIME              NOT NULL,
    author_id     BIGINT                NOT NULL,
    solution      BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT fk_answers_topic_id FOREIGN KEY (topic_id) REFERENCES topics (id),
    CONSTRAINT fk_answers_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);

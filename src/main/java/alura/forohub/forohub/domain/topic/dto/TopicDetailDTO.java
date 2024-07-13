package alura.forohub.forohub.domain.topic.dto;

import alura.forohub.forohub.domain.topic.Topic;

import java.time.LocalDateTime;

public record TopicDetailDTO(
        Long id,
        String title,
        String message,
        String authorName,
        Boolean status,
        LocalDateTime creationDate,
        LocalDateTime updateDate
) {
    public TopicDetailDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getAuthor().getName(),
                topic.isStatus(),
                topic.getCreationDate(),
                topic.getUpdateDate());
    }
}

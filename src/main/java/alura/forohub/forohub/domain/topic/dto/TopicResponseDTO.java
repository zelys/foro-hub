package alura.forohub.forohub.domain.topic.dto;

import alura.forohub.forohub.domain.topic.Topic;

public record TopicResponseDTO(
        String title,
        String message,
        Long author
) {
    public TopicResponseDTO(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getId());
    }
}


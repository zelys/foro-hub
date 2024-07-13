package alura.forohub.forohub.domain.topic.dto;

import alura.forohub.forohub.domain.topic.Topic;

public record TopicListDTO(
        Long id,
        String title,
        String message,
        String authorName
) {
    public TopicListDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getAuthor().getName());
    }
}


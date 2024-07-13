package alura.forohub.forohub.domain.topic.dto;

import alura.forohub.forohub.domain.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull User author
) {}


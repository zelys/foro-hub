package alura.forohub.forohub.domain.topic;

import alura.forohub.forohub.domain.topic.dto.TopicRequestDTO;
import alura.forohub.forohub.domain.topic.dto.TopicUpdateDTO;
import alura.forohub.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private boolean status = true;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Topic(TopicRequestDTO topicRequest) {
        this.title = topicRequest.title();
        this.message = topicRequest.message();
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.author = topicRequest.author();
    }

    // FECHA DE ACTUALIZACIÓN
    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    // DESACTIVAR TÓPICO
    public void deactivateTopic(Topic topic) {
        this.status = false;
    }

    // ACTUALIZAR TÓPICO
    public void updateFromDTO(TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() != null && !topicUpdateDTO.title().isEmpty()) {
            this.title = topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null && !topicUpdateDTO.message().isEmpty()) {
            this.message = topicUpdateDTO.message();
        }
    }
}


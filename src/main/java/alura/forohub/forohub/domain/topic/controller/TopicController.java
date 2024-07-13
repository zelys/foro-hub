package alura.forohub.forohub.domain.topic.controller;

import alura.forohub.forohub.domain.topic.Topic;
import alura.forohub.forohub.domain.topic.dto.*;
import alura.forohub.forohub.domain.topic.repository.TopicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    // REGISTRAR UN NUEVO TÓPICO
    @PostMapping
    @ResponseBody
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicRequestDTO topicRequest, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicRequest));
        TopicResponseDTO response = new TopicResponseDTO(topic);

        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    // MOSTRAR TODOS LOS TÓPICOS POR PAGINA
    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> listarMedicos(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findByStatusTrue(pageable).map(TopicListDTO::new));
    }

    // MOSTRAR DETALLE TÓPICO
    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(TopicDetailDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ACTUALIZAR DATOS DE REGISTRO SEGÚN ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicUpdateDTO topicUpdateDTO) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.updateFromDTO(topicUpdateDTO);

            topicRepository.save(topic);
            return ResponseEntity.ok("El tópico ha sido actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ELIMINAR TÓPICO
    @DeleteMapping("/delete_topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // EXCLUSION LÓGICA
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic(topic);
        return ResponseEntity.noContent().build();
    }
}


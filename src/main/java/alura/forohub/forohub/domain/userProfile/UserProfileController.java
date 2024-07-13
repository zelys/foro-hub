package alura.forohub.forohub.domain.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    // REGISTRAR UN NUEVO PERFIL DE USUARIO
    @PostMapping
    @ResponseBody
    public ResponseEntity createUserProfile(@Valid @RequestBody UserProfile userProfile) {
        Optional<UserProfile> existingProfile = userProfileRepository.findByName(userProfile.getName().toUpperCase());
        // EN CASO QUE YA EXISTA UN REGISTRO IGUAL
        if (existingProfile.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) //409 CONFLICT
                    .body("A user profile with the name '" + userProfile.getName() + "' already exists.");
        }
        // GUARDAR REGISTRO
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return ResponseEntity.ok(savedUserProfile);
    }
}


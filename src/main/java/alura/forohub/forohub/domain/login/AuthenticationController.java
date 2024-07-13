package alura.forohub.forohub.domain.login;

import alura.forohub.forohub.domain.users.User;
import alura.forohub.forohub.infra.service.JWTtokenDTO;
import alura.forohub.forohub.infra.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    AuthenticationManager authentication;

    TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authentication, TokenService tokenService) {
        this.authentication = authentication;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> authenticationUser(@RequestBody @Valid AuthenticationUserDTO authDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authDTO.name(), authDTO.password());
        var authenticatedUser = authentication.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(JWTtoken));
    }
}


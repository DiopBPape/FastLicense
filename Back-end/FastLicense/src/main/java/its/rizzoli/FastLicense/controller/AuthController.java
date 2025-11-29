package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.UserDTO;
import its.rizzoli.FastLicense.models.User;
import its.rizzoli.FastLicense.utility.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        boolean success = authService.login(userDTO.getUsername(), userDTO.getPassword());
        if (success) {
            return ResponseEntity.ok(Map.of("risultato", "Login effettuato con successo!"));
        } else {
            return ResponseEntity.status(401).body(Map.of("risultato", "Credenziali non valide"));
        }
    }
}


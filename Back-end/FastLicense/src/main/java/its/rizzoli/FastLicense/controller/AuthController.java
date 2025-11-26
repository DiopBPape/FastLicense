package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.utility.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean success = authService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Login effettuato con successo!");
        } else {
            return ResponseEntity.status(401).body("Credenziali non valide");
        }
    }
}


package its.rizzoli.FastLicense.utility;

import its.rizzoli.FastLicense.models.User;
import its.rizzoli.FastLicense.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Controllo login
    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return BCrypt.checkpw(password, user.getPassword());
        }
        return false;
    }
}

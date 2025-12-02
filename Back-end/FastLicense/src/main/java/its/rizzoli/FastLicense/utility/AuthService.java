package its.rizzoli.FastLicense.utility;

import its.rizzoli.FastLicense.models.User;
import its.rizzoli.FastLicense.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private User loggedUser;  // tiene traccia dell'utente loggato

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Controllo login
    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println(BCrypt.hashpw("password", BCrypt.gensalt()));
            if (BCrypt.checkpw(password, user.getPassword())) {

                this.loggedUser = user;  // memorizza l'utente loggato
                return true;
            }
        }

        return false;
    }

    // Restituisce l'utente loggato
    public User getLoggedUser() {
        return loggedUser;
    }

    // Logout
    public void logout() {
        this.loggedUser = null;
    }
}

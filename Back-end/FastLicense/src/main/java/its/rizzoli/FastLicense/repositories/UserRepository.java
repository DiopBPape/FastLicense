package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

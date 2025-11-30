package its.rizzoli.FastLicense.repositories;


import its.rizzoli.FastLicense.models.QuizEseguito;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizEseguitoRepository extends CrudRepository<QuizEseguito, Integer> {
    Optional<QuizEseguito> findByUserUsername(String username);

}

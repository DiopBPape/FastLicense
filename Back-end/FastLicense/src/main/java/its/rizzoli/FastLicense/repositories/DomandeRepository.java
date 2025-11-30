package its.rizzoli.FastLicense.repositories;


import its.rizzoli.FastLicense.models.Domande;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DomandeRepository extends CrudRepository<Domande, Integer> {
    Optional<Domande> findByTesto(String testo);

}

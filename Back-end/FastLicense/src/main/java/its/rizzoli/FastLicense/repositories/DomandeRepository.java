package its.rizzoli.FastLicense.repositories;


import its.rizzoli.FastLicense.models.Domande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DomandeRepository extends CrudRepository<Domande, Integer> {
    Optional<Domande> findByTesto(String testo);

    @Query(value = "SELECT * FROM domande ORDER BY RAND() LIMIT 30", nativeQuery = true)
    List<Domande> findRandom30();

}

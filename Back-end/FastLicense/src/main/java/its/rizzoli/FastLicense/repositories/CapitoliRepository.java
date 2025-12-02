package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.Capitoli;
import its.rizzoli.FastLicense.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CapitoliRepository extends CrudRepository<Capitoli, Integer> {
    Optional<Capitoli> findByTitolo(String titolo);


}

package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.Argomenti;
import its.rizzoli.FastLicense.models.Capitoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArgomentiRepository extends CrudRepository<Argomenti, Integer> {
    List<Argomenti> findByCapitolo(Capitoli capitolo);
    Optional<Argomenti> findByTitolo(String titolo);

}


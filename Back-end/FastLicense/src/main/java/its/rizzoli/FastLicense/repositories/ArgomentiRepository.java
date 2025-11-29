package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.Argomenti;
import its.rizzoli.FastLicense.models.Capitoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArgomentiRepository extends CrudRepository<Argomenti, Integer> {
    List<Argomenti> findByCapitolo(Capitoli capitolo);
}


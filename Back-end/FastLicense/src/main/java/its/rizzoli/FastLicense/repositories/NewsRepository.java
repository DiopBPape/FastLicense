package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


import java.util.Optional;

public interface NewsRepository extends CrudRepository <News, Integer> {
    List<News> findAllByOrderByDataOraDesc();
    Optional<News> findByTitolo(String titolo);


}

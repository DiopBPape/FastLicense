package its.rizzoli.FastLicense.repositories;

import its.rizzoli.FastLicense.models.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository <News, Integer> {
    List<News> findAllByOrderByDataOraDesc();
}

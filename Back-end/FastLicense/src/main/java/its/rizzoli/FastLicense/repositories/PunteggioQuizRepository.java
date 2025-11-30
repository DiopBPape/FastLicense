package its.rizzoli.FastLicense.repositories;


import its.rizzoli.FastLicense.models.Domande;
import its.rizzoli.FastLicense.models.PunteggioQuiz;
import its.rizzoli.FastLicense.models.QuizEseguito;
import org.springframework.data.repository.CrudRepository;

public interface PunteggioQuizRepository extends CrudRepository<PunteggioQuiz, Integer> {
    boolean existsByQuizAndDomanda(QuizEseguito quiz, Domande domanda);

}

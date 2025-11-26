package its.rizzoli.FastLicense.models;

import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

@Entity
public class PunteggioQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private QuizEseguito quiz;

    @ManyToOne
    @JoinColumn(name = "domandaId")
    private Domande domanda;

    private Boolean risposta;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuizEseguito getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEseguito quiz) {
        this.quiz = quiz;
    }

    public Domande getDomanda() {
        return domanda;
    }

    public void setDomanda(Domande domanda) {
        this.domanda = domanda;
    }

    public Boolean getRisposta() {
        return risposta;
    }

    public void setRisposta(Boolean risposta) {
        this.risposta = risposta;
    }
}



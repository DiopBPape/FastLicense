package its.rizzoli.FastLicense.models;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class QuizEseguito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int punteggio;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<PunteggioQuiz> risposte;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PunteggioQuiz> getRisposte() {
        return risposte;
    }

    public void setRisposte(List<PunteggioQuiz> risposte) {
        this.risposte = risposte;
    }
}





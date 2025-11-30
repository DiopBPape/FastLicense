package its.rizzoli.FastLicense.DTO;

import java.util.List;

public class QuizEseguitoDto {
    private Integer id;
    private int punteggio;
    private Integer userId;
    private List<PunteggioQuizDto> risposte;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PunteggioQuizDto> getRisposte() {
        return risposte;
    }

    public void setRisposte(List<PunteggioQuizDto> risposte) {
        this.risposte = risposte;
    }
}


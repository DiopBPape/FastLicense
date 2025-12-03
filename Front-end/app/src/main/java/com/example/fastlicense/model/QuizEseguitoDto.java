package com.example.fastlicense.model;

import java.util.List;

public class QuizEseguitoDto {
    private List<PunteggioQuizDto> risposte;
    public List<PunteggioQuizDto> getRisposte() { return risposte; }
    public void setRisposte(List<PunteggioQuizDto> risposte) { this.risposte = risposte; }
}

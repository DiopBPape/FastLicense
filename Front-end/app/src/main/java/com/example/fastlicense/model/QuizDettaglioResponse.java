package com.example.fastlicense.model;

import java.util.List;

public class QuizDettaglioResponse {
    private Integer quizId;
    private Integer punteggioMemorizzato;
    private Integer punteggioCalcolato; // Questo è il punteggio corretto
    private List<DomandaDettaglio> risposte;

    // GETTERS & SETTERS (Aggiungi tutti i Getter e Setter necessari)

    public Integer getQuizId() { return quizId; }
    public void setQuizId(Integer quizId) { this.quizId = quizId; }

    public Integer getPunteggioMemorizzato() { return punteggioMemorizzato; }
    public void setPunteggioMemorizzato(Integer punteggioMemorizzato) { this.punteggioMemorizzato = punteggioMemorizzato; }

    public Integer getPunteggioCalcolato() { return punteggioCalcolato; }
    public void setPunteggioCalcolato(Integer punteggioCalcolato) { this.punteggioCalcolato = punteggioCalcolato; }

    public List<DomandaDettaglio> getRisposte() { return risposte; }
    public void setRisposte(List<DomandaDettaglio> risposte) { this.risposte = risposte; }
}
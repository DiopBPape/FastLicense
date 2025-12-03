package com.example.fastlicense.model;

public class DomandaDettaglio {
    private Integer domandaId;
    private String testoDomanda;
    private String immagine;
    private Boolean rispostaDataUtente; // Risposta data dall'utente (true/false)
    private Boolean rispostaCorretta;   // Risposta corretta (true/false)
    private Boolean isGiusta;           // Se l'utente ha risposto correttamente

    // GETTERS & SETTERS (Aggiungi tutti i Getter e Setter necessari)

    public Integer getDomandaId() { return domandaId; }
    public void setDomandaId(Integer domandaId) { this.domandaId = domandaId; }
    // ... gli altri getter e setter
    public String getTestoDomanda() { return testoDomanda; }
    public void setTestoDomanda(String testoDomanda) { this.testoDomanda = testoDomanda; }

    public String getImmagine() { return immagine; }
    public void setImmagine(String immagine) { this.immagine = immagine; }

    public Boolean getRispostaDataUtente() { return rispostaDataUtente; }
    public void setRispostaDataUtente(Boolean rispostaDataUtente) { this.rispostaDataUtente = rispostaDataUtente; }

    public Boolean getRispostaCorretta() { return rispostaCorretta; }
    public void setRispostaCorretta(Boolean rispostaCorretta) { this.rispostaCorretta = rispostaCorretta; }

    public Boolean getIsGiusta() { return isGiusta; }
    public void setIsGiusta(Boolean isGiusta) { this.isGiusta = isGiusta; }
}
package its.rizzoli.FastLicense.DTO;

public class PunteggioQuizDto {
    private Integer id;
    private Integer domandaId;
    private Boolean risposta;

    // GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDomandaId() {
        return domandaId;
    }

    public void setDomandaId(Integer domandaId) {
        this.domandaId = domandaId;
    }

    public Boolean getRisposta() {
        return risposta;
    }

    public void setRisposta(Boolean risposta) {
        this.risposta = risposta;
    }
}

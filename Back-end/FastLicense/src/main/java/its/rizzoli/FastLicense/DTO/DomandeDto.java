package its.rizzoli.FastLicense.DTO;

public class DomandeDto {
    private Integer id;
    private String testo;
    private Boolean risposta;   // se vuoi NON inviarla -> toglila
    private String immagine;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Boolean getRisposta() {
        return risposta;
    }

    public void setRisposta(Boolean risposta) {
        this.risposta = risposta;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}


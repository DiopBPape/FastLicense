package its.rizzoli.FastLicense.models;

import jakarta.persistence.*;

@Entity
public class Domande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String testo;

    private Boolean risposta;

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

package its.rizzoli.FastLicense.models;

import jakarta.persistence.*;

@Entity
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "argomentoId")
    private Argomenti argomento;

    @ManyToOne
    @JoinColumn(name = "capitoloId")
    private Capitoli capitolo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Argomenti getArgomento() {
        return argomento;
    }

    public void setArgomento(Argomenti argomento) {
        this.argomento = argomento;
    }

    public Capitoli getCapitolo() {
        return capitolo;
    }

    public void setCapitolo(Capitoli capitolo) {
        this.capitolo = capitolo;
    }
}

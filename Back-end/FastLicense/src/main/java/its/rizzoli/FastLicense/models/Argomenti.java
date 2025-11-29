package its.rizzoli.FastLicense.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Argomenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titolo;

    @Column(columnDefinition = "TEXT")
    private String testo;

    @ManyToOne
    @JoinColumn(name = "capitoloId")
    private Capitoli capitolo;

    @OneToMany(mappedBy = "argomento", cascade = CascadeType.ALL)
    private List<Immagine> immagini = new ArrayList<>();



    // GETTERS & SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Capitoli getCapitolo() {
        return capitolo;
    }

    public void setCapitolo(Capitoli capitolo) {
        this.capitolo = capitolo;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
}


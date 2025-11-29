package its.rizzoli.FastLicense.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Capitoli {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titolo;

    @OneToMany(mappedBy = "capitolo", cascade = CascadeType.ALL)
    private List<Immagine> immagini = new ArrayList<>();


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

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
}


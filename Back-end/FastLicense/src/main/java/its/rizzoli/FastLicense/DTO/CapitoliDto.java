package its.rizzoli.FastLicense.DTO;

import java.util.List;

public class CapitoliDto {

    private Integer id;
    private String titolo;
    private List<String> immagini; // solo URL delle immagini

    public CapitoliDto() {}

    public CapitoliDto(Integer id, String titolo, List<String> immagini) {
        this.id = id;
        this.titolo = titolo;
        this.immagini = immagini;
    }

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

    public List<String> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<String> immagini) {
        this.immagini = immagini;
    }
}

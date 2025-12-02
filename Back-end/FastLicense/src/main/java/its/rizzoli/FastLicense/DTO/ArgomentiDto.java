package its.rizzoli.FastLicense.DTO;

import java.util.List;

public class ArgomentiDto {

    private Integer id;
    private String titolo;
    private String testo;

    // Info utili sul capitolo, senza portare dietro l'entità completa
    private Integer capitoloId;
    private String capitoloTitolo;

    // Solo URL delle immagini
    private List<ImmagineDTO> immagini;

    public ArgomentiDto() {}

    public ArgomentiDto(Integer id, String titolo, String testo,
                        Integer capitoloId, String capitoloTitolo,
                        List<ImmagineDTO> immagini) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.capitoloId = capitoloId;
        this.capitoloTitolo = capitoloTitolo;
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

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getCapitoloId() {
        return capitoloId;
    }

    public void setCapitoloId(Integer capitoloId) {
        this.capitoloId = capitoloId;
    }

    public String getCapitoloTitolo() {
        return capitoloTitolo;
    }

    public void setCapitoloTitolo(String capitoloTitolo) {
        this.capitoloTitolo = capitoloTitolo;
    }

    public List<ImmagineDTO> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<ImmagineDTO> immagini) {
        this.immagini = immagini;
    }
}


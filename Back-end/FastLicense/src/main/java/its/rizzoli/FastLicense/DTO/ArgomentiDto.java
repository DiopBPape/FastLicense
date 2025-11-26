package its.rizzoli.FastLicense.DTO;

public class ArgomentiDto {
    private Integer id;
    private String titolo;
    private String testo;
    private Integer capitoloId;

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
}


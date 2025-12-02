package its.rizzoli.FastLicense.DTO;

import java.util.List;

public class CapitoliDto {

    private Integer id;
    private String titolo;
    private String immagine;


    public CapitoliDto() {}

    public CapitoliDto(Integer id, String titolo, String immagine) {
        this.id = id;
        this.titolo = titolo;
        this.immagine = immagine;
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


}

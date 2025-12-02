package com.example.fastlicense.model;

import java.util.List;

public class CapitoliDTO {

    private Integer id;
    private String titolo;
    private String immagine;


    public Integer getId() { return id; }
    public String getTitolo() { return titolo; }

    public void setId(Integer id) { this.id = id; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}

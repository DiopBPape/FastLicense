package com.example.fastlicense.model;

import java.util.List;

public class CapitoliDTO {

    private Integer id;
    private String titolo;
    private List<ImmagineDTO> immagini;

    public Integer getId() { return id; }
    public String getTitolo() { return titolo; }
    public List<ImmagineDTO> getImmagini() { return immagini; }

    public void setId(Integer id) { this.id = id; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setImmagini(List<ImmagineDTO> immagini) { this.immagini = immagini; }
}

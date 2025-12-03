package com.example.fastlicense.model;

import java.util.List;

public class ArgomentiDTO {

    private Integer id;
    private String titolo;
    private String testo;

    private Integer capitoloId;
    private String capitoloTitolo;

    private List<String> immagini; // solo stringhe

    public Integer getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getTesto() { return testo; }
    public Integer getCapitoloId() { return capitoloId; }
    public String getCapitoloTitolo() { return capitoloTitolo; }
    public List<String> getImmagini() { return immagini; }
}


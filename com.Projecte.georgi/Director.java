package com.projecte.georgi;

import java.io.Serializable;

public class Director implements Serializable {

    private int    id;
    private String nom;
    private String cognoms;
    private String nacionalitat;
    private int    anyNaixement;

    public Director(int id, String nom, String cognoms, String nacionalitat, int anyNaixement) {
        this.id           = id;
        this.nom          = nom;
        this.cognoms      = cognoms;
        this.nacionalitat = nacionalitat;
        this.anyNaixement = anyNaixement;
    }

    public int    getId()           { return id; }
    public String getNom()          { return nom; }
    public String getCognoms()      { return cognoms; }
    public String getNacionalitat() { return nacionalitat; }
    public int    getAnyNaixement() { return anyNaixement; }

    @Override
    public String toString() {
        return id + " | " + nom + " " + cognoms + " | " + nacionalitat + " (" + anyNaixement + ")";
    }
}

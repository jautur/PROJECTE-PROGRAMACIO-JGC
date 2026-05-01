package com.projecte.georgi;

import java.io.Serializable;

public class Usuari implements Serializable {

    public static final String ROL_USUARI = "ROL_USUARI";
    public static final String ROL_ADMIN  = "ROL_ADMIN";

    private int    id;
    private String nom;
    private String cognoms;
    private String correu;
    private String contrasenya;
    private String poblacio;
    private String rol;
    private String dataNaixement;

    public Usuari(int id, String nom, String cognoms, String correu,
                  String contrasenya, String poblacio, String rol, String dataNaixement) {
        this.id             = id;
        this.nom            = nom;
        this.cognoms        = cognoms;
        this.correu         = correu;
        this.contrasenya    = contrasenya;
        this.poblacio       = poblacio;
        this.rol            = rol;
        this.dataNaixement  = dataNaixement;
    }

    // Getters
    public int    getId()            { return id; }
    public String getNom()           { return nom; }
    public String getCognoms()       { return cognoms; }
    public String getCorreu()        { return correu; }
    public String getContrasenya()   { return contrasenya; }
    public String getPoblacio()      { return poblacio; }
    public String getRol()           { return rol; }
    public String getDataNaixement() { return dataNaixement; }

    public String getCarpetaPersonal() {
        String partCorreu = correu.contains("@") ? correu.split("@")[0] : correu;
        return id + partCorreu;
    }

    public boolean esAdmin() {
        return ROL_ADMIN.equals(rol);
    }

    @Override
    public String toString() {
        return id + " | " + nom + " " + cognoms + " | " + correu + " | " + rol;
    }
}

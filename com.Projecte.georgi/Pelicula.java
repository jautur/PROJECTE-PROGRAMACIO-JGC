package com.projecte.georgi;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private int    id;
    private String titol;
    private int    any;
    private int    duracioMinuts;
    private String director;
    private String genere;

    public Pelicula(int id, String titol, int any, int duracioMinuts, String director, String genere) {
        this.id            = id;
        this.titol         = titol;
        this.any           = any;
        this.duracioMinuts = duracioMinuts;
        this.director      = director;
        this.genere        = genere;
    }

    public int    getId()            { return id; }
    public String getTitol()         { return titol; }
    public int    getAny()           { return any; }
    public int    getDuracioMinuts() { return duracioMinuts; }
    public String getDirector()      { return director; }
    public String getGenere()        { return genere; }

    @Override
    public String toString() {
        return id + " | " + titol + " (" + any + ") | " + duracioMinuts + " min | " + director;
    }
}

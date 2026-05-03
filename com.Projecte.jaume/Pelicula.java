import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Pelicula implements Serializable, Gestionable, Comparable<Pelicula> , Comparator<Pelicula> {
    private String titol;
    private int any;
    private int duracio;
    private String productora;
    private double puntuacio;
    private Random aleatori = new Random();

    public Pelicula(String titol, int any) {
        this.titol = titol;
        this.any = any;
        this.duracio = aleatori.nextInt(120) + 50;
        this.productora = "Productora n" + (aleatori.nextInt(10) + 1);
        this.puntuacio = aleatori.nextDouble() * 10;
    }

    public Pelicula(String titol, int any, int duracio, String productora, double puntuacio) {
        this.titol = titol;
        this.any = any;
        this.duracio = duracio;
        this.productora = productora;
        this.puntuacio = puntuacio;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public int getDuracio() {
        return duracio;
    }

    public void setDuracio(int duracio) {
        this.duracio = duracio;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public double getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(double puntuacio) {
        this.puntuacio = puntuacio;
    }

    @Override
    public String getIdentificador() {
        return titol;
    }

    @Override
    public String resum() {
        return titol + " (" + any + ") - " + duracio + " minuts";
    }

    @Override
    public void mostrarDetalls() {
        System.out.println("    Titol: " + titol.toUpperCase());
        System.out.println("    Any: " + any);
        System.out.println("    Duracio: " + duracio + " minuts");
        System.out.println("    Productora: " + productora);
        System.out.println("    Puntuacio: " + puntuacio);
    }

    @Override
    public int compareTo(Pelicula o) {
        return this.titol.compareToIgnoreCase(o.titol);
    }

    @Override
    public int compare(Pelicula o1, Pelicula o2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pelicula))
            return false;
        Pelicula p = (Pelicula) o;
        return Objects.equals(this.titol, p.titol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titol);
    }
}
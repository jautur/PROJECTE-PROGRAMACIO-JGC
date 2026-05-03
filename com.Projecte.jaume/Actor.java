import java.io.Serializable;
import java.util.Objects;

public class Actor implements Serializable {
    private String nom;
    private int edat;

    public Actor(String nom, int edat) {
        this.nom = nom;
        this.edat = edat;
    }

    public String toString() {
        return nom + " (" + edat + " anys)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Actor))
            return false;
        Actor a = (Actor) o;
        return Objects.equals(this.nom, a.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
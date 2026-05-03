import java.io.Serializable;
import java.util.Objects;

public class Director implements Serializable {
    private String nom;
    private String nacionalitat;

    public Director(String nom, String nacionalitat) {
        this.nom = nom;
        this.nacionalitat = nacionalitat;
    }

    public String toString() {
        return nom + " - " + nacionalitat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Director))
            return false;
        Director d = (Director) o;
        return Objects.equals(this.nom, d.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
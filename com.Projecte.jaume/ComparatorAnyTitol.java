import java.util.Comparator;

public class ComparatorAnyTitol implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        int resultat = Integer.compare(p1.getAny(), p2.getAny());

        if (resultat == 0) {
            resultat = p1.getTitol().compareToIgnoreCase(p2.getTitol());
        }

        return resultat;
    }
}
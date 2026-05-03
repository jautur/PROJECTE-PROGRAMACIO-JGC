import java.util.Comparator;

public class ComparatorDuracio implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula o1, Pelicula o2) {
        return Integer.compare(o1.getDuracio(), o2.getDuracio());
    }
}
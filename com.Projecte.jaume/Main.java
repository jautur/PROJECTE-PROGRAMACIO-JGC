import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    private static final String FITXER = "Usuaris.dat";

    public static void main(String[] args) {
        creaEstructuraDades();
        iniciApp();
    }

    public static void creaEstructuraDades() {
        File dades = new File("dades");
        File usuaris = new File(FITXER);
        if (!usuaris.exists()) {
            try {
                usuaris.createNewFile();
                System.out.println("Creat fitxer: " + usuaris.getPath());
            } catch (IOException e) {
                System.out.println("Error creant el fitxer " + usuaris.getPath());
                e.printStackTrace();
            }
        }
        if (!dades.exists()) {
            dades.mkdir();
        }

        String[] tipus = { "actor", "director", "pelicules" };

        for (String t : tipus) {
            File f = new File("dades/" + t + ".dades");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                    System.out.println("Creat fitxer: " + f.getPath());
                } catch (IOException e) {
                    System.out.println("Error creant el fitxer " + f.getPath());
                    e.printStackTrace();
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------
    // _____________________________________MENUS________________________________
    // -----------------------------------------------------------------------------------------

    public static void iniciApp() {
        System.out.println("\n\n\n\n\n");
        carregaUsuaris();
        System.out.println("Benvingut a l'aplicació de gestió d'usuaris");
        System.out.println("1. Iniciar sessió");
        System.out.println("2. Registrar-se");

        int opcio = entrada.nextInt();
        entrada.nextLine();
        switch (opcio) {
            case 1:
                iniciarSessio();
                break;
            case 2:
                registrarUsuari1();
                break;
            default:
                System.out.println("Opció no vàlida");
        }
    }

    public static void menuUsuari(Usuario usuari) {
        int opcio;

        do {
            System.out.println("\nMENÚ D'USUARI - " + usuari.getNom().toUpperCase());
            System.out.println("1. Veure llistats PERSONALS");
            System.out.println("2. Veure llistats GENERALS");
            System.out.println("3. Afegir elements a llistats PERSONALS");
            System.out.println("4. Afegir elements a llistats GENERALS");
            System.out.println("5. Eliminar elements de llistats PERSONALS");
            if (usuari.getRol() == rol.ROL_ADMIN)
                System.out.println("6. Eliminar elements de llistats GENERALS");
            System.out.println("0. Eixir");

            opcio = entrada.nextInt();
            entrada.nextLine();

            switch (opcio) {
                case 1:
                    menuVeure(usuari);
                    break;
                case 2:
                    menuVeureGeneral(usuari);
                    break;
                case 3:
                    menuAfegir(usuari);
                    break;
                case 4:
                    menuAfegirGeneral(usuari);
                    break;
                case 5:
                    menuEliminar(usuari);
                    break;
                case 6:
                    if (usuari.getRol() == rol.ROL_ADMIN) {
                        menuEliminarGeneral(usuari);
                    } else {
                        System.out.println("Opció no vàlida");
                    }
                    break;
                case 0:
                    iniciApp();
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }

        } while (opcio != 0);
    }

    // -----------------------------------------------------------------------------------------
    // _____________________________________CARREGA
    // USUARIS________________________________
    // -----------------------------------------------------------------------------------------

    public static void carregaUsuaris() {
        System.out.println("Carregant usuaris...");
        Usuario admin = new Usuario("admin", rol.ROL_ADMIN);
        // Usuario usuari1 = new Usuario("usu", "usu");
    }

    // -----------------------------------------------------------------------------------------
    // _____________________________________INICI y
    // REGISTRE________________________________
    // -----------------------------------------------------------------------------------------

    public static void iniciarSessio() {
        System.out.println("Usuari:");
        String usuari = entrada.nextLine();
        System.out.println("Contrasenya:");
        String contrasenya = entrada.nextLine();
        Usuario u = Usuario.login(usuari, contrasenya);
        if (u != null) {
            System.out.println("Benvingut/da " + u.getNomComplet());
            comprovaLlistes(u);
            menuUsuari(u);
        } else {
            System.out.println("Accés denegat");
            iniciApp();
        }
    }

    public static void registrarUsuari1() {

        System.out.println("Nom:");
        String nom = entrada.nextLine();

        System.out.println("Cognoms:");
        String cognoms = entrada.nextLine();

        System.out.println("Correu electrònic:");
        String email = entrada.nextLine();

        String pass1, pass2;
        do {
            System.out.println("Contrasenya:");
            pass1 = entrada.nextLine();

            System.out.println("Repetix contrasenya:");
            pass2 = entrada.nextLine();

            if (!pass1.equals(pass2)) {
                System.out.println("Les contrasenyes no coincideixen. Torna-ho a intentar.");
            }

        } while (!pass1.equals(pass2));

        System.out.println("Població:");
        String poblacio = entrada.nextLine();

        System.out.println("Data de naixement (yyyy-mm-dd):");
        String fecNac = entrada.nextLine();

        if (!FITXER.contains(email)) {
            Usuario nou = new Usuario(nom, cognoms, email, pass1, poblacio, fecNac);
            System.out.println("Usuari registrat correctament.");
            menuUsuari(nou);
        } else {
            System.out.println("Aquest correu ja està registrat. Torna-ho a intentar.");
            iniciApp();
        }

    }

    // -----------------------------------------------------------------------------------------
    // ________________________LLEGIR y GUARDAR ________________________________
    // -----------------------------------------------------------------------------------------

    public static <T> ArrayList<T> llegirFitxer(String ruta) {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
                return (ArrayList<T>) ois.readObject();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void guardarFitxer(String ruta, ArrayList<?> llista) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(llista);
            oos.close();
            System.out.println("Element afegit correctament.");
        } catch (Exception e) {
            System.out.println("Error guardant fitxer");
        }
    }

    // -----------------------------------------------------------------------------------------
    // ________________________COMPROVALLISTES y
    // ORDENALLISTES________________________________
    // -----------------------------------------------------------------------------------------

    public static void comprovaLlistes(Usuario usuari) {
        System.out.println("Comprovant llistats...");
        String[] tipus = { "actor", "director", "pelicules" };
        for (String t : tipus) {
            String ruta = usuari.getCarpeta() + "/" + t + ".llista";
            File f = new File(ruta);
            if (f.exists()) {
                String rutaGeneral = "dades/" + t + ".dades";
                ArrayList<Object> llistaGeneral = llegirFitxer(rutaGeneral);
                ArrayList<Object> llistaUsuari = llegirFitxer(ruta);
                llistaUsuari.removeIf(element -> !llistaGeneral.contains(element));
                guardarFitxer(ruta, llistaUsuari);
            } else {
                System.out.println("No s'ha trobat el llistat de " + t + " de l'usuari.");
            }
        }
        System.out.println("Llistats actualitzats.");
    }

    public static void ordenarPelicules(ArrayList<Object> llista) {

        System.out.println("\nCom vols ordenar les pel·lícules?");
        System.out.println("1. Per títol (Comparable)");
        System.out.println("2. Per duració (Comparator)");
        System.out.println("3. Per any + títol (Comparator múltiple)");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {

            case 1:
                llista.sort((a, b) -> ((Pelicula) a).compareTo((Pelicula) b));
                break;

            case 2:
                llista.sort((a, b) -> Integer.compare(
                        ((Pelicula) a).getDuracio(),
                        ((Pelicula) b).getDuracio()));
                break;

            case 3:
                llista.sort((a, b) -> {
                    Pelicula p1 = (Pelicula) a;
                    Pelicula p2 = (Pelicula) b;

                    int cmpAny = Integer.compare(p1.getAny(), p2.getAny());

                    if (cmpAny != 0)
                        return cmpAny;

                    return p1.getTitol().compareToIgnoreCase(p2.getTitol());
                });
                break;

            default:
                System.out.println("Opció no vàlida. No s'ordena.");
        }
    }

    // ______________________________________________________________________________________
    // _________________________LLISTATS PERSONALS________________________________
    // -----------------------------------------------------------------------------------------

    public static void menuVeure(Usuario usuari) {
        System.out.println("1. Actors");
        System.out.println("2. Directors");
        System.out.println("3. Pel·lícules");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                veureLlista(usuari, "actor");
                break;
            case 2:
                veureLlista(usuari, "director");
                break;
            case 3:
                veureLlista(usuari, "pelicules");
                break;
        }
    }

    public static void menuAfegir(Usuario usuari) {
        System.out.println("1. Actor");
        System.out.println("2. Director");
        System.out.println("3. Pel·lícula");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                afegirLlista(usuari, "actor");
                break;
            case 2:
                afegirLlista(usuari, "director");
                break;
            case 3:
                afegirLlista(usuari, "pelicules");
                break;
        }
    }

    public static void menuEliminar(Usuario usuari) {
        System.out.println("1. Actors");
        System.out.println("2. Directors");
        System.out.println("3. Pel·lícules");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                eliminarLlista(usuari, "actor");
                break;
            case 2:
                eliminarLlista(usuari, "director");
                break;
            case 3:
                eliminarLlista(usuari, "pelicules");
                break;
        }
    }

    public static void afegirLlista(Usuario usuari, String tipus) {
        String ruta = usuari.getCarpeta() + "/" + tipus + ".llista";
        String rutaGeneral = "dades/" + tipus + ".dades";
        ArrayList<Object> llista = llegirFitxer(ruta);
        ArrayList<Object> llistaGeneral = llegirFitxer(rutaGeneral);
        Object nou = null;
        switch (tipus) {

            case "actor":
                System.out.println("Nom actor:");
                String nomA = entrada.nextLine();

                System.out.println("Edat:");
                int edat = entrada.nextInt();
                entrada.nextLine();
                nou = new Actor(nomA, edat);
                if (!llista.contains(nou)) {
                    if (!llistaGeneral.contains(nou)) {
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    } else {
                        System.out.println("Aquest element tambe existeix a la llista general.");
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    }
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;

            case "director":
                System.out.println("Nom director:");
                String nomD = entrada.nextLine();

                System.out.println("Nacionalitat:");
                String nac = entrada.nextLine();

                nou = new Director(nomD, nac);
                if (!llista.contains(nou)) {
                    if (!llistaGeneral.contains(nou)) {
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    } else {
                        System.out.println("Aquest element tambe existeix a la llista general.");
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    }
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;

            case "pelicules":
                System.out.println("Títol:");
                String titol = entrada.nextLine();

                System.out.println("Any:");
                int any = entrada.nextInt();
                entrada.nextLine();

                nou = new Pelicula(titol, any);
                if (!llista.contains(nou)) {
                    if (!llistaGeneral.contains(nou)) {
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    } else {
                        System.out.println("Aquest element tambe existeix a la llista general.");
                        llista.add(nou);
                        llistaGeneral.add(nou);
                    }
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;
        }
        guardarFitxer(ruta, llista);
        guardarFitxer(rutaGeneral, llistaGeneral);
    }

    public static void veureLlista(Usuario usuari, String tipus) {
        String ruta = usuari.getCarpeta() + "/" + tipus + ".llista";
        ArrayList<Object> llista = llegirFitxer(ruta);
        if (llista.isEmpty()) {
            System.out.println("No hi ha dades.");
            return;
        }
        int opcio;
        if (tipus.equals("pelicules")) {
            ordenarPelicules(llista);
        }
        do {
            System.out.println("\nLLISTA DE " + tipus.toUpperCase());

            for (int i = 0; i < llista.size(); i++) {
                if (tipus.equals("pelicules")) {
                    System.out.println((i + 1) + ". " + ((Pelicula) llista.get(i)).resum());
                } else {
                    System.out.println((i + 1) + ". " + llista.get(i));
                }
            }

            System.out.println("0. Tornar");

            opcio = entrada.nextInt();
            entrada.nextLine();

            if (opcio > 0 && opcio <= llista.size()) {
                System.out.println("\nDETALL:");
                System.out.println(llista.get(opcio - 1));
            }
        } while (opcio != 0);
    }

    public static void eliminarLlista(Usuario usuari, String tipus) {
        String ruta = usuari.getCarpeta() + "/" + tipus + ".llista";
        ArrayList<Object> llista = llegirFitxer(ruta);
        if (llista.isEmpty()) {
            System.out.println("No hi ha dades.");
            return;
        }
        int opcio;
        System.out.println("\nLLISTA DE " + tipus.toUpperCase());

        for (int i = 0; i < llista.size(); i++) {
            if (tipus.equals("pelicules")) {
                System.out.println((i + 1) + ". " + ((Pelicula) llista.get(i)).resum());
            } else {
                System.out.println((i + 1) + ". " + llista.get(i));
            }
        }
        System.out.println("Introdueix el número de l'element a eliminar (0 per tornar):");
        opcio = entrada.nextInt();
        entrada.nextLine();
        if (opcio > 0) {
            if (opcio <= llista.size()) {
                llista.remove(opcio - 1);
                guardarFitxer(ruta, llista);
                System.out.println("Element eliminat correctament.");
            }
        } else {
            System.out.println("Tornant al menú anterior.");
        }
    }

    // ______________________________________________________________________________________
    // _____________________________________LLISTATS
    // GENERALS________________________________
    // -----------------------------------------------------------------------------------------

    public static void menuVeureGeneral(Usuario usuari) {
        System.out.println("1. Actors");
        System.out.println("2. Directors");
        System.out.println("3. Pel·lícules");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                veureLlistaGeneral(usuari, "actor");
                break;
            case 2:
                veureLlistaGeneral(usuari, "director");
                break;
            case 3:
                veureLlistaGeneral(usuari, "pelicules");
                break;
        }
    }

    public static void menuAfegirGeneral(Usuario usuari) {
        System.out.println("1. Actor");
        System.out.println("2. Director");
        System.out.println("3. Pel·lícula");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                afegirLlistaGeneral(usuari, "actor");
                break;
            case 2:
                afegirLlistaGeneral(usuari, "director");
                break;
            case 3:
                afegirLlistaGeneral(usuari, "pelicules");
                break;
        }
    }

    public static void menuEliminarGeneral(Usuario usuari) {
        System.out.println("1. Actors");
        System.out.println("2. Directors");
        System.out.println("3. Pel·lícules");

        int opcio = entrada.nextInt();
        entrada.nextLine();

        switch (opcio) {
            case 1:
                eliminarLlistaGeneral(usuari, "actor");
                break;
            case 2:
                eliminarLlistaGeneral(usuari, "director");
                break;
            case 3:
                eliminarLlistaGeneral(usuari, "pelicules");
                break;
        }
    }

    public static void afegirLlistaGeneral(Usuario usuari, String tipus) {
        String ruta = "dades/" + tipus + ".dades";
        ArrayList<Object> llista = llegirFitxer(ruta);
        Object nou = null;
        switch (tipus) {

            case "actor":
                System.out.println("Nom actor:");
                String nomA = entrada.nextLine();

                System.out.println("Edat:");
                int edat = entrada.nextInt();
                entrada.nextLine();
                nou = new Actor(nomA, edat);
                if (!llista.contains(nou)) {
                    llista.add(nou);
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;

            case "director":
                System.out.println("Nom director:");
                String nomD = entrada.nextLine();

                System.out.println("Nacionalitat:");
                String nac = entrada.nextLine();

                nou = new Director(nomD, nac);
                if (!llista.contains(nou)) {
                    llista.add(nou);
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;

            case "pelicules":
                System.out.println("Títol:");
                String titol = entrada.nextLine();

                System.out.println("Any:");
                int any = entrada.nextInt();
                entrada.nextLine();

                nou = new Pelicula(titol, any);
                if (!llista.contains(nou)) {
                    llista.add(nou);
                } else {
                    System.out.println("Aquest element ja existeix a la llista.");
                }
                break;
        }
        guardarFitxer(ruta, llista);
        System.out.println("Element afegit correctament.");
    }

    public static void veureLlistaGeneral(Usuario usuari, String tipus) {
        String ruta = "dades/" + tipus + ".dades";
        ArrayList<Object> llista = llegirFitxer(ruta);
        if (llista.isEmpty()) {
            System.out.println("No hi ha dades.");
            return;
        }
        int opcio;
        if (tipus.equals("pelicules")) {
            ordenarPelicules(llista);
        }
        do {
            System.out.println("\nLLISTA DE " + tipus.toUpperCase());

            for (int i = 0; i < llista.size(); i++) {
                if (tipus.equals("pelicules")) {
                    System.out.println((i + 1) + ". " + ((Pelicula) llista.get(i)).resum());
                } else {
                    System.out.println((i + 1) + ". " + llista.get(i));
                }
            }

            System.out.println("0. Tornar");

            opcio = entrada.nextInt();
            entrada.nextLine();

            if (opcio > 0 && opcio <= llista.size()) {
                System.out.println("\nDETALL:");
                System.out.println(llista.get(opcio - 1));
            }
        } while (opcio != 0);
    }

    public static void eliminarLlistaGeneral(Usuario usuari, String tipus) {
        String ruta = "dades/" + tipus + ".dades";
        ArrayList<Object> llista = llegirFitxer(ruta);
        if (llista.isEmpty()) {
            System.out.println("No hi ha dades.");
            return;
        }
        int opcio;
        if (tipus.equals("pelicules")) {
            ordenarPelicules(llista);
        }
        System.out.println("\nLLISTA DE " + tipus.toUpperCase());

        for (int i = 0; i < llista.size(); i++) {
            if (tipus.equals("pelicules")) {
                System.out.println((i + 1) + ". " + ((Pelicula) llista.get(i)).resum());
            } else {
                System.out.println((i + 1) + ". " + llista.get(i));
            }
        }
        System.out.println("Introdueix el número de l'element a eliminar (0 per tornar):");
        opcio = entrada.nextInt();
        entrada.nextLine();
        if (opcio > 0) {
            if (opcio <= llista.size()) {
                llista.remove(opcio - 1);
                guardarFitxer(ruta, llista);
                System.out.println("Element eliminat correctament.");
            }
        } else {
            System.out.println("Tornant al menú anterior.");
        }
    }

}
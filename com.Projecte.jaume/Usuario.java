import java.io.*;
import java.util.ArrayList;

public class Usuario implements Serializable{

    private int id;
    String nom;
    private String cognoms;
    private String email;
    private String passwd;
    private String poblacio;
    private rol rol;
    private String fecNac;

    private static final String FITXER = "Usuaris.dat";

    public Usuario(String nom, rol rol) {
        this.id = 0;
        this.nom = nom;
        this.cognoms = nom;
        this.email = nom;
        this.passwd = nom;
        this.poblacio = nom;
        this.rol = rol.ROL_ADMIN;
        this.fecNac = null;

        guardar();
        crearDirectorio();
    }

    public Usuario(String nom, String cognoms, String email, String passwd, String poblacio, String fecNac) {
        this.id = ultimID() + 1;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.passwd = passwd;
        this.poblacio = poblacio;
        this.rol = rol.ROL_USUARI;
        this.fecNac = fecNac;

        guardar();
        crearDirectorio();
    }

    public Usuario(String nom, String passwd) {
        this.id = ultimID() + 1;
        this.nom = nom;
        this.cognoms = "default";
        this.email = "email" + id + "@email.com";
        this.passwd = passwd;
        this.poblacio = "default";
        this.rol = rol.ROL_USUARI;
        this.fecNac = null;

        guardar();
        crearDirectorio();
    }

    private void guardar() {
        ArrayList<Usuario> usuarios = leerTodos();

        if (!existeUsuario(usuarios)) {
            usuarios.add(this);
            escribirTodos(usuarios);
            System.out.println("Usuari registrat: " + email);
        } else {
            System.out.println("Usuari ja existeix: " + email);
        }
    }

    public static ArrayList<Usuario> leerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FITXER))) {

            lista = (ArrayList<Usuario>) ois.readObject();

        } catch (Exception e) {
            System.out.println("FITXER BUIT");
        }

        return lista;
    }

    public static Usuario login(String nom, String passwd) {
        ArrayList<Usuario> usuarios = leerTodos();

        if (usuarios == null || usuarios.isEmpty()) {
            return null;
        }

        for (Usuario u : usuarios) {
            if (u.getNom() != null && u.getPasswd() != null &&
                    u.getNom().equals(nom) && u.getPasswd().equals(passwd)) {
                return u;
            }
        }

        return null;
    }

    private void escribirTodos(ArrayList<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error al guardar objectes");
        }
    }

    private boolean existeUsuario(ArrayList<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            if (u.email.equals(this.email)) {
                return true;
            }
        }
        return false;
    }

    private int ultimID() {
        ArrayList<Usuario> usuarios = leerTodos();
        int max = 0;

        for (Usuario u : usuarios) {
            if (u.id > max) {
                max = u.id;
            }
        }

        return max;
    }

    private void crearDirectorio() {
        String nombre = getCarpeta();

        File dir = new File(nombre);

        if (!dir.exists()) {
            if (dir.mkdir()) {
                try {
                    new File(dir, "pelicules.llista").createNewFile();
                    new File(dir, "actor.llista").createNewFile();
                    new File(dir, "director.llista").createNewFile();

                    System.out.println("Carpeta i fitxers creats correctament");

                } catch (Exception e) {
                    System.out.println("Error creant fitxers");
                }
            } else {
                System.out.println("No s'ha pogut crear la carpeta");
            }
        }

        //System.out.println("NOM CARPETA: " + nombre);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String getNomComplet() {
        return nom + " " + cognoms;
    }

    public String getCarpeta() {
        return id + (email.contains("@") ? email.substring(0, email.indexOf("@")) : email);
    }

    public rol getRol() {
        return rol;
    }

    

}
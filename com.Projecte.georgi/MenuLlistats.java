package com.projecte.georgi;

import java.util.Scanner;

public class MenuLlistats {

    private Scanner sc      = new Scanner(System.in);
    private Usuari  usuari;

    public MenuLlistats(Usuari usuari) {
        this.usuari = usuari;
    }

    public void mostrarMenu() {
        System.out.println("\nBenvingut/da, " + usuari.getNom() + " " + usuari.getCognoms() + "!");

        int opcio;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Veure llistats");
            System.out.println("2. Afegir elements");
            System.out.println("0. Tancar sessió");
            System.out.print("Opció: ");
            opcio = Integer.parseInt(sc.nextLine().trim());

            switch (opcio) {
                case 1 -> menuVeureLlistats();
                case 2 -> menuAfegirElements();
                case 0 -> System.out.println("Fins aviat, " + usuari.getNom() + "!");
                default -> System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private void menuVeureLlistats() {
        int opcio;
        do {
            System.out.println("\nVEURE LLISTATS");
            System.out.println("1. Películes");
            System.out.println("2. Actors");
            System.out.println("3. Directors");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = Integer.parseInt(sc.nextLine().trim());

            switch (opcio) {
                case 1 -> mostrarLlistat("pelicula");
                case 2 -> mostrarLlistat("actor");
                case 3 -> mostrarLlistat("director");
                case 0 -> {}
                default -> System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private void mostrarLlistat(String tipus) {
        System.out.println("Mostrar llistat de: " + tipus);
    }

    private void menuAfegirElements() {
        int opcio;
        do {
            System.out.println("\nAFEGIR ELEMENTS");
            System.out.println("1. Afegir pel·lícula");
            System.out.println("2. Afegir actor");
            System.out.println("3. Afegir director");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = Integer.parseInt(sc.nextLine().trim());

            switch (opcio) {
                case 1 -> afegirPelicula();
                case 2 -> afegirActor();
                case 3 -> afegirDirector();
                case 0 -> {}
                default -> System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private void afegirPelicula() {
        System.out.println("Afegir pel·lícula pendent d'implementar.");
    }

    private void afegirActor() {
        System.out.println("Afegir actor pendent d'implementar.");
    }

    private void afegirDirector() {
        System.out.println("Afegir director pendent d'implementar.");
    }
}

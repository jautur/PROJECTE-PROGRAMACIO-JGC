package com.projecte.georgi;

import java.util.Scanner;

public class MenuAcces {

    private Scanner sc = new Scanner(System.in);

    public Usuari mostrarMenu() {
        int opcio;
        do {
            System.out.println("\n=== MENÚ D'ACCÉS ===");
            System.out.println("1. Login");
            System.out.println("2. Registre");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = Integer.parseInt(sc.nextLine().trim());

            switch (opcio) {
                case 1 -> { return login(); }
                case 2 -> { registre(); }
                case 0 -> { System.out.println("Adeu!"); }
                default -> System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);

        return null;
    }

    private Usuari login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Usuari: ");
        String usuari = sc.nextLine().trim();
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine().trim();

        System.out.println("Comprovació pendent d'implementar.");
        return null;
    }

    private void registre() {
        System.out.println("\n--- REGISTRE ---");
        System.out.print("Nom: ");
        String nom = sc.nextLine().trim();
        System.out.print("Cognoms: ");
        String cognoms = sc.nextLine().trim();
        System.out.print("Correu: ");
        String correu = sc.nextLine().trim();
        System.out.print("Contrasenya: ");
        String pass1 = sc.nextLine().trim();
        System.out.print("Repeteix contrasenya: ");
        String pass2 = sc.nextLine().trim();
        System.out.print("Població: ");
        String poblacio = sc.nextLine().trim();
        System.out.print("Data de naixement (dd/MM/yyyy): ");
        String data = sc.nextLine().trim();

        System.out.println("Guardat en fitxer pendent d'implementar.");
    }
}

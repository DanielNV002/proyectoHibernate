package org.example.Proyect;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Gestion G = new Gestion();

        int eleccion = 1;

        System.out.println("\n --- Bienvenido a AnimaLost ---");

        while (eleccion != 0){
        System.out.println("""
                      ╔═════════════════════════════════════════╗
                      ║  1. Registrar un animal perdido         ║
                      ║  2. Buscar animales por especie         ║
                      ║  3. Actualizar estado                   ║
                      ║  4. Ver datos de la familia de acogida  ║
                      ║  0. Salir                               ║
                      ╚═════════════════════════════════════════╝
                """);

        System.out.print("Opcion: ");
        eleccion = in.nextInt();

            switch (eleccion){
                case 0:
                    System.out.println("\n --- Hasta Pronto ---");
                    G.registro();
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("\n --- Registrar ---");
                    break;
                case 2:
                    System.out.println("\n --- Buscar por especie ---");
                    break;
                case 3:
                    System.out.println("\n --- Actuaizar estado ---");
                    break;
                case 4:
                    System.out.println("\n --- Datos de Familiares ---");
                    break;
                default:
                    System.out.println("\n Eleccion no valida");
                    break;
            }
        }

    }
}
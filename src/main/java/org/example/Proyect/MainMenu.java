package org.example.Proyect;

import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        // Creamos las tablas de la BBDD
        crearTablas();

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
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("\n --- Registrar ---");
                    G.registro();
                    break;
                case 2:
                    System.out.println("\n --- Buscar por especie ---");
                    G.buscarByEspecie();
                    break;
                case 3:
                    System.out.println("\n --- Actuaizar estado ---");
                    G.actualizarEstado();
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

    public static void crearTablas(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }
}
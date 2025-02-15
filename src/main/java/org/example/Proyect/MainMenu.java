/**
 * Clase MainMenu que representa el menú principal de la aplicación AnimaLost.
 * Esta clase proporciona un menú interactivo que permite gestionar animales perdidos,
 * familias de acogida y realizar adopciones.
 *
 * @version 1.0
 * @author Daniel Navarro
 */
package org.example.Proyect;

import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class MainMenu {

    /**
     * Método principal que inicia la aplicación AnimaLost y muestra el menú interactivo.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Crear las tablas de la base de datos al iniciar la aplicación
        crearTablas();

        Scanner in = new Scanner(System.in);
        Gestion G = new Gestion();

        int eleccion = 1;

        System.out.println("\n --- Bienvenido a AnimaLost ---");

        while (eleccion != 0) {
            System.out.println("""
                      ╔═════════════════════════════════════════╗
                      ║  1. Registrar un animal perdido         ║
                      ║  2. Buscar animales por especie         ║
                      ║  3. Actualizar estado                   ║
                      ║  4. Registrar Familia                   ║
                      ║  5. Ver datos de la familia de acogida  ║
                      ║  6. Hacer adopcion                      ║
                      ║  0. Salir                               ║
                      ╚═════════════════════════════════════════╝
                """);

            System.out.print("Opcion: ");
            eleccion = in.nextInt();

            switch (eleccion) {
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
                    System.out.println("\n --- Actualizar estado ---");
                    G.actualizarEstado();
                    break;
                case 4:
                    System.out.println("\n --- Registrar Familia ---");
                    G.registroFamilia();
                    break;
                case 5:
                    System.out.println("\n --- Datos de Familiares ---");
                    G.verDatosFamilia();
                    break;
                case 6:
                    System.out.println("\n --- Hacer Adopcion ---");
                    G.hacerAdopcion();
                    break;
                default:
                    System.out.println("\n --- ELECCION NO VALIDA --- ");
                    break;
            }
        }
    }

    /**
     * Método para crear las tablas necesarias en la base de datos.
     * Se ejecuta al inicio de la aplicación.
     */
    public static void crearTablas() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }
}
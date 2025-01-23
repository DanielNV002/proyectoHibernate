/**
 * La clase Gestion proporciona métodos para gestionar animales y familias de acogida
 * en un sistema de adopción y refugio. Permite registrar animales y familias, actualizar
 * estados de animales, buscar animales por especie, realizar adopciones y más.
 *
 * @version 1.0
 * @author Daniel Navarro
 */
package org.example.Proyect;

import org.example.DAO.animalDAO;
import org.example.DAO.animalDAOImpl;
import org.example.DAO.familiaDAOImpl;
import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.example.entities.familiAcogida;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Gestion {

    /**
     * Capitaliza la primera letra de una cadena y convierte el resto a minúsculas.
     *
     * @param input Cadena de texto a capitalizar.
     * @return Cadena con la primera letra en mayúscula y el resto en minúsculas.
     */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Registra un nuevo animal en la base de datos. Solicita los datos al usuario
     * y crea un nuevo objeto {@link animal} que luego se almacena en la base de datos.
     */
    public void registro() {
        Scanner in = new Scanner(System.in);
        animal A = new animal();

        System.out.println("Introduzca los datos para el registro");
        System.out.print("Nombre: ");
        A.setNombre(capitalize(in.nextLine()));

        System.out.print("Especie: ");
        A.setEspecie(capitalize(in.nextLine()));

        System.out.print("Edad: ");
        A.setEdad(in.nextInt());

        in.nextLine();

        System.out.print("Descripcion de como se perdio: ");
        A.setDescripcionPerdida(in.nextLine());

        System.out.print("Estado del animal: ");
        A.setEstado(in.nextLine());

        A = new animal(null, A.getNombre(), A.getEspecie(), A.getEdad(), A.getDescripcionPerdida(), A.getEstado());
        animalDAO animal = new animalDAOImpl();
        animal.create(A);
    }

    /**
     * Muestra todas las especies registradas en la base de datos. Devuelve true
     * si hay especies registradas, de lo contrario, devuelve false.
     *
     * @return true si hay especies registradas, false en caso contrario.
     */
    public boolean mostrarEspecies() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            String hql = "SELECT DISTINCT a.especie FROM animal a";
            Query<String> query = session.createQuery(hql, String.class);
            List<String> especies = query.list();

            if (especies.isEmpty()) {
                System.out.println("No hay especies registradas.");
                return false;
            } else {
                System.out.println("Especies registradas:");
                System.out.print(" | ");
                for (String especie : especies) {
                    System.out.print(especie + " | ");
                }
                System.out.println();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    /**
     * Permite buscar animales por especie. Solicita al usuario la especie a buscar
     * y muestra los resultados obtenidos de la base de datos.
     */
    public void buscarByEspecie() {
        Scanner in = new Scanner(System.in);

        boolean hayEspecies = mostrarEspecies();
        if (!hayEspecies) {
            System.out.println("No hay especies para buscar.");
            return;
        }

        System.out.println("Introduce la especie de búsqueda:");
        String especie = capitalize(in.nextLine());
        animalDAO animal = new animalDAOImpl();
        animal.findByEspecie(especie);
    }

    /**
     * Permite actualizar el estado de un animal. Solicita al usuario el ID del animal
     * y elige un nuevo estado entre las opciones disponibles.
     */
    public void actualizarEstado() {
        Scanner in = new Scanner(System.in);

        String estadoA = "";

        System.out.println("Introduzca el ID del animal: ");
        Integer id = in.nextInt();

        Integer estado = 10;

        while (estado != 0) {
            System.out.println("""
                          ╔═════════════════════════════════════════╗
                          ║  1. Recien abandonado                   ║
                          ║  2. Tiempo en el refugio                ║
                          ║  3. Proximamente en acogida             ║
                          ║  0. Salir                               ║
                          ╚═════════════════════════════════════════╝
                    """);
            System.out.println("Introduzca el nuevo estado: ");
            estado = in.nextInt();

            switch (estado) {
                case 0:
                    System.out.println(" --- SALIENDO ---");
                    break;
                case 1:
                    estadoA = "Recien abandonado";
                    break;
                case 2:
                    estadoA = "Tiempo en el refugio";
                    break;
                case 3:
                    estadoA = "Proximamente en acogida";
                    break;
                default:
                    System.out.println("--- OPCION NO VALIDA ---");
                    break;
            }

            if (estado != 0) {
                animalDAO animal = new animalDAOImpl();
                animal.updateEstadoById(id, estadoA);
            }
        }
    }

    /**
     * Registra una nueva familia de acogida en la base de datos. Solicita los datos
     * al usuario y crea un nuevo objeto {@link familiAcogida} que se almacena en la base de datos.
     */
    public void registroFamilia() {
        Scanner in = new Scanner(System.in);
        familiAcogida A = new familiAcogida();

        System.out.println("Introduzca los datos para el registro");
        System.out.print("Nombre: ");
        A.setNombre(capitalize(in.nextLine()));

        System.out.print("Ciudad: ");
        A.setCiudad(capitalize(in.nextLine()));

        System.out.print("Edad: ");
        A.setEdad(in.nextInt());

        A = new familiAcogida(null, A.getNombre(), A.getCiudad(), A.getEdad(), null);
        familiaDAOImpl familia = new familiaDAOImpl();
        familia.create(A);
    }

    /**
     * Muestra los datos de todas las familias de acogida registradas en la base de datos.
     */
    public void verDatosFamilia() {
        familiaDAOImpl familia = new familiaDAOImpl();
        List<familiAcogida> lista = familia.findAll();
        for (familiAcogida a : lista) {
            System.out.print(a);
        }
    }

    /**
     * Realiza una adopción asignando un animal a una familia de acogida. Solicita
     * al usuario el ID del animal y de la familia para realizar la adopción.
     */
    public void hacerAdopcion() {
        Scanner in = new Scanner(System.in);

        familiaDAOImpl familia = new familiaDAOImpl();
        animalDAO animal = new animalDAOImpl();

        System.out.print("Introduce el id del animal a adoptar: ");
        Integer idAnimal = in.nextInt();
        System.out.print("Introduce el id de la familia que adopta: ");
        Integer idFamilia = in.nextInt();

        familia.hacerAdopcion(idFamilia, animal.findById(idAnimal));
    }
}

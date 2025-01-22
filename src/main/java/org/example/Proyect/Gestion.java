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

    // Metodo para capitalizar la primera letra
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Si el string es nulo o vacío, no hace nada
        }
        // Capitalizar la primera letra y convertir el resto a minúsculas
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    //Metodo para registrar nuevo animal
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

        // Creamos el nuevo animal
        A = new animal(null, A.getNombre(), A.getEspecie(), A.getEdad(), A.getDescripcionPerdida(), A.getEstado());
        // Llamamos a la interfaz para trabajar
        animalDAO animal = new animalDAOImpl();
        // Añadimos el animal a la BBDD
        animal.create(A);
    }

    // Metodo para mostrar todas las especies registradas
    public void mostrarEspecies() {
        // Obtener la sesión de Hibernate
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // Iniciar una transacción
            session.beginTransaction();

            // Consulta HQL para obtener todos los animales
            String hql = "SELECT DISTINCT a.especie FROM animal a"; // Query para obtener especies únicas
            Query<String> query = session.createQuery(hql, String.class);

            // Ejecutar la consulta y obtener los resultados
            List<String> especies = query.list();

            // Mostrar las especies
            if (especies.isEmpty()) {
                System.out.println("No hay especies registradas.");
            } else {
                System.out.println("Especies registradas:");
                System.out.print(" | ");
                for (String especie : especies) {
                    System.out.print(especie + " | ");
                }
                System.out.println();
            }

            // Finalizar la transacción
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }

    //Metodo para buscar por especie
    public void buscarByEspecie() {
        Scanner in = new Scanner(System.in);

        mostrarEspecies();
        System.out.println("Introduce la especie de búsqueda:");
        String especie = capitalize(in.nextLine());  // Leemos la especie que se quiere buscar

        // Llamamos a la interfaz para trabajar
        animalDAO animal = new animalDAOImpl();
        // Buscamos la especie en la BBDD
        animal.findByEspecie(especie);
    }

    //Metodo para cambiar el estado de un animal
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
                // Llamamos a la interfaz para trabajar
                animalDAO animal = new animalDAOImpl();
                // Actualizamos el estado del animal deseado
                animal.updateEstadoById(id, estadoA);
            }
        }
    }

    //Metodo para registrar nueva familia
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

        // Creamos el nuevo animal
        A = new familiAcogida(null, A.getNombre(), A.getCiudad(), A.getEdad(), null);
        // Llamamos a la interfaz para trabajar
        familiaDAOImpl familia = new familiaDAOImpl();
        // Añadimos el animal a la BBDD
        familia.create(A);
    }

    //Metodo para ver los datos de la familia de acogida
    public void verDatosFamilia() {
        // Llamamos a la interfaz para trabajar
        familiaDAOImpl familia = new familiaDAOImpl();
        // Vemos todas las familais de la BBDD
        List<familiAcogida> lista = familia.findAll();
        for(familiAcogida a: lista){
            System.out.print(a);
        }
    }

    //Metodo para realizar la adopcion
    public void hacerAdopcion(){

        Scanner in = new Scanner(System.in);

        // Llamamos a la interfaz para trabajar
        familiaDAOImpl familia = new familiaDAOImpl();
        // Pedimos los datos
        System.out.print("Introduce el id del animal a adoptar: ");
        Integer idAnimal = in.nextInt();
        System.out.print("Introduce el id de la familia que adopta: ");
        Integer idFamilia = in.nextInt();

        // Metodo para hacer la adopcion
        familia.hacerAdopcion(idFamilia, idAnimal);

    }
}

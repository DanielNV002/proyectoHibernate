package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.example.entities.familiAcogida;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Implementación de la interfaz {@link familiaDAO} para realizar operaciones CRUD
 * sobre la entidad {@link familiAcogida} utilizando Hibernate.
 *
 * Esta clase incluye métodos para:
 * - Consultar todas las familias de acogida.
 * - Registrar una nueva familia de acogida.
 * - Asignar un animal a una familia de acogida para realizar la adopción.
 *
 * Las transacciones y sesiones de Hibernate son manejadas cuidadosamente para
 * garantizar que los datos sean consistentes y para manejar errores de manera adecuada.
 */
public class familiaDAOImpl implements familiaDAO {

    /**
     * Obtiene todas las familias de acogida registradas en la base de datos.
     *
     * @return Una lista de objetos {@link familiAcogida} que representan todas las familias de acogida.
     * Si no hay familias registradas, devuelve una lista vacía.
     */
    @Override
    public List<familiAcogida> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<familiAcogida> familia = null;

        try {
            transaction = session.beginTransaction();
            familia = session.createQuery("from familiAcogida", familiAcogida.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return familia;
    }

    /**
     * Registra una nueva familia de acogida en la base de datos.
     *
     * @param familiAcogida El objeto {@link familiAcogida} que contiene los datos de la nueva familia de acogida.
     * @return El objeto {@link familiAcogida} registrado en la base de datos, con su ID asignado.
     * En caso de error, se devuelve el mismo objeto sin cambios.
     */
    @Override
    public familiAcogida create(familiAcogida familiAcogida) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(familiAcogida);
            transaction.commit();
            System.out.println(" --- Familia registrada exitosamente --- ");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return familiAcogida;
    }

    /**
     * Asigna un animal a una familia de acogida para realizar la adopción.
     *
     * @param id El ID de la familia de acogida.
     * @param idAnimal El objeto {@link animal} que se desea asignar a la familia de acogida.
     * @return El objeto {@link familiAcogida} actualizado con el nuevo animal asignado.
     * Si no se encuentra la familia de acogida con el ID proporcionado, devuelve {@code null}.
     */
    @Override
    public familiAcogida hacerAdopcion(Integer id, animal idAnimal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        familiAcogida adopcion = null;

        try {
            transaction = session.beginTransaction();

            String hql = "UPDATE familiAcogida SET idAnimal = :idAnimal WHERE id = :id";
            int result = session.createQuery(hql)
                    .setParameter("idAnimal", idAnimal)
                    .setParameter("id", id)
                    .executeUpdate();

            if (result > 0) {
                adopcion = session.get(familiAcogida.class, id);
                transaction.commit();
                System.out.println("Adopción realizada correctamente.");
            } else {
                System.out.println("No se encontró la familia de acogida con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return adopcion;
    }
}

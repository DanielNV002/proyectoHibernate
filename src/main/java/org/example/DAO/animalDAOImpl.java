package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implementación de la interfaz {@code animalDAO} para realizar operaciones CRUD
 * relacionadas con la entidad {@code animal} en la base de datos.
 */
public class animalDAOImpl implements animalDAO {

    /**
     * Actualiza el estado de un animal en la base de datos identificado por su ID.
     *
     * @param id El ID del animal que se desea actualizar.
     * @param estado El nuevo estado que se desea asignar al animal.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean updateEstadoById(int id, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE animal SET estado = :estado WHERE id = :animalId";
            int result = session.createQuery(hql)
                    .setParameter("estado", estado)
                    .setParameter("animalId", id)
                    .executeUpdate();

            if (result > 0) {
                transaction.commit();
                System.out.println("Estado del animal actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el animal con ID: " + id);
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene todos los animales perdidos registrados en la base de datos.
     *
     * @return Lista de objetos {@code animal}.
     */
    @Override
    public List<animal> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<animal> animales = null;

        try {
            transaction = session.beginTransaction();
            animales = session.createQuery("from animal", animal.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animales;
    }

    /**
     * Encuentra y devuelve una lista de animales según la especie especificada.
     *
     * @param especie Especie que se desea buscar.
     * @return Lista de objetos {@code animal} que coinciden con la especie.
     */
    @Override
    public List<animal> findByEspecie(String especie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<animal> animales = null;

        try {
            transaction = session.beginTransaction();
            String hql = "from animal a where a.especie = :especie";
            Query<animal> query = session.createQuery(hql, animal.class);
            query.setParameter("especie", especie);
            animales = query.list();

            System.out.print(" | ");
            for (animal a : animales) {
                System.out.print(a.getNombre() + " | ");
            }
            System.out.println();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animales;
    }

    /**
     * Crea un nuevo animal en la base de datos.
     *
     * @param animal El objeto {@code animal} que se desea persistir.
     * @return El objeto {@code animal} creado.
     */
    @Override
    public animal create(animal animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            System.out.println(" --- Animal registrado exitosamente --- ");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animal;
    }

    /**
     * Encuentra un animal por su ID.
     *
     * @param id El ID del animal que se desea buscar.
     * @return El objeto {@code animal} encontrado, o {@code null} si no existe.
     */
    @Override
    public animal findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        animal animal = null;

        try {
            transaction = session.beginTransaction();
            String hql = "FROM animal WHERE id = :id";
            animal = (animal) session.createQuery(hql, animal.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (animal != null) {
                System.out.println("Animal encontrado: " + animal.getNombre());
            } else {
                System.out.println("No se encontró el animal con ID: " + id);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return animal;
    }
}

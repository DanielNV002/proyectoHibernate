package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class animalDAOImpl implements animalDAO {

    @Override
    public boolean updateEstadoById(int id, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Usamos HQL para actualizar solo el estado del animal por su ID
            String hql = "UPDATE animal SET estado = :estado WHERE id = :animalId";
            int result = session.createQuery(hql)
                    .setParameter("estado", estado)
                    .setParameter("animalId", id)
                    .executeUpdate();

            // Si se actualizó al menos un registro, confirmamos
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
                transaction.rollback();  // Deshacer si hay un error
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();  // Cerrar la sesión
        }
    }

    /**
     * Obtiene todos los animales perdidos.
     */
    @Override
    public List<animal> findAll() {
        // Abrir una sesión
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Iniciar una transacción
        Transaction transaction = null;
        List<animal> animales = null;

        try {
            // Comenzar transacción
            transaction = session.beginTransaction();
            // Consulta HQL para obtener todos los animales
            animales = session.createQuery("from animal", animal.class).list();
            // Confirmar la transacción (en este caso no es necesario, ya que solo leemos)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer la transacción en caso de error
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }
        return animales;
    }

    /**
     * Obtiene los animales por especie.
     *
     * @param especie Especie que se va a buscar.
     */
    @Override
    public List<animal> findByEspecie(String especie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<animal> animales = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();
            // Crear consulta HQL para buscar animales por especie
            String hql = "from animal a where a.especie = :especie";
            Query<animal> query = session.createQuery(hql, animal.class);
            query.setParameter("especie", especie);  // Establecer el parámetro de especie
            animales = query.list();  // Obtener la lista de animales que coinciden
            System.out.print(" | ");
            for (animal a : animales) {
                System.out.print(a.getNombre() + " | ");
            }
            System.out.println();
            // Confirmar transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }
        return animales;
    }

    /**
     * Crea un nuevo animal en la base de datos.
     *
     * @param animal El objeto animal que se quiere persistir.
     */
    @Override
    public animal create(animal animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();
            // Guardar el nuevo animal
            session.persist(animal);
            // Confirmar transacción
            transaction.commit();
            System.out.println(" --- Animal registrado exitosamente --- ");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer si hay un error
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }
        return animal;
    }

    @Override
    public Integer findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer animalId = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Usamos HQL para obtener el animal por su ID
            String hql = "FROM Animal WHERE id = :animalId";  // Usar el nombre de la clase 'Animal'
            animal animal = (animal) session.createQuery(hql)
                    .setParameter("animalId", id)  // Pasar el ID del animal
                    .uniqueResult();  // Obtener el resultado único

            // Verificamos si se encontró el animal
            if (animal != null) {
                animalId = animal.getId();  // Obtener el ID del animal
                transaction.commit();
                System.out.println("Animal encontrado: ID = " + animalId);
            } else {
                System.out.println("No se encontró el animal con ID: " + id);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer si hay un error
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }

        return animalId;  // Devolver el ID del animal o null si no se encuentra
    }

}

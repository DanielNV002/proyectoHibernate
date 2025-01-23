package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.example.entities.familiAcogida;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class familiaDAOImpl implements familiaDAO {
    /**
     * @return todas las familias de acogida
     */
    @Override
    public List<familiAcogida> findAll() {
        // Abrir una sesión
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Iniciar una transacción
        Transaction transaction = null;
        List<familiAcogida> familia = null;

        try {
            // Comenzar transacción
            transaction = session.beginTransaction();
            // Consulta HQL para obtener todas las familias
            familia = session.createQuery("from familiAcogida", familiAcogida.class).list();
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
        return familia;
    }

    /**
     * @param familiAcogida
     * @return Crea una nueva familia de acogida
     */
    @Override
    public familiAcogida create(familiAcogida familiAcogida) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();
            // Guardar la nueva familia
            session.persist(familiAcogida);
            // Confirmar transacción
            transaction.commit();
            System.out.println(" --- Familia registrada exitosamente --- ");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer si hay un error
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }
        return familiAcogida;
    }

    /**
     * @param id
     * @param idAnimal
     * @return Modifica el id del animal acogido a la familia
     */
    @Override
    public familiAcogida hacerAdopcion(Integer id, animal idAnimal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        familiAcogida adopcion = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Usamos HQL para actualizar solo el estado del animal por su ID
            String hql = "UPDATE familiAcogida SET idAnimal = :idAnimal WHERE id = :id";
            int result = session.createQuery(hql)
                    .setParameter("idAnimal", idAnimal)
                    .setParameter("id", id)
                    .executeUpdate();
            // Si se actualizó al menos un registro, confirmamos
            if (result > 0) {
                // Obtener el objeto actualizado
                adopcion = session.get(familiAcogida.class, id);
                transaction.commit();
                System.out.println("Adopción realizada correctamente.");
            } else {
                System.out.println("No se encontró la familia de acogida con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer si hay un error
            }
            e.printStackTrace();
        } finally {
            session.close();  // Cerrar la sesión
        }
        return adopcion;  // Devolver el objeto actualizado o null si no se encontró
    }

}

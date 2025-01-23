package org.example.DAO;

import org.example.entities.animal;
import org.example.entities.familiAcogida;

import java.util.List;

public interface familiaDAO {

    /**
     *
     * @return todas las familias de acogida
     */
    List<familiAcogida> findAll();

    /**
     *
     * @param familiAcogida
     * @return Crea una nueva familia de acogida
     */
    // Inserta un nuevo registro
    familiAcogida create(familiAcogida familiAcogida);

    /**
     *
     * @param id
     * @param idAnimal
     * @return  Modifica el id del animal acogido a la familia
     */
    familiAcogida hacerAdopcion(Integer id, animal idAnimal);
}

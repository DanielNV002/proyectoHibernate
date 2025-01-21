package org.example.DAO;

import org.example.entities.animal;

import java.util.List;

/**
 * DAO (Data Access Object)
 *
 * Create / Read / Update / Delete
 */

public interface animalDAO {

    /**
     * @return todos los animales perdidos
     */

    List<animal> findAll();

    //@return devuelve el animal por su id
    animal findById(Integer id);

    //@return devuelve un animal por su especie
    List<animal> findByEspecie(String especie);

    // Inserta un nuevo registro
    animal create(animal animal);

    // Actualizar
    animal update(animal animal);

    /**
     * @param id
     * @return borra un id concreto
     */
    boolean deleteById(Integer id);

}

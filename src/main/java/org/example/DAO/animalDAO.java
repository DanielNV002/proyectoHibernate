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

    //@return devuelve un animal por su especie
    List<animal> findByEspecie(String especie);

    // Inserta un nuevo registro
    animal create(animal animal);

    // Actualizar
    boolean updateEstadoById(int id, String estado);

    // encontrar por ID
    Integer findById(Integer id);
}

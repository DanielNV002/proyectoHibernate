package org.example.DAO;

import org.example.entities.animal;

import java.util.List;

/**
 * Interfaz DAO (Data Access Object) para la entidad {@code animal}.
 * Proporciona los métodos básicos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la base de datos relacionadas con animales.
 */
public interface animalDAO {

    /**
     * Encuentra y devuelve una lista de todos los animales perdidos registrados en la base de datos.
     *
     * @return Lista de objetos {@code animal} que representan los animales perdidos.
     */
    List<animal> findAll();

    /**
     * Encuentra y devuelve una lista de animales según la especie especificada.
     *
     * @param especie La especie del animal que se desea buscar.
     * @return Lista de objetos {@code animal} que coinciden con la especie dada.
     */
    List<animal> findByEspecie(String especie);

    /**
     * Inserta un nuevo registro de animal en la base de datos.
     *
     * @param animal El objeto {@code animal} que se desea insertar.
     * @return El objeto {@code animal} insertado, con su ID actualizado si la operación fue exitosa.
     */
    animal create(animal animal);

    /**
     * Actualiza el estado de un animal en la base de datos identificado por su ID.
     *
     * @param id El ID del animal que se desea actualizar.
     * @param estado El nuevo estado que se desea asignar al animal.
     * @return {@code true} si la operación de actualización fue exitosa, {@code false} en caso contrario.
     */
    boolean updateEstadoById(int id, String estado);

    /**
     * Encuentra y devuelve un animal según su ID.
     *
     * @param id El ID del animal que se desea buscar.
     * @return El objeto {@code animal} que coincide con el ID dado, o {@code null} si no se encuentra.
     */
    animal findById(Integer id);
}
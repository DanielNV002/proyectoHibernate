package org.example.DAO;

import org.example.entities.animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalDAOImplTest {

    private animalDAOImpl animalDAO;

    @BeforeEach
    public void setUp() {
        // Inicializa la clase animalDAO
        animalDAO = new animalDAOImpl();
    }

    @Test
    public void testCreate() {
        animal newAnimal = new animal(null, "Bobby", "Perro", 2, "Perdido", "activo");

        // Crear animal
        animal createdAnimal = animalDAO.create(newAnimal);

        assertNotNull(createdAnimal);
        assertEquals("Bobby", createdAnimal.getNombre());
        assertEquals("Perro", createdAnimal.getEspecie());
    }

    @Test
    public void testFindById() {
        animal newAnimal = new animal(null, "Firulais", "Perro", 3, "Perdido", "activo");

        // Crear animal
        animal createdAnimal = animalDAO.create(newAnimal);

        // Buscar el animal por ID
        animal foundAnimal = animalDAO.findById(createdAnimal.getId());

        assertNotNull(foundAnimal);
        assertEquals("Firulais", foundAnimal.getNombre());
        assertEquals("Perro", foundAnimal.getEspecie());
    }

    @Test
    public void testUpdateEstadoById() {
        animal newAnimal = new animal(null, "Tommy", "Gato", 4, "Perdido", "activo");

        // Crear animal
        animal createdAnimal = animalDAO.create(newAnimal);

        // Actualizar el estado
        boolean isUpdated = animalDAO.updateEstadoById(createdAnimal.getId(), "Adoptado");

        assertTrue(isUpdated);

        // Verificar que el estado se actualizó correctamente
        animal updatedAnimal = animalDAO.findById(createdAnimal.getId());
        assertEquals("Adoptado", updatedAnimal.getEstado());
    }

    @Test
    public void testFindAll() {
        animal newAnimal1 = new animal(null, "Firulais", "Perro", 5, "Perdido", "activo");
        animal newAnimal2 = new animal(null, "Rex", "Perro", 2, "Perdido", "activo");

        // Crear animales
        animalDAO.create(newAnimal1);
        animalDAO.create(newAnimal2);

        // Obtener todos los animales
        List<animal> animals = animalDAO.findAll();

        assertNotNull(animals);
        assertEquals(2, animals.size());
    }

    @Test
    public void testFindByEspecie() {
        animal newAnimal1 = new animal(null, "Firulais", "Perro", 5, "Perdido", "activo");
        animal newAnimal2 = new animal(null, "Rex", "Perro", 2, "Perdido", "activo");

        // Crear animales
        animalDAO.create(newAnimal1);
        animalDAO.create(newAnimal2);

        // Buscar animales por especie
        List<animal> animals = animalDAO.findByEspecie("Perro");

        assertNotNull(animals);
        assertEquals(6, animals.size());
        assertTrue(animals.stream().anyMatch(a -> a.getEspecie().equals("Perro")));
    }
}

package org.example.DAO;

import org.example.DAO.familiaDAOImpl;
import org.example.Util.HibernateUtil;
import org.example.entities.animal;
import org.example.entities.familiAcogida;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class familiaDAOImplTest {

    private static familiaDAOImpl familiaDAO;
    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setUp() {
        // Inicializar el DAO y la SessionFactory
        sessionFactory = HibernateUtil.getSessionFactory();
        familiaDAO = new familiaDAOImpl();
    }

    @BeforeEach
    public void initTestData() {
        // Este método puede usarse para insertar datos en la base de datos antes de cada test
    }

    @Test
    public void testCreate() {
        // Crear una nueva familia de acogida
        familiAcogida familia = new familiAcogida();
        familia.setNombre("Familia García");
        familia.setCiudad("Madrid");
        familia.setEdad(35);

        // Llamar al método create() para agregar la familia
        familiAcogida nuevaFamilia = familiaDAO.create(familia);

        // Verificar que la familia se ha creado correctamente
        assertNotNull(nuevaFamilia.getId(), "El ID no debe ser nulo");
        assertEquals("Familia García", nuevaFamilia.getNombre(), "El nombre de la familia debe coincidir");
    }

    @Test
    public void testFindAll() {
        // Insertar algunas familias de acogida para probar la consulta
        familiAcogida familia1 = new familiAcogida(null,"Familia Pérez", "Barcelona", 40, null);
        familiAcogida familia2 = new familiAcogida(null,"Familia Sánchez", "Valencia", 30, null);
        familiaDAO.create(familia1);
        familiaDAO.create(familia2);

        // Obtener todas las familias
        List<familiAcogida> familias = familiaDAO.findAll();

        // Verificar que las familias están en la base de datos
        assertNotNull(familias, "La lista no debe ser nula");
        assertEquals(2, familias.size(), "Debe haber 2 familias registradas");
    }

    @Test
    public void testHacerAdopcion() {
        // Crear una familia de acogida y un animal
        familiAcogida familia = new familiAcogida(null,"Familia López", "Sevilla", 50, null);
        animal nuevoAnimal = new animal();
        nuevoAnimal.setNombre("Perro Max");
        nuevoAnimal.setEspecie("Perro");
        // Insertar la familia y el animal en la base de datos
        familiaDAO.create(familia);

        // Asignar un animal a la familia
        familiAcogida familiaConAnimal = familiaDAO.hacerAdopcion(familia.getId(), nuevoAnimal);

        // Verificar que la familia tiene el animal asignado
        assertNotNull(familiaConAnimal, "La familia no debe ser nula");
        assertNotNull(familiaConAnimal.getIdAnimal(), "El animal asignado no debe ser nulo");
    }

    @Test
    public void testHacerAdopcionNoExistente() {
        // Intentar asignar un animal a una familia que no existe
        animal nuevoAnimal = new animal();
        nuevoAnimal.setNombre("Perro Max");
        nuevoAnimal.setEspecie("Perro");

        familiAcogida familia = familiaDAO.hacerAdopcion(9999, nuevoAnimal); // ID que no existe

        // Verificar que la familia es nula
        assertNull(familia, "La familia no debe ser encontrada");
    }

    @AfterEach
    public void cleanUp() {
        // Limpiar la base de datos después de cada test (si es necesario)
    }

    @AfterAll
    public static void tearDown() {
        // Cerrar la sesión y limpiar los recursos al final de todas las pruebas
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

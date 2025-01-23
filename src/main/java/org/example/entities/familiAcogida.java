package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Representa una entidad de tipo Familia Acogida que se mapea a la tabla "familiAcogida" en la base de datos.
 * Esta clase contiene información sobre una familia que acoge a un animal, incluyendo su nombre, ciudad, edad
 * y el animal que han acogido.
 *
 * La clase implementa la interfaz {@link Serializable} para permitir su serialización.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-23
 */
@Entity
@Table(name = "familiAcogida") // opcional si se quiere cambiar el nombre de la tabla
public class familiAcogida implements Serializable {

    /** Identificador único de la familia de acogida. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Nombre de la familia o persona que acoge al animal. */
    private String nombre;

    /** Ciudad donde reside la familia de acogida. */
    @Column(nullable = false)
    private String ciudad;

    /** Edad de la persona o familia de acogida. */
    @Column(nullable = false)
    private Integer edad;

    /** Animal acogido por la familia. */
    @OneToOne
    @JoinColumn(name = "id_Animal")
    private animal idAnimal;

    /**
     * Constructor por defecto.
     * Crea una instancia vacía de la clase {@link familiAcogida}.
     */
    public familiAcogida() {}

    /**
     * Constructor con parámetros.
     * Crea una instancia de la clase {@link familiAcogida} con los valores proporcionados.
     *
     * @param id el identificador único de la familia de acogida
     * @param nombre el nombre de la familia o persona que acoge al animal
     * @param ciudad la ciudad donde reside la familia de acogida
     * @param edad la edad de la persona o familia de acogida
     * @param idAnimal el animal que ha sido acogido por la familia
     */
    public familiAcogida(Integer id, String nombre, String ciudad, Integer edad, animal idAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
        this.idAnimal = idAnimal;
    }

    /**
     * Obtiene el identificador único de la familia de acogida.
     *
     * @return el identificador de la familia de acogida
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la familia de acogida.
     *
     * @param id el identificador de la familia de acogida
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la familia de acogida.
     *
     * @return el nombre de la familia de acogida
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la familia de acogida.
     *
     * @param nombre el nombre de la familia de acogida
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ciudad donde reside la familia de acogida.
     *
     * @return la ciudad de la familia de acogida
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad donde reside la familia de acogida.
     *
     * @param ciudad la ciudad de la familia de acogida
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la edad de la familia de acogida.
     *
     * @return la edad de la familia de acogida
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la familia de acogida.
     *
     * @param edad la edad de la familia de acogida
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el animal acogido por la familia.
     *
     * @return el animal acogido
     */
    public animal getIdAnimal() {
        return idAnimal;
    }

    /**
     * Establece el animal acogido por la familia.
     *
     * @param idAnimal el animal acogido
     */
    public void setIdAnimal(animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    /**
     * Devuelve una representación en formato de tabla del objeto {@link familiAcogida}, con un encabezado y una fila de datos.
     *
     * @return una cadena con los detalles de la familia de acogida en formato tabular
     */
    @Override
    public String toString() {

        // Encabezados de la tabla
        String header = String.format("%-20s %-20s %-10s %-10s\n", "Nombre", "Ciudad", "Edad", "ID Animal")
                + ("══════════════════════════════════════════════════════════════════\n");
        String row;

        if(idAnimal == null){
            // Datos de la fila
            row = String.format("%-20s %-20s %-10d %-10s\n", nombre, ciudad, edad, null)
                    + ("══════════════════════════════════════════════════════════════════\n");

            // Unimos encabezado y fila
        }else{
            // Datos de la fila
            row = String.format("%-20s %-20s %-10d %-10d\n", nombre, ciudad, edad, idAnimal.getId())
                    + ("══════════════════════════════════════════════════════════════════\n");

            // Unimos encabezado y fila
        }
        return header + row;
    }
}

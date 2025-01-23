package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Representa una entidad de tipo Animal que se mapea a la tabla "animal" en la base de datos.
 * Esta clase contiene información sobre un animal, incluyendo su nombre, especie, edad,
 * descripción de la pérdida y estado.
 * <p>
 * La clase implementa la interfaz {@link Serializable} para permitir su serialización.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-23
 */
@Entity
@Table(name = "animal") // opcional si se quiere cambiar el nombre de la tabla
public class animal implements Serializable {

    /**
     * Identificador único del animal.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del animal.
     */
    private String nombre;

    /**
     * Especie del animal.
     */
    @Column(nullable = false)
    private String especie;

    /**
     * Edad del animal.
     */
    @Column(nullable = false)
    private Integer edad;

    /**
     * Descripción de la pérdida del animal.
     */
    private String descripcionPerdida;

    /**
     * Estado del animal (por ejemplo, perdido, encontrado, etc.).
     */
    private String estado;

    /**
     * Constructor por defecto.
     * Crea una instancia vacía de la clase {@link animal}.
     */
    public animal() {
    }

    /**
     * Constructor con parámetros.
     * Crea una instancia de la clase {@link animal} con los valores proporcionados.
     *
     * @param id                 el identificador único del animal
     * @param nombre             el nombre del animal
     * @param especie            la especie del animal
     * @param edad               la edad del animal
     * @param descripcionPerdida la descripción de la pérdida del animal
     * @param estado             el estado del animal
     */
    public animal(Integer id, String nombre, String especie, Integer edad, String descripcionPerdida, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcionPerdida = descripcionPerdida;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador único del animal.
     *
     * @return el identificador del animal
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del animal.
     *
     * @param id el identificador del animal
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del animal.
     *
     * @return el nombre del animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del animal.
     *
     * @param nombre el nombre del animal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especie del animal.
     *
     * @return la especie del animal
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie del animal.
     *
     * @param especie la especie del animal
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene la edad del animal.
     *
     * @return la edad del animal
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Establece la edad del animal.
     *
     * @param edad la edad del animal
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la descripción de la pérdida del animal.
     *
     * @return la descripción de la pérdida
     */
    public String getDescripcionPerdida() {
        return descripcionPerdida;
    }

    /**
     * Establece la descripción de la pérdida del animal.
     *
     * @param descripcionPerdida la descripción de la pérdida
     */
    public void setDescripcionPerdida(String descripcionPerdida) {
        this.descripcionPerdida = descripcionPerdida;
    }

    /**
     * Obtiene el estado del animal.
     *
     * @return el estado del animal
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del animal.
     *
     * @param estado el estado del animal
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación en formato de cadena del objeto {@link animal}.
     *
     * @return una cadena con los detalles del animal
     */
    @Override
    public String toString() {
        return "animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", descripcionPerdida='" + descripcionPerdida + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

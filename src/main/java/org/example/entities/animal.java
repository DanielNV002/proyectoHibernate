package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "animal") // optativo si queremos cambiar el nombre de la tabla
public class animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private Integer edad;

    private String descripcionPerdida;

    //constructores
    public animal(){};

    public animal(Integer id, String nombre, String especie, Integer edad, String descripcionPerdida) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcionPerdida = descripcionPerdida;
    }

    //getter y setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getdescripcionPerdida() {
        return descripcionPerdida;
    }

    public void setdescripcionPerdida(String descripcionPerdida) {
        this.descripcionPerdida = descripcionPerdida;
    }

    //toString

    @Override
    public String toString() {
        return "animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", descripcionPerdida='" + descripcionPerdida + '\'' +
                '}';
    }
}

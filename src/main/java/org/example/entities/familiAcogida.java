package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "familiAcogida") // optativo si queremos cambiar el nombre de la tabla
public class familiAcogida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private Integer edad;

    @OneToOne
    @JoinColumn(name = "id_Animal")
    private animal idAnimal;

    //constructores
    public familiAcogida(){};

    public familiAcogida(Integer id, String nombre, String ciudad, Integer edad, animal idAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
        this.idAnimal = idAnimal;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    //toString
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

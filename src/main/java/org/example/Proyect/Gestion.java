package org.example.Proyect;

import org.example.entities.animal;

import java.util.Scanner;

public class Gestion {

    //Metodo para registrar nuevo animal
    void registro(){

        Scanner in = new Scanner(System.in);
        animal A = new animal();

        System.out.println("Introduzca los datos para el registro");
        System.out.println("Nombre: ");
        A.setNombre(in.nextLine());
        System.out.println("Especie: ");
        A.setEspecie(in.nextLine());
        System.out.println("Edad: ");
        A.setEdad(in.nextInt());
        System.out.println("Descripcion de como se perdio: ");
        A.setdescripcionPerdida(in.nextLine());

        A = new animal(null, A.getNombre(), A.getEspecie(), A.getEdad(), A.getdescripcionPerdida());


    }
}

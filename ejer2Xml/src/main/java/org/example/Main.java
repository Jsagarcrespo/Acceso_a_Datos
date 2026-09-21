package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {


    // SOLUCIONES SI NO COGE EL XSTREAM MIRAR QUE POM.XML ESTE DENTRO DE DEPENDENCIES, LUEGO HACER QUE CORRA TODO EL PROYECTO
    // SI NO SE SOLUCIONA, IR A VIEW --> MAVEN --> RELOAD ALL MAVEN PROJECTS

    public static void main(String[] args) {

        try {

            FileOutputStream fileout = new FileOutputStream("FichPersona.dat");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

            // Creamos algunas personas
            Persona persona1 = new Persona("Ana", 14);
            Persona persona2 = new Persona("Pedro", 15);
            Persona persona3 = new Persona("Juan", 18);
            Persona persona4 = new Persona("Maria", 20);

            // Guardamos las personas en el fichero
            dataOS.writeObject(persona1);
            dataOS.writeObject(persona2);
            dataOS.writeObject(persona3);
            dataOS.writeObject(persona4);

            dataOS.close();

            System.out.println("FichPersona.dat creado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

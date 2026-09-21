package org.example;

import com.thoughtworks.xstream.XStream;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EscribirPersonas {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File fichero = new File("FichPersona.dat");

        FileInputStream filein = new FileInputStream(fichero);

        ObjectInputStream dataIS = new ObjectInputStream(filein);

        System.out.println("Comienza el proceso de creación del fichero a XML...");

        // Creamos un objeto ListaPersona
        ListaPersona ListaPersona = new ListaPersona();

        try {

            // Leemos todas las personas del fichero
            while (true) {

                Persona persona = (Persona) dataIS.readObject();

                ListaPersona.add(persona);
            }

        } catch (EOFException e) {
            // Hemos llegado al final del fichero
        }

        dataIS.close();

        try {

            // Creamos el objeto XStream
            XStream xstream = new XStream();

            // Cambiamos los nombres de las etiquetas XML
            xstream.alias("ListaPersonaMunicipio", ListaPersona.class);
            xstream.alias("DatosPersona", Persona.class);

            // Eliminamos la etiqueta de la lista
            xstream.addImplicitCollection(ListaPersona.class, "lista");

            // Generamos el fichero XML
            xstream.toXML(
                    ListaPersona,
                    new FileOutputStream("Personas.xml")
            );

            System.out.println("Creado fichero XML....");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    

}

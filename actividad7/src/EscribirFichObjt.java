
import java.io.*;

public class EscribirFichObjt {

    public static void main(String[] args) throws IOException {

        File fichero = new File("FichPersona.dat");

        // Flujo de salida
        FileOutputStream fileout = new FileOutputStream(fichero);

        // Flujo para escribir objetos
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        // Creamos objetos Persona
        Persona p1 = new Persona("Ana", 14);
        Persona p2 = new Persona("Luis", 15);
        Persona p3 = new Persona("Alicia", 13);
        Persona p4 = new Persona("Cabezon", 15);

        // Guardamos los objetos en el fichero
        dataOS.writeObject(p1);
        dataOS.writeObject(p2);
        dataOS.writeObject(p3);
        dataOS.writeObject(p4);

        // Cerramos el flujo
        dataOS.close();
    }
}

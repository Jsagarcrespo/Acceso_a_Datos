import java.io.*;


public class LeerFichObject {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File fichero = new File("FichPersona.dat");

        // Flujo de entrada
        FileInputStream filein = new FileInputStream(fichero);

        // Flujo para leer objetos
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {

            while (true) {

                // Leemos un objeto
                Persona p = (Persona) dataIS.readObject();

                // Mostramos sus datos
                System.out.println("Nombre: " + p.getNom());
                System.out.println("Edad: " + p.getEdad());
                System.out.println("-------------------");
            }

        } catch (EOFException e) {
            // Hemos llegado al final del fichero
        }

        dataIS.close();
    }
}

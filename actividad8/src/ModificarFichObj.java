import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ModificarFichObj {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);

        // ArrayList para guardar las personas
        ArrayList<Persona> personas = new ArrayList<>();

        File fichero = new File("FichPersona.dat");

        // -------------------------
        // 1. LEER EL FICHERO
        // -------------------------

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Persona p = (Persona) dataIS.readObject();
                personas.add(p);
            }
        } catch (EOFException e) {
            // Hemos llegado al final del fichero
        }

        dataIS.close();

        // -------------------------
        // 2. PEDIR DATOS AL USUARIO
        // -------------------------

        System.out.print("¿Que usuario quieres cambiar? ");
        int registro = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nueva edad: ");
        int nuevaEdad = sc.nextInt();

        // -------------------------
        // 3. MODIFICAR PERSONA
        // -------------------------

        if (registro >= 1 && registro <= personas.size()) {

            Persona persona = personas.get(registro - 1);

            persona.setNom(nuevoNombre);
            persona.setEdad(nuevaEdad);

            System.out.println("Usuario modificado correctamente.");

        } else {
            System.out.println("El usuario no existe.");
        }

        // -------------------------
        // 4. VOLVER A ESCRIBIR
        // -------------------------

        FileOutputStream fileout = new FileOutputStream(fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        for (Persona p : personas) {
            dataOS.writeObject(p);
        }

        dataOS.close();
        sc.close();
    }
}


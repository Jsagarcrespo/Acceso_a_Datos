import java.io.*;

void main() throws IOException {
    File fichero = new File("C:\\Users\\149FA04\\Desktop\\Acceso_a_Datos\\actividad5\\FichData.dat");

    FileInputStream filein = new FileInputStream(fichero);

    DataInputStream dataIS = new DataInputStream(filein);

    try {
        while (true) {

            String nombre = dataIS.readUTF();
            int edad = dataIS.readInt();

            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("-------------------");
        }

    } catch (EOFException e) {
        System.out.println("No se encuentra el fichero");
    }

    dataIS.close();
}



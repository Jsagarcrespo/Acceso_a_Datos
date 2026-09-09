import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actividad3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Archivos en el directorio actual:");
        File f = new File(".");
        String[] archivos = f.list();
        for (int i = 0; i < archivos.length; i++)
        {
            System.out.println(archivos[i]);
        }


        System.out.printf("Nombre: " + f.getName());
        System.out.println("Ruta: " + f.getPath());
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());
        System.out.println("Tamaño: " + f.length() + " bytes");
        System.out.println("¿Lectura?: " + f.canRead());
        System.out.println("¿Escritura?: " + f.canWrite());
        System.out.println("¿Es un directorio?: " + f.isDirectory());
        System.out.println("¿Es un fichero?: " + f.isFile());
        System.out.println("Esciribe el nombre del fichero:");


        File direc = new File("sagar");

        if (direc.mkdir()){
            System.out.println("Directorio creado correctamente");
        } else {
            System.out.println("No se ha podido crear");
        }

       File fichero1 = new File(direc, "f1.txt");
       File fichero2 = new File(direc, "f2.txt");

       if (fichero1.createNewFile()){
           System.out.println("fichero 1 creado");
       }

       if (fichero2.createNewFile()){
           System.out.println("fichero 2 creado");
       }


       if (fichero1.delete()){
           System.out.printf("fichero 1 eliminado");
       }

       if (fichero2.delete()){
           System.out.printf("fichero 2 eliminado");
       }

       if (direc.delete()){
           System.out.println("Bye bye");
       }

    }



}
import java.io.RandomAccessFile;
import java.io.IOException;

public class EscribirFichAleatorio {

    public static void main(String[] args) {

        // Arrays con los datos de los empleados
        String[] apellidos = {
                "Errasti",
                "Gorosabel",
                "Ugarte",
                "Etxeberria",
                "Agirre"
        };

        int[] departamentos = {
                10,
                20,
                10,
                30,
                20
        };

        double[] salarios = {
                20000,
                40000,
                35000,
                45000,
                30000
        };

        // Fichero aleatorio
        String nombreFichero = "empleados.dat";

        try (RandomAccessFile fichero =
                     new RandomAccessFile(nombreFichero, "rw")) {

            // Escribimos los empleados de forma secuencial
            for (int i = 0; i < apellidos.length; i++) {

                // El identificador es índice + 1
                int id = i + 1;

                fichero.writeInt(id);

                // El apellido debe ocupar exactamente 10 caracteres
                String apellido = apellidos[i];

                // Rellenamos con espacios si tiene menos de 10 caracteres
                StringBuilder apellidoCompleto =
                        new StringBuilder(apellido);

                while (apellidoCompleto.length() < 10) {
                    apellidoCompleto.append(" ");
                }

                // Si tuviera más de 10 caracteres, nos quedamos con los 10 primeros
                if (apellidoCompleto.length() > 10) {
                    apellidoCompleto.setLength(10);
                }

                // Escribimos los 10 caracteres
                fichero.writeChars(apellidoCompleto.toString());

                // Departamento
                fichero.writeInt(departamentos[i]);

                // Salario
                fichero.writeDouble(salarios[i]);
            }

            System.out.println("Fichero creado correctamente.");
            System.out.println("Número de empleados: " + apellidos.length);
            System.out.println("Tamaño del fichero: " + fichero.length() + " bytes");

        } catch (IOException e) {
            System.out.println("Error al trabajar con el fichero: "
                    + e.getMessage());
        }
    }
}




import java.io.RandomAccessFile;
import java.io.IOException;

public class LeerFichAleatorio {

    public static void main(String[] args) {

        String nombreFichero = "C:\\Users\\149FA04\\Desktop\\Acceso_a_Datos\\empleados.dat";
        final int TAM_REGISTRO = 36;

        try (RandomAccessFile fichero =
                     new RandomAccessFile(nombreFichero, "r")) {

            // Si se pasa un identificador como argumento
            if (args.length == 1) {

                int idBuscado = Integer.parseInt(args[0]);
                boolean encontrado = false;

                // Recorremos los registros
                for (long posicion = 0;
                     posicion < fichero.length();
                     posicion += TAM_REGISTRO) {

                    // Nos posicionamos al principio del registro
                    fichero.seek(posicion);

                    int id = fichero.readInt();

                    // Leemos los 10 caracteres del apellido
                    StringBuilder apellido = new StringBuilder();

                    for (int i = 0; i < 10; i++) {
                        apellido.append(fichero.readChar());
                    }

                    int departamento = fichero.readInt();
                    double salario = fichero.readDouble();

                    // Comprobamos si es el empleado buscado
                    if (id == idBuscado) {

                        System.out.println("ID: " + id);
                        System.out.println("Apellido: " + apellido.toString().trim());
                        System.out.println("Departamento: " + departamento);
                        System.out.println("Salario: " + salario);

                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println(
                            "No existe ningún empleado con el ID " + idBuscado
                    );
                }

            } else {

                // No se ha pasado ningún argumento:
                // mostramos todos los empleados

                for (long posicion = 0;
                     posicion < fichero.length();
                     posicion += TAM_REGISTRO) {

                    // Nos posicionamos al principio del registro
                    fichero.seek(posicion);

                    int id = fichero.readInt();

                    // Leemos los 10 caracteres del apellido
                    StringBuilder apellido = new StringBuilder();

                    for (int i = 0; i < 10; i++) {
                        apellido.append(fichero.readChar());
                    }

                    int departamento = fichero.readInt();
                    double salario = fichero.readDouble();

                    System.out.println("ID: " + id);
                    System.out.println("Apellido: " + apellido.toString().trim());
                    System.out.println("Departamento: " + departamento);
                    System.out.println("Salario: " + salario);
                    System.out.println("-------------------------");
                }
            }

        } catch (IOException e) {

            System.out.println("Error al leer el fichero: "
                    + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "El identificador debe ser un número entero."
            );
        }
    }
}

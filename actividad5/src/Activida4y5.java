import java.io.*;

void main() throws IOException {

    try {
        File direc = new File("sagar");

        if (direc.mkdir()) {
            System.out.println("Directorio creado correctamente");
        } else {
            System.out.println("No se ha podido crear");
        }

        File fichero1 = new File(direc, "f1.txt");


        if (fichero1.createNewFile()) {
            System.out.println("fichero 1 creado");
        }

        FileWriter fw = new FileWriter(fichero1);

        // ejercicio 4
        /*
        String texto = "Que es lo que se supone que ponga aqui";

        char[] caracteres = texto.toCharArray();

        for (int i = 0; i < caracteres.length; i++) {
            fw.write(caracteres[i]);
        }
        */

        // ejercicio 5
        String prov[] = {"gipuzkoa", "bizkaia", "araba"};

        for (int i = 0; i < prov.length; i++) {
            fw.write(prov[i]);
        }
        fw.close();

        // ejercicio 5.1
        BufferedWriter fichero = new BufferedWriter(
                new FileWriter(fichero1, true)
        );

        for (int i = 1; i < 11; i++) {
            fichero.write("Fila numero: " + i);
            fichero.newLine();
        }

        fichero.close();

    }catch (FileNotFoundException fn ){
        System.out.println("No se encuentra el fichero");
    } catch (IOException e){
        System.out.println("Ha ocurrido un error: " + e.getMessage());
    }


}

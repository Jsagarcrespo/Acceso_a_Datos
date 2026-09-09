import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("fichero.txt");
            BufferedReader br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null)
                System.out.println(linea);

            br.close();
        }
        catch (FileNotFoundException fn) {
            System.out.println("Error de lectura");
        }

        catch (IOException io) {
            System.out.println("Error de E/S");
        }
    }
}

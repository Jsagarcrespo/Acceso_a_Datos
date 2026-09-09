import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        File fichero = new File("fichero.txt");

        FileReader fic = new FileReader(fichero);

        char[] caracteres = new char[20];

        int i;
       /* while ((i = fic.read()) != -1) {
            System.out.print((char) i);
        }*/

        while ((i = fic.read(caracteres)) != -1) {
            System.out.print(new String(caracteres, 0, i));
        }

        fic.close();
    }
}

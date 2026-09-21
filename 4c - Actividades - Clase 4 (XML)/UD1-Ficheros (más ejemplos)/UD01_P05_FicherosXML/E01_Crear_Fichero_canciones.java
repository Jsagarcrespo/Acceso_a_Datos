package UD01_P05_FicherosXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class E01_Crear_Fichero_canciones {

	public static void main(String[] args) throws IOException {

		File f = new File("src\\P05_FicherosXML\\ficheros\\canciones.dat");
		f.createNewFile();
		
		FileOutputStream fileOut = new FileOutputStream(f);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

		Cancion canciones[] = new Cancion[5];
		
		canciones[0] = new Cancion(1, 2002, "Plug n baby", "Muse", "2:40", false);
		canciones[1] = new Cancion(2, 2013, "R u mine", "Arctic Monkeys", "3:10", false);
		canciones[2] = new Cancion(3, 2014, "Loose Sutures", "Fuzz", "4:30", false);
		canciones[3] = new Cancion(4, 2018, "Total Football", "Parquet Courts", "3:00", false);
		canciones[4] = new Cancion(5, 1973, "Entre dos aguas", "Paco de lucia", "6:00", true);
		
		for(int i = 0; i < canciones.length; i++)
		{
			objectOut.writeObject(canciones[i]);
		}
		
		objectOut.close();

	}

}

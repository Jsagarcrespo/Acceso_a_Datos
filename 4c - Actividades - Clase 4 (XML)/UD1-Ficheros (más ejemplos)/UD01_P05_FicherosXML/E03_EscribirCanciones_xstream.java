/**
 * Clase que escribe en un fichero XML la lista de canciones
 */

package UD01_P05_FicherosXML;

import java.io.*;
import com.thoughtworks.xstream.XStream;

public class E03_EscribirCanciones_xstream {

	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{

		File f = new File("src\\P05_FicherosXML\\ficheros\\canciones.dat");
		
		//flujo de entrada
		FileInputStream fileIn = new FileInputStream(f);
		
		//conecta el flujo de bytes al flujo de objetos
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		
		System.out.println
        ("Comienza el proceso de creacion del fichero a XML ...");
		
		//creamos un objeto de lista de canciones
		ListaCanciones lista_canciones = new ListaCanciones();
		
		try
		{
			while(true)
			{
				//leer una cancion
				Cancion cancion = (Cancion) objectIn.readObject();
				
				//a�adir cancion a la lista
				lista_canciones.add(cancion);
			}
		}
		catch(EOFException eo) {}
		
		//cerramos flujo del fichero de objetos
		objectIn.close();
		
		try
		{
			XStream xstream = new XStream();
			
			//cambiar nombre de la etiqueta XML
			xstream.alias("ListaCancionesXS", ListaCanciones.class);
			xstream.alias("DatosCancion", Cancion.class);
			
			//cambiar nombre de los atributos de la etiqueta cancion
			//si no se cambia, sxtream introduce el nombre 
			//de la variable de la clase
			xstream.aliasField("Id_cancion", Cancion.class, "id");
			xstream.aliasField("A�o", Cancion.class, "ano");
			
			//quitar etiqueta lista (atributo de la claseListaPersonas)
			xstream.addImplicitCollection(ListaCanciones.class, "lista");
			
			//insertar los objetos en el xml
			xstream.toXML(lista_canciones, 
			new FileOutputStream("src\\P05_FicherosXML\\ficheros\\canciones_xs.xml"));
			
			System.out.println("Creado fichero XML....");
		}
		catch(Exception e) {e.printStackTrace();}

	}

}

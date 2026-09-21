/**
 * Clase que lee con Xstream un fichero XML de canciones
 */

package UD01_P05_FicherosXML;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.thoughtworks.xstream.XStream;

public class E03_LeerCanciones_xstream {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		XStream xstream = new XStream();
		
		xstream.alias("ListaCancionesXS", ListaCanciones.class);
		xstream.alias("DatosCancion", Cancion.class);
		xstream.addImplicitCollection(ListaCanciones.class, "lista");
		
		//leemos el fichero xml
		ListaCanciones lista_canciones = (ListaCanciones) xstream.fromXML(
		new FileInputStream("src\\UD01_P05_FicherosXML\\ficheros\\canciones_xs.xml"));
		
		//sacar por pantalla el n�mero de canciones que hay en el fichero xml
		System.out.println(
		"Numero de Canciones: "+lista_canciones.getListaCanciones().size());
		
		//creamos lista de canciones y metemos dentro todas las canciones
		List<Cancion> lista = new ArrayList<Cancion>();
		lista = lista_canciones.getListaCanciones();
		
		Iterator<Cancion> iterador = lista.listIterator();
		
		//while para recorrer y mostrar la lista de canciones
		while(iterador.hasNext())
		{
			Cancion p = (Cancion) iterador.next();
			System.out.println("Id: " + p.getId() +" - A�o: " + p.getAno());
		}
		
		System.out.println("Fin de listado .....");

	}

}

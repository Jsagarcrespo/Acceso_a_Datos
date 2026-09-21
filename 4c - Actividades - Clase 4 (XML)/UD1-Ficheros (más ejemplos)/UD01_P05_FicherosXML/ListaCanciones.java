/**
 * Clase que contiene una lista de canciones
 */

package UD01_P05_FicherosXML;

import java.util.ArrayList;
import java.util.List;

public class ListaCanciones {
	
	private List<Cancion> lista = new ArrayList<Cancion>();
	
	public ListaCanciones()
	{
		
	}
	
	public void add(Cancion p)
	{
		lista.add(p);
	}
	
	public List<Cancion> getListaCanciones()
	{
		return lista;
	}

}

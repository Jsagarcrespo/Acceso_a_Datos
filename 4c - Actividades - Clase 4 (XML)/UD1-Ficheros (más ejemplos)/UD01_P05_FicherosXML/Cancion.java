package UD01_P05_FicherosXML;

import java.io.Serializable;

public class Cancion implements Serializable{
	
	private int id; // 4B
	private int ano; // 4B
	private String titulo; //20 char -> 2B * 20 = 40 B
	private String artista; //20 char -> 2B * 20 = 40 B
	private String duracion; //20 char -> 2B * 20 = 40 B
	private boolean cancion_espanola; // 1B
	
	// TAMA�O TOTAL CANCION = 129 Bytes;
	
	Cancion(int vid, int vano, String vtitulo, String vartista, String vduracion, boolean vcancion_espanola)
	{
		id = vid;
		ano = vano;
		titulo = vtitulo;
		artista = vartista;
		duracion = vduracion;
		cancion_espanola = vcancion_espanola;
	}
	
	Cancion()
	{
		id = 0;
		ano = 0;
		titulo = "";
		artista = "";
		duracion = "";
		cancion_espanola = false;
	}
	
	//GETTERS Y SETTERS -
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public boolean isCancion_espanola() {
		return cancion_espanola;
	}

	public void setCancion_espanola(boolean cancion_espanola) {
		this.cancion_espanola = cancion_espanola;
	}
	
	//
	
	/**
	 * Metodo que devuelve un String con los datos de una cancion
	 * @return String con los datos de la canci�n
	 */
	public String mostrarDatosCancion()
	{
		return "" + getId() + " - " + getAno() + " - " 
	+ acortarString(getTitulo()) + " - " + 
				acortarString(getArtista()) + " - " 
	+ acortarString(getDuracion()) + " - " + isCancion_espanola() + "";
	}
	
	
	/**
	 * Metodo que elimina todos los espacios en blanco que deja el buffer
	 * al final de una cadena de caracteres
	 * @param cadena Cadena de caracteres a acortar
	 * @return Cadena de caracteres acortada
	 */
	public String acortarString(String cadena) 
	{
		char[] car;
		String str = "";
		car = cadena.toCharArray();
		
		for(int i = cadena.length()-1; i>=0; i--)
		{
			if(Character.isLetterOrDigit((car[i])))
			{
				for(int x = 0; x < i+1; x++)
				{
					str = str + car[x];
				}break;
			}
		}
		
		return str;
		
	}

}

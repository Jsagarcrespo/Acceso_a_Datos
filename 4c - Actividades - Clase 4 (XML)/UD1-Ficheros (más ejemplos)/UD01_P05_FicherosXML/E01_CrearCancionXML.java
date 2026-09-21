package UD01_P05_FicherosXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class E01_CrearCancionXML {

	public static void main(String[] args) throws IOException {

	//creamos variable fichero y flujo de lectura de fichero de objetos
	
	File f = new File("src\\P05_FicherosXML\\ficheros\\canciones.dat");
	
	FileInputStream fileIn = new FileInputStream(f);
	ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	
	
	//creamos variables 

	Cancion cancion = new Cancion();
	
	
	//instancia para construir el parser
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	
	try
	{
		
		//creamos varias objetos para crear finalmente el documento xml
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		DOMImplementation implementation = builder.getDOMImplementation();
		
		Document document = implementation.createDocument(null,"Canciones",null);
			document.setXmlVersion("1.0");
			
			
		for(int i = 0; i < 5; i++)
		{
			
			cancion = (Cancion) objectIn.readObject();
			
			//creamos nodo raiz cancion
			Element raiz = document.createElement("Cancion");
			
			//a�adimos nodo principal 
			document.getDocumentElement().appendChild(raiz);
			
			//
			
			//creamos elemento id
			crearElemento("Id",Integer.toString(cancion.getId()),raiz,document);
			
			//creamos elemento a�o
			crearElemento("Ano",Integer.toString(cancion.getAno()),raiz,document);
			
			//creamos elemento titulo
			crearElemento("Titulo", cancion.getTitulo(), raiz, document);
			
			//creamos elemento artista
			crearElemento("Artista", cancion.getArtista(), raiz, document);
			
			//creamos elemento duracion
			crearElemento("Duracion", cancion.getDuracion(), raiz, document);
			
			//creamos elemento es cancion espa�ola?
			crearElemento("Espanola", 
						  Boolean.toString(cancion.isCancion_espanola()), 
					      raiz, document);
			
		}
		
		Source source = new DOMSource(document);
		
		Result result = new StreamResult(
			new File("src\\P05_FicherosXML\\ficheros\\canciones.xml"));
		
		Transformer transformer = 
			TransformerFactory.newInstance().newTransformer();
		
		//se transforma el documento al fichero
		transformer.transform(source, result);
		
		
		//MOSTRAR EL DOCUMENTO POR CONSOLA
		Result console = new StreamResult(System.out);
		transformer.transform(source, console);
		
	}
	catch (Exception e)
	{
		System.out.println("Ha habido un error: " + e);
	}
	
	objectIn.close();

}
	
	/**
	 * Metodo que crea un elemento para escribirlo en documento xml
	 * @param datoCancion Elemento de la canci�n
	 * @param valor Valor que se va a dar al elemento
	 * @param raiz Elemento raiz
	 * @param document Documento donde se van a a�adir estos elementos
	 */
	static void crearElemento(String datoCancion, String valor, Element raiz,
							  Document document)
	{
		
		Element elemento = document.createElement(datoCancion);
		Text texto = document.createTextNode(valor); //damos valor
		
		raiz.appendChild(elemento); //pegamos el elemento hijo a la raiz
		elemento.appendChild(texto); //pegamos el valor al elemento hijo
		
	}

}

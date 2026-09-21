package UD01_P05_FicherosXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class E04_Convertir_XML_HTML {

	public static void main(String[] args) throws IOException 
	{
		
	  String hojaEstilo = "src\\P05_FicherosXML\\ficheros\\plantilla.xsl";
	  String datosCanciones = "src\\P05_FicherosXML\\ficheros\\canciones.xml";
	  
	  File pagina_html = new File("src\\P05_FicherosXML\\ficheros\\canciones.html");
	  FileOutputStream output = new FileOutputStream(pagina_html); //crear fichero HTML
	  
	  Source estilos =new StreamSource(hojaEstilo); //fuente XSL
	  Source datos =new StreamSource(datosCanciones); //fuente XML
	  Result result = new StreamResult(output); //resultado de la transformaci�n
	  
	  try
	  {     
	   Transformer transformer =  
	               TransformerFactory.newInstance().newTransformer(estilos); 
	   
	   transformer.transform(datos, result);	//obtiene el HTML
	  }
	  catch(Exception e){System.err.println("Error: "+e);}
	  
	  output.close();  //cerrar fichero 	
		 

	}

}

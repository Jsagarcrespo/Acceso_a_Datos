package org.example;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ListarPeli {

    JPanel ListarPeli;
    private JList<Pelicula> listPeli;
    private JButton bXML;

    public ListarPeli(List<Pelicula> peliculas) {

        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        for (Pelicula peli : peliculas) {
            peliculaDefaultListModel.addElement(peli);
        }

        listPeli.setModel(peliculaDefaultListModel);

        bXML.addActionListener(e -> {

            try {

                XStream xstream = new XStream(new StaxDriver());

                // Nombre que tendrá la lista en el XML
                xstream.alias("peliculas", List.class);

                // Nombre que tendrá cada película
                xstream.alias("pelicula", Pelicula.class);

                // Creamos el fichero
                FileOutputStream fileout =
                        new FileOutputStream("FicheroPelicula.xml");

                // Writer para escribir el XML
                Writer writer =
                        new OutputStreamWriter(
                                fileout,
                                StandardCharsets.UTF_8
                        );

                // PrettyPrintWriter permite escribir el XML
                // con saltos de línea e indentación
                PrettyPrintWriter prettyWriter =
                        new PrettyPrintWriter(writer);

                // Convertimos la lista a XML
                xstream.marshal(peliculas, prettyWriter);

                // Cerramos el writer
                prettyWriter.close();

                JOptionPane.showMessageDialog(
                        null,
                        "XML generado correctamente"
                );

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Error al generar el XML: " + ex.getMessage()
                );
            }
        });
    }
}
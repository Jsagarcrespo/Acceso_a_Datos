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
import java.util.ArrayList;
import java.util.List;

public class ListarPeli {

    JPanel ListarPeli;
    private JList<Pelicula> listPeli;
    private JButton bXML;

    public ListarPeli(List<Pelicula> peliculas, Usuario usuarioLogueado) {

        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        List<Pelicula> peliculasUsuario = new ArrayList<>();

        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId() == usuarioLogueado.getId()) {

                peliculasUsuario.add(peli);
                peliculaDefaultListModel.addElement(peli);
            }
        }

        listPeli.setModel(peliculaDefaultListModel);

        bXML.addActionListener(e -> {

            try {

                XStream xstream = new XStream(new StaxDriver());

                xstream.alias("peliculas", List.class);
                xstream.alias("pelicula", Pelicula.class);

                FileOutputStream fileout =
                        new FileOutputStream("FicheroPelicula.xml");

                Writer writer =
                        new OutputStreamWriter(
                                fileout,
                                StandardCharsets.UTF_8
                        );

                PrettyPrintWriter prettyWriter =
                        new PrettyPrintWriter(writer);

                xstream.marshal(peliculasUsuario, prettyWriter);

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

package org.example.listar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.example.models.Pelicula;
import org.example.models.Usuario;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ListarPeli {

    public JPanel ListarPeli;
    private JList<Pelicula> listPeli;
    private JButton bXML;

    public ListarPeli(
            List<Pelicula> peliculas,
            Usuario usuarioLogueado) {

        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        List<Pelicula> peliculasUsuario = new ArrayList<>();

        // Mostramos únicamente las películas del usuario conectado
        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId()
                    == usuarioLogueado.getId()) {

                peliculasUsuario.add(peli);
                peliculaDefaultListModel.addElement(peli);
            }
        }

        listPeli.setModel(peliculaDefaultListModel);

        // Generamos el XML al pulsar el botón
        bXML.addActionListener(e -> {

            try {

                // Configuramos XStream
                XStream xstream = new XStream(new StaxDriver());

                // Nombres de las etiquetas
                xstream.alias("peliculas", List.class);
                xstream.alias("pelicula", Pelicula.class);

                // Permitimos los tipos necesarios
                xstream.allowTypes(new Class[]{
                        ArrayList.class,
                        Pelicula.class
                });

                // No incluimos el usuario ni la valoración
                xstream.omitField(Pelicula.class, "usuario");
                xstream.omitField(Pelicula.class, "valoracion");

                // Creamos el directorio si no existe
                File directorio = new File("ficherosXML");

                if (!directorio.exists() && !directorio.mkdirs()) {
                    throw new IOException(
                            "No se pudo crear el directorio ficherosXML"
                    );
                }

                // Creamos el archivo XML
                File archivoXML = new File(
                        directorio,
                        "FicheroPelicula.xml"
                );

                // Escribimos en UTF-8
                try (Writer writer = new OutputStreamWriter(
                        new FileOutputStream(archivoXML),
                        StandardCharsets.UTF_8)) {

                    PrettyPrintWriter prettyWriter =
                            new PrettyPrintWriter(writer);

                    xstream.marshal(
                            peliculasUsuario,
                            prettyWriter
                    );

                    prettyWriter.close();
                }

                JOptionPane.showMessageDialog(
                        null,
                        "XML de películas generado correctamente"
                );

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Error al generar el XML: "
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}

package org.example.listar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import org.example.models.Pelicula;
import org.example.models.Usuario;
import org.example.models.Valoracion;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ListarValoracion {

    public JPanel panelListarValor;
    private JPanel panelPrincipal;
    private JList<Pelicula> listPeliValorar;
    private JButton bXML;

    public ListarValoracion(
            List<Pelicula> peliculas,
            Usuario usuarioLogueado) {

        DefaultListModel<Pelicula> modelo =
                new DefaultListModel<>();

        List<Valoracion> valoraciones = new ArrayList<>();

        // Obtenemos las valoraciones de las películas del usuario
        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId()
                    == usuarioLogueado.getId()
                    && peli.getValoracion() != null) {

                // Añadimos la película a la lista visual
                modelo.addElement(peli);

                // Añadimos su valoración a la lista
                valoraciones.add(peli.getValoracion());
            }
        }

        // Mostramos las películas valoradas
        listPeliValorar.setModel(modelo);

        // Generamos los ficheros al pulsar el botón
        bXML.addActionListener(e -> {

            try {
                // 2. Generamos el XML
                XStream xstream =
                        new XStream(new StaxDriver());

                // Nombres de las etiquetas XML
                xstream.alias("valoraciones", List.class);
                xstream.alias("valoracion", Valoracion.class);

                // Permitimos los tipos utilizados
                xstream.allowTypes(new Class[]{
                        Valoracion.class,
                        ArrayList.class
                });

                File directorio = new File("ficherosXML");

                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                FileOutputStream fileout =
                        new FileOutputStream( new File(directorio,
                                "FicheroValoraciones.xml")
                        );

                // Writer para escribir en UTF-8
                Writer writer =
                        new OutputStreamWriter(
                                fileout,
                                StandardCharsets.UTF_8
                        );

                // Creamos el escritor XML
                PrettyPrintWriter prettyWriter =
                        new PrettyPrintWriter(writer);

                // Convertimos la lista a XML
                xstream.marshal(
                        valoraciones,
                        prettyWriter
                );

                // Cerramos el escritor
                prettyWriter.close();

                JOptionPane.showMessageDialog(
                        null,
                        "Ficheros DAT y XML generados correctamente"
                );

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Error al guardar las valoraciones: "
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}

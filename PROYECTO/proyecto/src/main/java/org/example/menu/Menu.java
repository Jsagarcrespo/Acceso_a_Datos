package org.example.menu;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.example.crear.CrearValoracion;
import org.example.crear.CrearPelicula;
import org.example.editar.EditarPelicula;
import org.example.editar.EditarUsuario;
import org.example.editar.EditarValoracion;
import org.example.eliminar.EliminarPeli;
import org.example.eliminar.EliminarUsu;
import org.example.eliminar.EliminarValor;
import org.example.listar.ListarPeli;
import org.example.listar.ListarUsu;
import org.example.listar.ListarValoracion;
import org.example.models.Pelicula;
import org.example.models.Usuario;
import org.example.models.Valoracion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Container {
    private JPanel menu;
    private JButton CrearPeli;
    private JButton EdPelicula;
    private JButton ElPelicula;
    private JButton ListPelicula;
    private JButton EdUsu;
    private JButton bEliminarCuenta;
    private JButton bListaUsuarios;
    private JButton bEditValoracion;
    private JButton bCrearValoracion;
    private JButton bEliValor;
    private JButton bLValorar;
    private JButton bXMLtodo;

    List<Pelicula> peliculas = new ArrayList<>();
    List<Usuario> usuario = new ArrayList<>();

    public Menu(Usuario usuarioLogueado) {

        cargarPeliculas();
        cargarUsuarios();

        CrearPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear pelicula");
                frame.setContentPane(
                        new CrearPelicula(peliculas, usuarioLogueado).NPelicula
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        EdPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("EditarPelicula");
                frame.setContentPane(
                        new EditarPelicula(peliculas, usuarioLogueado).panelEditar
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        ElPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Eliminar Pelicula");
                frame.setContentPane(
                        new EliminarPeli(peliculas, usuarioLogueado).panelEliminarPeli
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        ListPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Listar Pelicula");
                frame.setContentPane(
                        new ListarPeli(peliculas, usuarioLogueado).ListarPeli
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        EdUsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Editar Usuario");
                frame.setContentPane(
                        new EditarUsuario(usuario).EdUsuario
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        bEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Eliminar Usuarios");
                frame.setContentPane(
                        new EliminarUsu(usuarioLogueado, usuario).panelEliminarUsu
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        bListaUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Listar Usuarios");
                frame.setContentPane(
                        new ListarUsu(usuario).ListarUsu
                );
                frame.pack();
                frame.setVisible(true);
            }
        });
        bEditValoracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Editar Valoracion");
                frame.setContentPane(
                        new EditarValoracion(peliculas, usuarioLogueado).panelValoracion
                );
                frame.pack();
                frame.setVisible(true);
            }
        });
        bCrearValoracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear Valoracion");
                frame.setContentPane(
                        new CrearValoracion(peliculas, usuarioLogueado).panelValoracion
                );
                frame.pack();
                frame.setVisible(true);
            }
        });
        bEliValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear Valoracion");
                frame.setContentPane(
                        new EliminarValor(peliculas, usuarioLogueado).panelEliminarValor
                );
                frame.pack();
                frame.setVisible(true);
            }
        });
        bLValorar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear Valoracion");
                frame.setContentPane(
                        new ListarValoracion(peliculas, usuarioLogueado).panelListarValor
                );
                frame.pack();
                frame.setVisible(true);
            }
        });

        bXMLtodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try {
                    // 1. Creamos XStream
                    XStream xstream = new XStream(new StaxDriver());

                    // 2. Nombres de las etiquetas XML
                    xstream.alias("usuarios", List.class);
                    xstream.alias("usuario", Usuario.class);
                    xstream.alias("pelicula", Pelicula.class);
                    xstream.alias("valoracion", Valoracion.class);

                    // 3. Permitimos los tipos utilizados
                    xstream.allowTypes(new Class[]{
                            ArrayList.class,
                            Usuario.class,
                            Pelicula.class,
                            Valoracion.class
                    });

                    // 4. Evitamos que XStream recorra relaciones
                    // que podrían generar referencias circulares
                    xstream.omitField(Usuario.class, "peliculas");
                    xstream.omitField(Pelicula.class, "usuario");

                    // 5. Creamos el directorio
                    File directorio = new File("ficherosXML");

                    if (!directorio.exists() && !directorio.mkdirs()) {
                        throw new IOException(
                                "No se pudo crear el directorio ficherosXML"
                        );
                    }

                    // 6. Preparamos el archivo XML
                    File archivoXML = new File(
                            directorio,
                            "FicheroPeliculaTotal.xml"
                    );

                    // 7. Escribimos el XML
                    try (Writer writer = new OutputStreamWriter(
                            new FileOutputStream(archivoXML),
                            StandardCharsets.UTF_8)) {

                        PrettyPrintWriter prettyWriter =
                                new PrettyPrintWriter(writer);

                        prettyWriter.startNode("usuarios");

                        // Recorremos TODOS los usuarios
                        for (Usuario usu : usuario) {

                            prettyWriter.startNode("usuario");

                            // Datos del usuario
                            xstream.omitField(Usuario.class, "peliculas");
                            xstream.marshal(usu, prettyWriter);

                            // Buscamos las películas del usuario
                            for (Pelicula peli : peliculas) {

                                if (peli.getUsuario() != null
                                        && peli.getUsuario().getId() == usu.getId()) {

                                    // Datos de la película
                                    prettyWriter.startNode("pelicula");

                                    xstream.omitField(Pelicula.class, "usuario");

                                    // Escribimos los campos de la película
                                    prettyWriter.startNode("titulo");
                                    prettyWriter.setValue(peli.getTitulo());
                                    prettyWriter.endNode();

                                    prettyWriter.startNode("director");
                                    prettyWriter.setValue(peli.getDirector());
                                    prettyWriter.endNode();

                                    prettyWriter.startNode("genero");
                                    prettyWriter.setValue(peli.getGenero());
                                    prettyWriter.endNode();

                                    prettyWriter.startNode("anio");
                                    prettyWriter.setValue(
                                            String.valueOf(peli.getAnio())
                                    );
                                    prettyWriter.endNode();

                                    prettyWriter.startNode("descripcion");
                                    prettyWriter.setValue(peli.getDescripcion());
                                    prettyWriter.endNode();

                                    // Valoración de la película, si existe
                                    if (peli.getValoracion() != null) {

                                        prettyWriter.startNode("valoracion");

                                        prettyWriter.startNode("puntuacion");
                                        prettyWriter.setValue(
                                                String.valueOf(
                                                        peli.getValoracion()
                                                                .getPuntuacion()
                                                )
                                        );
                                        prettyWriter.endNode();

                                        prettyWriter.startNode("comentario");
                                        prettyWriter.setValue(
                                                peli.getValoracion().getComentario()
                                        );
                                        prettyWriter.endNode();

                                        prettyWriter.endNode();
                                    }

                                    prettyWriter.endNode();
                                }
                            }

                            prettyWriter.endNode();
                        }

                        prettyWriter.endNode();
                        prettyWriter.flush();
                    }

                    JOptionPane.showMessageDialog(
                            null,
                            "FicheroPeliculaTotal.xml generado correctamente"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al generar el XML: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ex.printStackTrace();
                }






            }
        });

    }

    private void cargarPeliculas() {

        try {

            FileInputStream filein =
                    new FileInputStream("FicheroPelicula.dat");

            ObjectInputStream dataIS =
                    new ObjectInputStream(filein);

            peliculas = (List<Pelicula>) dataIS.readObject();

            dataIS.close();

        } catch (FileNotFoundException e) {

            peliculas = new ArrayList<>();

        } catch (IOException | ClassNotFoundException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al cargar las películas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            peliculas = new ArrayList<>();
        }
    }

    private void cargarUsuarios() {

        File archivo = new File(
                "ficherosDat",
                "FicheroUsuarios.dat"
        );

        if (!archivo.exists()) {
            usuario = new ArrayList<>();
            return;
        }

        try {

            FileInputStream filein =
                    new FileInputStream(archivo);

            ObjectInputStream dataIS =
                    new ObjectInputStream(filein);

            usuario = (List<Usuario>) dataIS.readObject();

            dataIS.close();

        } catch (IOException | ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al cargar los usuarios: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            usuario = new ArrayList<>();
        }
    }

    public JPanel getMenu() {
        return menu;
    }
}
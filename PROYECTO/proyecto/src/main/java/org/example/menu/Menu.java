package org.example.menu;

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
import org.example.models.Pelicula;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

        File archivo = new File("FicheroUsuario.dat");

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
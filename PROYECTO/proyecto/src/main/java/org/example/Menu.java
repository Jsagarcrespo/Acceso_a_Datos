package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    List<Pelicula> peliculas = new ArrayList<>();
    List<Usuario> usuario = new ArrayList<>();

    public Menu(Usuario usuarioLogueado) {

        cargarPeliculas();
        cargarUsuarios();

        CrearPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear pelicula");
                frame.setContentPane(new CrearPelicula(peliculas).NPelicula);
                frame.pack();
                frame.setVisible(true);
            }
        });
        EdPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFrame frame = new JFrame("EditarPelicula");
                    frame.setContentPane(new EditarPelicula(peliculas).panelEditar);
                    frame.pack();
                    frame.setVisible(true);

            }
        });
        ElPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Eliminar Pelicula");
                frame.setContentPane(new EliminarPeli(peliculas).panelEliminarPeli);
                frame.pack();
                frame.setVisible(true);
            }
        });
        ListPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Listar Pelicula");
                frame.setContentPane(new ListarPeli(peliculas).ListarPeli);
                frame.pack();
                frame.setVisible(true);
            }
        });
        EdUsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Editar Usuario");
                frame.setContentPane(new EditarUsuario(usuario).EdUsuario);
                frame.pack();
                frame.setVisible(true);
            }
        });
        bEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Eliminar Usuarios");
                frame.setContentPane(new EliminarUsu(usuarioLogueado, usuario).panelEliminarUsu);
                frame.pack();
                frame.setVisible(true);
            }
        });
        bListaUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Listar Usuarios");
                frame.setContentPane(new ListarUsu(usuario).ListarUsu);
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

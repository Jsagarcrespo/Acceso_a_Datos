package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


public class Menu extends Container {
    private JPanel menu;
    private JButton CrearPeli;
    private JButton EdPelicula;
    private JButton ElPelicula;
    private JButton ListPelicula;

    List<Pelicula> peliculas = new ArrayList<>();
    List<Usuario> usuario = new ArrayList<>();

    public Menu() {

        cargarPeliculas();

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

    public JPanel getMenu() {
        return menu;
    }

}

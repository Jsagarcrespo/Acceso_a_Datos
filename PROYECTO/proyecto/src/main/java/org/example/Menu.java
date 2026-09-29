package org.example;

import javax.swing.*;
import java.awt.*;
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

    List<Pelicula> peliculas = new ArrayList<>();
    List<Usuario> usuario = new ArrayList<>();

    public Menu() {
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
    }


    public JPanel getMenu() {
        return menu;
    }

}

package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CrearPelicula {
    private JTextField tFtitulo;
    private JTextField tFDirector;
    private JTextField tFGenero;
    private JTextArea tADescripcion;
    private JButton Guardar;
    private JLabel JLTitulo;
    JPanel NPelicula;
    private JTextField tFAnio;

    int contPeli = 0;

    public CrearPelicula(List<Pelicula> peliculas) {
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = String.valueOf(tFtitulo.getText());
                String director = String.valueOf(tFDirector.getText());
                String genero = String.valueOf(tFGenero.getText());

                int anio = Integer.parseInt(tFAnio.getText());

                String descrip = String.valueOf(tADescripcion.getText());

                Pelicula pelicula = new Pelicula(nombre, director, descrip,genero, anio);
                peliculas.add(pelicula);

                contPeli++;

                System.out.println("hay registrado los siguiente productos que en total son " + contPeli + ": ");

                for (Pelicula peli : peliculas){
                    System.out.println(peli.toString());
                }

                tFtitulo.setText("");
                tFDirector.setText("");
                tFGenero.setText("");
                tFAnio.setText("");
                tADescripcion.setText("");

            }
        });
    }



}

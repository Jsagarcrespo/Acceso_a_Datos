package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

    public CrearPelicula(List<Pelicula> peliculas, Usuario usuarioLogueado) {

        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String nombre = String.valueOf(tFtitulo.getText()).trim();
                    String director = String.valueOf(tFDirector.getText()).trim();
                    String genero = String.valueOf(tFGenero.getText()).trim();

                    int anio = Integer.parseInt(tFAnio.getText().trim());

                    String descrip = String.valueOf(tADescripcion.getText()).trim();

                    if (nombre.isEmpty() || director.isEmpty()
                            || genero.isEmpty() || descrip.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Rellena todos los campos",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    Pelicula pelicula =
                            new Pelicula(nombre, director, descrip, genero, anio);

                    // Relacionamos la película con el usuario que ha iniciado sesión
                    pelicula.setUsuario(usuarioLogueado);

                    peliculas.add(pelicula);

                    contPeli++;

                    System.out.println(
                            "Hay registradas " + contPeli + " películas nuevas."
                    );

                    for (Pelicula peli : peliculas) {
                        System.out.println(peli.toString());
                    }

                    FileOutputStream fileout =
                            new FileOutputStream("FicheroPelicula.dat");

                    ObjectOutputStream dataOS =
                            new ObjectOutputStream(fileout);

                    dataOS.writeObject(peliculas);

                    dataOS.close();

                    JOptionPane.showMessageDialog(
                            null,
                            "Película creada correctamente"
                    );

                    tFtitulo.setText("");
                    tFDirector.setText("");
                    tFGenero.setText("");
                    tFAnio.setText("");
                    tADescripcion.setText("");

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El año debe ser un número",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al guardar la película: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}
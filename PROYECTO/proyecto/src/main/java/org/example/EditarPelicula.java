package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EditarPelicula {

    JPanel panelEditar;
    private JList<Pelicula> lisPelicula;
    private JButton bEditar;
    private JTextField JTTitulo;
    private JTextArea tADescripcion;
    private JTextField JTAns;
    private JTextField JTGenero;
    private JTextField JTDirector;

    public EditarPelicula(List<Pelicula> peliculas, Usuario usuarioLogueado) {

        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        peliculaDefaultListModel.clear();

        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId() == usuarioLogueado.getId()) {

                peliculaDefaultListModel.addElement(peli);
            }
        }

        lisPelicula.setModel(peliculaDefaultListModel);

        bEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pelicula pelicula = lisPelicula.getSelectedValue();

                if (pelicula == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una pelicula"
                    );

                    return;
                }

                try {

                    String titulo = String.valueOf(JTTitulo.getText()).trim();
                    String director = String.valueOf(JTDirector.getText()).trim();
                    String genero = String.valueOf(JTGenero.getText()).trim();

                    int anio = Integer.parseInt(JTAns.getText().trim());

                    String descrip =
                            String.valueOf(tADescripcion.getText()).trim();

                    pelicula.setTitulo(titulo);
                    pelicula.setDirector(director);
                    pelicula.setGenero(genero);
                    pelicula.setAnio(anio);
                    pelicula.setDescripcion(descrip);

                    guardarPeliculas(peliculas);

                    JOptionPane.showMessageDialog(
                            null,
                            "Película modificada correctamente"
                    );

                    lisPelicula.clearSelection();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El año debe ser un número",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        lisPelicula.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                Pelicula peli = lisPelicula.getSelectedValue();

                if (peli != null) {

                    JTTitulo.setText(peli.getTitulo());
                    JTDirector.setText(peli.getDirector());
                    JTGenero.setText(peli.getGenero());
                    JTAns.setText(String.valueOf(peli.getAnio()));
                    tADescripcion.setText(peli.getDescripcion());
                }
            }
        });
    }

    private void guardarPeliculas(List<Pelicula> peliculas) {

        try {

            FileOutputStream fileout =
                    new FileOutputStream("FicheroPelicula.dat");

            ObjectOutputStream dataOS =
                    new ObjectOutputStream(fileout);

            dataOS.writeObject(peliculas);

            dataOS.close();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar las películas: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
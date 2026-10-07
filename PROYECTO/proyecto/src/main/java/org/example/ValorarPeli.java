package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ValorarPeli {
    JPanel panelValoracion;
    private JLabel JLTitulo;
    private JTextField tFPuntuacion;
    private JButton bGuardar;
    private JList<Pelicula> listPelicula;
    private JTextArea tAComentario;

    public ValorarPeli(List<Pelicula> peliculas, Usuario usuarioLogueado) {
        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        peliculaDefaultListModel.clear();

        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId() == usuarioLogueado.getId()) {

                peliculaDefaultListModel.addElement(peli);
            }
        }

        listPelicula.setModel(peliculaDefaultListModel);

        // Al seleccionar una película, comprobamos si ya tiene valoración
        listPelicula.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                Pelicula peliculaSeleccionada =
                        listPelicula.getSelectedValue();

                if (peliculaSeleccionada != null) {

                    Valoracion valoracion =
                            peliculaSeleccionada.getValoracion();

                    if (valoracion != null) {

                        tFPuntuacion.setText(
                                String.valueOf(valoracion.getPuntuacion())
                        );

                        tAComentario.setText(
                                valoracion.getComentario()
                        );

                    } else {

                        tFPuntuacion.setText("");
                        tAComentario.setText("");
                    }
                }
            }
        });

        bGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pelicula peliculaSeleccionada =
                        listPelicula.getSelectedValue();

                if (peliculaSeleccionada == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Selecciona una película",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                try {

                    int puntuacion = Integer.parseInt(
                            tFPuntuacion.getText().trim()
                    );

                    if (puntuacion < 0 || puntuacion > 10) {
                        JOptionPane.showMessageDialog(
                                null,
                                "La puntuación debe estar entre 0 y 10",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    String comentario =
                            tAComentario.getText().trim();

                    Valoracion valoracion =
                            new Valoracion(puntuacion, comentario);

                    peliculaSeleccionada.setValoracion(valoracion);

                    guardarPeliculas(peliculas);

                    JOptionPane.showMessageDialog(
                            null,
                            "Valoración guardada correctamente"
                    );

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "La puntuación debe ser un número",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
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

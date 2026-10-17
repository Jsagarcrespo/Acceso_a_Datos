package org.example.eliminar;

import org.example.models.Pelicula;
import org.example.models.Usuario;
import org.example.models.Valoracion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EliminarValor {

    public JPanel panelEliminarValor;
    private JPanel panelPrincipal;
    private JList<Pelicula> listPeliValorar;
    private JLabel Titulo;
    private JLabel JLPunt;
    private JTextArea tAComen;
    private JButton bEliminar;

    public EliminarValor(List<Pelicula> peliculas, Usuario usuarioLogueado) {

        // Creamos el modelo de la lista
        DefaultListModel<Pelicula> modelo =
                new DefaultListModel<>();

        // Mostramos solamente las películas del usuario
        // que tengan una valoración
        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId() == usuarioLogueado.getId()
                    && peli.getValoracion() != null) {

                modelo.addElement(peli);
            }
        }

        listPeliValorar.setModel(modelo);

        // Al seleccionar una película, mostramos su valoración
        listPeliValorar.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                Pelicula peliculaSeleccionada =
                        listPeliValorar.getSelectedValue();

                if (peliculaSeleccionada != null) {

                    Valoracion valoracion =
                            peliculaSeleccionada.getValoracion();

                    if (valoracion != null) {

                        JLPunt.setText(
                                "Puntuación: "
                                        + valoracion.getPuntuacion()
                        );

                        tAComen.setText(
                                valoracion.getComentario()
                        );
                    }

                } else {

                    JLPunt.setText("Puntuación:");
                    tAComen.setText("");
                }
            }
        });

        // Botón para eliminar la valoración
        bEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Pelicula peliculaSeleccionada =
                        listPeliValorar.getSelectedValue();

                // Comprobamos que haya una película seleccionada
                if (peliculaSeleccionada == null) {

                    JOptionPane.showMessageDialog(
                            panelEliminarValor,
                            "Selecciona una película",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                // Confirmamos que el usuario quiere eliminarla
                int respuesta = JOptionPane.showConfirmDialog(
                        panelEliminarValor,
                        "¿Quieres eliminar la valoración de esta película?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (respuesta != JOptionPane.YES_OPTION) {
                    return;
                }

                // Eliminamos la valoración de la película
                peliculaSeleccionada.setValoracion(null);

                // Guardamos los cambios en el fichero
                if (guardarPeliculas(peliculas)) {

                    // Quitamos la película de la lista visual
                    modelo.removeElement(peliculaSeleccionada);

                    // Limpiamos los campos
                    JLPunt.setText("Puntuación:");
                    tAComen.setText("");

                    JOptionPane.showMessageDialog(
                            panelEliminarValor,
                            "Valoración eliminada correctamente"
                    );
                }
            }
        });

        // Si no hay películas valoradas, mostramos un aviso
        if (modelo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    panelEliminarValor,
                    "No tienes películas con valoraciones para eliminar.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // Guardamos las películas actualizadas en el fichero
    private boolean guardarPeliculas(List<Pelicula> peliculas) {

        try (FileOutputStream fileout =
                     new FileOutputStream("FicheroPelicula.dat");
             ObjectOutputStream dataOS =
                     new ObjectOutputStream(fileout)) {

            dataOS.writeObject(peliculas);
            return true;

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    panelEliminarValor,
                    "Error al guardar las películas: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }
}
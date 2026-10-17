package org.example.crear;

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
import java.awt.Color;

public class CrearValoracion {

    public JPanel panelValoracion;
    private JLabel JLTitulo;
    private JTextField tFPuntuacion;
    private JButton bGuardar;
    private JList<Pelicula> listPelicula;
    private JTextArea tAComentario;

    public CrearValoracion(List<Pelicula> peliculas, Usuario usuarioLogueado) {
        DefaultListModel<Pelicula> peliculaDefaultListModel =
                new DefaultListModel<>();

        for (Pelicula peli : peliculas) {

            if (peli.getUsuario() != null
                    && peli.getUsuario().getId() == usuarioLogueado.getId()) {

                peliculaDefaultListModel.addElement(peli);
            }
        }

        listPelicula.setModel(peliculaDefaultListModel);

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
                        
                        tFPuntuacion.setEditable(false);
                        tAComentario.setEditable(false);

                    } else {
                        
                        tFPuntuacion.setText("");
                        tAComentario.setText("");

                        tFPuntuacion.setEditable(true);
                        tAComentario.setEditable(true);
                    }
                    
                    Color colorFondo = tFPuntuacion.isEditable()
                            ? Color.WHITE
                            : Color.LIGHT_GRAY;

                    tFPuntuacion.setBackground(colorFondo);
                    tAComentario.setBackground(colorFondo);

                } else {
                    
                    tFPuntuacion.setText("");
                    tAComentario.setText("");

                    tFPuntuacion.setEditable(false);
                    tAComentario.setEditable(false);

                    tFPuntuacion.setBackground(Color.LIGHT_GRAY);
                    tAComentario.setBackground(Color.LIGHT_GRAY);
                }
            }
        });
        
        tFPuntuacion.setEditable(false);
        tAComentario.setEditable(false);

        tFPuntuacion.setBackground(Color.LIGHT_GRAY);
        tAComentario.setBackground(Color.LIGHT_GRAY);
        
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

                if (peliculaSeleccionada.getValoracion() != null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Esta pelicula ya tiene una valoracion. "
                                    + "Para modificarla, utiliza Editar Valoracion.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE
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
                                "La puntuacion debe estar entre 0 y 10",
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
                            "Valoracion guardada correctamente"
                    );

                    tFPuntuacion.setEditable(false);
                    tAComentario.setEditable(false);

                    tFPuntuacion.setBackground(Color.LIGHT_GRAY);
                    tAComentario.setBackground(Color.LIGHT_GRAY);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "La puntuacion debe ser un número",
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
                    "Error al guardar las peliculas: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EliminarPeli {
    JPanel panelEliminarPeli;
    private JPanel panelPrincipal;
    private JList<Pelicula> listPeli;
    private JLabel JLtit;
    private JLabel JLdir;
    private JLabel JLgen;
    private JLabel JLans;
    private JLabel Genero;
    private JLabel Anio;
    private JLabel Director;
    private JLabel Titulo;
    private JTextArea tADescripcion;
    private JButton bEliminar;

    public EliminarPeli(List<Pelicula> peliculas) {


        JLtit.setText("");
        JLdir.setText("");
        JLgen.setText("");
        JLans.setText("");

        tADescripcion.setText("");
        tADescripcion.setForeground(Color.WHITE);
        tADescripcion.setBackground(Color.DARK_GRAY);


        DefaultListModel<Pelicula> peliculaDefaultListModel = new DefaultListModel<>();
        peliculaDefaultListModel.clear();

        for (Pelicula peli : peliculas){
            peliculaDefaultListModel.addElement(peli);
        }

        listPeli.setModel(peliculaDefaultListModel);

        listPeli.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    Pelicula peli = listPeli.getSelectedValue();

                    if (peli != null) {
                        JLtit.setText(peli.getTitulo());
                        JLdir.setText(peli.getDirector());
                        JLgen.setText(peli.getGenero());
                        JLans.setText(String.valueOf(peli.getAnio()));

                        tADescripcion.setText(peli.getDescripcion());

                    }
                }
            }
        });


        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pelicula pelicula = listPeli.getSelectedValue();

                if (pelicula == null){
                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una pelicula"
                    );
                    return;
                }

                int indice = listPeli.getSelectedIndex();

                peliculas.remove(indice);

                peliculaDefaultListModel.remove(indice);

                guardarPeliculas(peliculas);

                JOptionPane.showMessageDialog(
                        null,
                        "Película eliminada correctamente"
                );
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

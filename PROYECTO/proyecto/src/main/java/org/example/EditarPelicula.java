package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditarPelicula {
    JPanel panelEditar;
    private JList<Pelicula> lisPelicula;
    private JButton bEditar;
    private JTextField JTTitulo;
    private JTextArea tADescripcion;
    private JTextField JTAns;
    private JTextField JTGenero;
    private JTextField JTDirector;


    public EditarPelicula(List<Pelicula> peliculas) {
        DefaultListModel<Pelicula> peliculaDefaultListModel = new DefaultListModel<>();
        peliculaDefaultListModel.clear();

        for (Pelicula peli : peliculas){
            peliculaDefaultListModel.addElement(peli);
        }

        lisPelicula.setModel(peliculaDefaultListModel);

            bEditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Pelicula pelicula = lisPelicula.getSelectedValue();

                    if (pelicula == null){
                        JOptionPane.showMessageDialog(
                                null,
                                "Seleccione una pelicula"
                        );
                        return;
                    }

                    String titulo = String.valueOf(JTTitulo.getText());
                    String director = String.valueOf(JTDirector.getText());
                    String genero = String.valueOf(JTGenero.getText());

                    int anio = Integer.parseInt(JTAns.getText());

                    String descrip = String.valueOf(tADescripcion.getText());

                    pelicula.setTitulo(titulo);
                    pelicula.setDirector(director);
                    pelicula.setGenero(genero);
                    pelicula.setAnio(anio);
                    pelicula.setDescripcion(descrip);

                    lisPelicula.clearSelection();

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




}

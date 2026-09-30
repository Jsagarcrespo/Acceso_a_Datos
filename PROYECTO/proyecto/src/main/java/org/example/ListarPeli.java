package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ListarPeli {
    JPanel ListarPeli;
    private JList<Pelicula> listPeli;
    private JButton bXML;

    public ListarPeli(List<Pelicula> peliculas) {

        DefaultListModel<Pelicula> peliculaDefaultListModel = new DefaultListModel<>();
        peliculaDefaultListModel.clear();

        for (Pelicula peli : peliculas){
            peliculaDefaultListModel.addElement(peli);
        }

        listPeli.setModel(peliculaDefaultListModel);


        listPeli.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {


                //////



                //////
            }
        });
    }
}

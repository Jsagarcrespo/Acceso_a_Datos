package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EditarUsuario {
    JPanel EdUsuario;
    private JLabel JLTitulo;
    private JTextField tFNom;
    private JTextField tFPasswd;
    private JTextField tFTelefono;
    private JButton Guardar;
    private JList<Usuario> listUsuario;



    public EditarUsuario(List<Usuario> usuarios) {

        DefaultListModel<Usuario> usuarioDefaultListModel = new DefaultListModel<>();
        usuarioDefaultListModel.clear();

        for (Usuario usu : usuarios) {
            usuarioDefaultListModel.addElement(usu);
        }

        listUsuario.setModel(usuarioDefaultListModel);


        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Usuario usuario = listUsuario.getSelectedValue();

                if (usuario == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una pelicula"
                    );
                    return;
                }

                String nom = String.valueOf(tFNom.getText());
                int tel =  Integer.parseInt(tFTelefono.getText());
                String passwd = String.valueOf(tFPasswd.getText());


                usuario.setNombre(nom);
                usuario.setTel(tel);
                usuario.setPassw(passwd);


                guardarUsuarios(usuarios);

                listUsuario.clearSelection();

            }
        });
        listUsuario.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    Usuario usu = listUsuario.getSelectedValue();

                    if (usu != null) {
                        tFNom.setText(usu.getNombre());
                        tFPasswd.setText(usu.getPassw());
                        tFTelefono.setText(String.valueOf(usu.getTel()));

                    }
                }


            }
        });
    }

    private void guardarUsuarios(List<Usuario> usuarios) {

        try {

            FileOutputStream fileout =
                    new FileOutputStream("FicheroPelicula.dat");

            ObjectOutputStream dataOS =
                    new ObjectOutputStream(fileout);

            dataOS.writeObject(usuarios);

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

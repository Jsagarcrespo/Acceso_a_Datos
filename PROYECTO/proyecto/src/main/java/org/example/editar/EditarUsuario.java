package org.example.editar;

import org.example.models.Usuario;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EditarUsuario {

    public JPanel EdUsuario;
    private JLabel JLTitulo;
    private JTextField tFNom;
    private JTextField tFPasswd;
    private JTextField tFTelefono;
    private JButton Guardar;
    private JList<Usuario> listUsuario;

    public EditarUsuario(List<Usuario> usuarios) {

        DefaultListModel<Usuario> usuarioDefaultListModel =
                new DefaultListModel<>();

        for (Usuario usu : usuarios) {
            usuarioDefaultListModel.addElement(usu);
        }

        listUsuario.setModel(usuarioDefaultListModel);

        System.out.println(
                "Usuarios en la lista visual: "
                        + usuarioDefaultListModel.getSize()
        );

        System.out.println(
                "Modelo asociado: " + listUsuario.getModel().getSize()
        );

        listUsuario.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                Usuario usu = listUsuario.getSelectedValue();

                if (usu != null) {
                    tFNom.setText(usu.getNombre());
                    tFPasswd.setText(usu.getPassw());
                    tFTelefono.setText(String.valueOf(usu.getTel()));
                }
            }
        });

        Guardar.addActionListener(e -> {

            Usuario usuario = listUsuario.getSelectedValue();

            if (usuario == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un usuario"
                );
                return;
            }

            try {

                String nom = tFNom.getText().trim();
                String passwd = tFPasswd.getText().trim();
                int tel = Integer.parseInt(tFTelefono.getText().trim());

                if (nom.isEmpty() || passwd.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Rellena todos los campos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                usuario.setNombre(nom);
                usuario.setTel(tel);
                usuario.setPassw(passwd);

                guardarUsuarios(usuarios);

                // Actualizar también el elemento mostrado en la JList
                int indice = listUsuario.getSelectedIndex();
                usuarioDefaultListModel.setElementAt(usuario, indice);

                JOptionPane.showMessageDialog(
                        null,
                        "Usuario modificado correctamente"
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "El teléfono debe ser un número",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private void guardarUsuarios(List<Usuario> usuarios) {

        try {

            File directorio = new File("ficherosDat");

            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            FileOutputStream fileout =
                    new FileOutputStream( new File(directorio,
                            "FicheroUsuarios.dat")
                    );

            ObjectOutputStream dataOS =
                    new ObjectOutputStream(fileout);

            dataOS.writeObject(usuarios);

            dataOS.close();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar los usuarios: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
package org.example;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CrearUsuario {

    JPanel NUsuario;
    private JLabel JLTitulo;
    private JTextField tFNom;
    private JTextField tFPasswd;
    private JTextField tFTelefono;
    private JButton Guardar;


    public CrearUsuario(List<Usuario> usuarios) {

        Guardar.addActionListener(e -> {

            try {

                String nombre = tFNom.getText().trim();
                String passwd = tFPasswd.getText().trim();
                int tel = Integer.parseInt(tFTelefono.getText().trim());

                if (nombre.isEmpty() || passwd.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Rellena todos los campos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                int id = 0;

                for (Usuario usu : usuarios) {
                    if (usu.getId() > id) {
                        id = usu.getId();
                    }
                }

                id++;


                Usuario usuario = new Usuario(id,nombre, tel, passwd);

                usuarios.add(usuario);

                guardarUsuarios(usuarios);

                JOptionPane.showMessageDialog(
                        null,
                        "Usuario creado correctamente"
                );

                tFNom.setText("");
                tFTelefono.setText("");
                tFPasswd.setText("");

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

            FileOutputStream fileout =
                    new FileOutputStream("FicheroUsuario.dat");

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
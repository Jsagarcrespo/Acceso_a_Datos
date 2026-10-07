package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class PagPrincipal {

    JPanel panel1;
    private JPanel Jlog;
    private JTextField TFusu;
    private JTextField tFContra;
    private JButton Bentrar;
    private JLabel TFcontra;
    private JButton bRegistrar;

    List<Usuario> usuario = new ArrayList<>();

    public PagPrincipal() {

        cargarUsuarios();

        Bentrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usu = TFusu.getText();
                String contra = tFContra.getText();

                if (usu.isEmpty() || contra.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Te falta un área por rellenar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                try {

                    Usuario usuarioLogueado = null;

                    for (Usuario usur : usuario) {

                        if (usur.getNombre().equals(usu)
                                && usur.getPassw().equals(contra)) {

                            usuarioLogueado = usur;
                            break;
                        }
                    }

                    if (usuarioLogueado != null) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Inicio de sesión correcto"
                        );

                        Menu m = new Menu(usuarioLogueado);
                        JFrame frame = new JFrame("Menu");
                        frame.setContentPane(m.getMenu());
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                        SwingUtilities
                                .getWindowAncestor(Bentrar)
                                .dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "Usuario o contraseña incorrectos",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al leer los usuarios: "
                                    + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        bRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registrar Usuario");
                frame.setContentPane(new CrearUsuario(usuario).NUsuario);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private void cargarUsuarios() {

        try {

            FileInputStream filein =
                    new FileInputStream("FicheroUsuario.dat");

            ObjectInputStream dataIS =
                    new ObjectInputStream(filein);

            usuario = (List<Usuario>) dataIS.readObject();

            dataIS.close();

        } catch (IOException | ClassNotFoundException ex) {

            usuario = new ArrayList<>();
        }
    }
}
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EliminarUsu {

    JPanel panelEliminarUsu;
    private JButton bEliminar;
    private JPanel panelPrincipal;
    private JLabel JLnom;
    private JLabel JLtel;
    private JLabel JLcon;
    private JLabel JLid;
    private JLabel abNom;
    private JList<Usuario> listUsu;

    public EliminarUsu(Usuario usuario, List<Usuario> usuarios) {

        JLid.setText(String.valueOf(usuario.getId()));
        JLnom.setText(usuario.getNombre());
        JLtel.setText(String.valueOf(usuario.getTel()));
        JLcon.setText(usuario.getPassw());

        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Seguro que quieres eliminar este usuario?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {

                    for (int i = 0; i < usuarios.size(); i++) {

                        if (usuarios.get(i).getId() == usuario.getId()) {
                            usuarios.remove(i);
                            break;
                        }
                    }

                    if (guardarUsuarios(usuarios)) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Usuario eliminado correctamente"
                        );

                        cerrarVentanas();

                        PagPrincipal pagPrincipal = new PagPrincipal();

                        JFrame frame = new JFrame("Inicio de sesión");
                        frame.setContentPane(pagPrincipal.panel1);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
            }
        });
    }

    private boolean guardarUsuarios(List<Usuario> usuarios) {

        try {

            FileOutputStream fileout =
                    new FileOutputStream("FicheroUsuario.dat");

            ObjectOutputStream dataOS =
                    new ObjectOutputStream(fileout);

            dataOS.writeObject(usuarios);

            dataOS.close();

            return true;

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar los usuarios: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }

    private void cerrarVentanas() {

        Window[] ventanas = Window.getWindows();

        for (Window ventana : ventanas) {
            ventana.dispose();
        }
    }

    private void limpiarDatos() {

        JLid.setText("");
        JLnom.setText("");
        JLtel.setText("");
        JLcon.setText("");
    }
}
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

                    // Primero eliminamos las películas de este usuario
                    if (!eliminarPeliculasUsuario(usuario)) {
                        return;
                    }

                    // Después eliminamos el usuario
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

    private boolean eliminarPeliculasUsuario(Usuario usuario) {

        File archivo = new File("FicheroPelicula.dat");

        if (!archivo.exists()) {
            return true;
        }

        try {

            FileInputStream filein =
                    new FileInputStream(archivo);

            ObjectInputStream dataIS =
                    new ObjectInputStream(filein);

            List<Pelicula> peliculas =
                    (List<Pelicula>) dataIS.readObject();

            dataIS.close();

            for (int i = peliculas.size() - 1; i >= 0; i--) {

                Pelicula pelicula = peliculas.get(i);

                if (pelicula.getUsuario() != null
                        && pelicula.getUsuario().getId() == usuario.getId()) {

                    peliculas.remove(i);
                }
            }

            FileOutputStream fileout =
                    new FileOutputStream("FicheroPelicula.dat");

            ObjectOutputStream dataOS =
                    new ObjectOutputStream(fileout);

            dataOS.writeObject(peliculas);
            dataOS.close();

            return true;

        } catch (IOException | ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al eliminar las películas del usuario: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
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

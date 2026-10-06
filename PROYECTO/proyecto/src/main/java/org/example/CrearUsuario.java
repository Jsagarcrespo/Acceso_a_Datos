package org.example;

import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = String.valueOf(tFNom.getText());
                String passwd = String.valueOf(tFPasswd.getText());
                int tel = Integer.parseInt(tFTelefono.getText());

                Usuario usuario = new Usuario(nombre, tel, passwd);
                usuarios.add(usuario);


                try {
                    FileOutputStream fileout = new FileOutputStream("FicheroUsuarios.dat");
                    ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

                    dataOS.writeObject(usuarios);
                    dataOS.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                tFNom.setText("");
                tFTelefono.setText("");
                tFPasswd.setText("");


            }
        });
    }


}

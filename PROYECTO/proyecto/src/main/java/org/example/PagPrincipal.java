package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagPrincipal {
    JPanel panel1;
    private JPanel Jlog;
    private JTextField TFusu;
    private JTextField tFContra;
    private JButton Bentrar;
    private JLabel TFcontra;

    public PagPrincipal() {
        Bentrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = String.valueOf(TFusu.getText());
                String usuar = "admin";
                String contra = tFContra.getText();
                String passwd = "admin";

                if (usuario.isEmpty() || contra.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Te falta un area por rellenar", "Título", JOptionPane.ERROR_MESSAGE);
                }

                if (usuario.equals(usuar) && contra.equals(passwd)) {
                    //JOptionPane.showMessageDialog(null, "Has introducido bien la contraseña", "Título", JOptionPane.INFORMATION_MESSAGE);

                    Menu m = new Menu();

                    JFrame frame = new JFrame("Menu");
                    frame.setContentPane(m.getMenu());
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    SwingUtilities.getWindowAncestor(Bentrar).dispose();


                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Título", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

   /* public static void main(String[] args) {
        JFrame frame = new JFrame("PagPrincipal");
        frame.setContentPane(new PagPrincipal().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);    }*/

    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new PagPrincipal().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/

}

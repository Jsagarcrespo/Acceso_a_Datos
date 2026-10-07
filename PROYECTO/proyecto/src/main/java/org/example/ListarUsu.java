package org.example;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ListarUsu {
    JPanel ListarUsu;
    private JList listUsu;
    private JButton bXML;

    public ListarUsu(List<Usuario> usuarios) {

        DefaultListModel<Usuario> usuarioDefaultListModel =
                new DefaultListModel<>();

        for (Usuario usu : usuarios) {
            usuarioDefaultListModel.addElement(usu);
        }

        listUsu.setModel(usuarioDefaultListModel);

        bXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    XStream xstream = new XStream(new StaxDriver());

                    // Nombre que tendrá la lista en el XML
                    xstream.alias("usuarios", List.class);

                    // Nombre que tendrá cada película
                    xstream.alias("usuario", Usuario.class);

                    // Creamos el fichero
                    FileOutputStream fileout =
                            new FileOutputStream("FicheroUsuarios.xml");

                    // Writer para escribir el XML
                    Writer writer =
                            new OutputStreamWriter(
                                    fileout,
                                    StandardCharsets.UTF_8
                            );

                    // PrettyPrintWriter permite escribir el XML
                    // con saltos de línea e indentación
                    PrettyPrintWriter prettyWriter =
                            new PrettyPrintWriter(writer);

                    // Convertimos la lista a XML
                    xstream.marshal(usuarios, prettyWriter);

                    // Cerramos el writer
                    prettyWriter.close();

                    JOptionPane.showMessageDialog(
                            null,
                            "XML generado correctamente"
                    );

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al generar el XML: " + ex.getMessage()
                    );
                }


            }
        });
    }
}

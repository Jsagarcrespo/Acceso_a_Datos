package org.example.listar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ListarUsu {
    public JPanel ListarUsu;
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

                    xstream.alias("usuarios", List.class);

                    xstream.alias("usuario", Usuario.class);

                    File directorio = new File("ficherosXML");

                    if (!directorio.exists()) {
                        directorio.mkdirs();
                    }

                    FileOutputStream fileout =
                            new FileOutputStream( new File(directorio,
                                    "FicheroUsuarios.xml")
                            );

                    Writer writer =
                            new OutputStreamWriter(
                                    fileout,
                                    StandardCharsets.UTF_8
                            );

                    PrettyPrintWriter prettyWriter =
                            new PrettyPrintWriter(writer);

                    xstream.marshal(usuarios, prettyWriter);

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

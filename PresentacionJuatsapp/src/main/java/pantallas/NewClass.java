/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class NewClass extends JFrame {
    private JTextPane chatArea;
    private JTextField mensajeCampo;

    public NewClass() {
        setTitle("Chat de Mensajería");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

                // Área de chat
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setContentType("text/html"); // Permitir el uso de HTML
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // Campo de mensaje
        mensajeCampo = new JTextField();
        mensajeCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = mensajeCampo.getText();
                agregarMensaje("Yo: " + mensaje, true); // Mensaje enviado por el usuario
                mensajeCampo.setText("");
            }
        });
        add(mensajeCampo, BorderLayout.SOUTH);

        // Botón para enviar mensaje
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = mensajeCampo.getText();
                agregarMensaje("Yo: " + mensaje, false); // Mensaje enviado por el usuario
                mensajeCampo.setText("");
            }
        });
        add(enviarButton, BorderLayout.EAST);
    }

    // Método para agregar un mensaje al área de chat
    private void agregarMensaje(String mensaje, boolean enviadoPorUsuario) {
        StyledDocument doc = chatArea.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributos, enviadoPorUsuario ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);

        try {
            doc.insertString(doc.getLength(), mensaje + "\n", atributos);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewClass chatInterfaz = new NewClass();
                chatInterfaz.setVisible(true);
            }
        });
    }
}

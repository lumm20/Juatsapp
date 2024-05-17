/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.ChatDTO;
import DTOs.UsuarioDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import fachadas.FachadaControlUsuarios;

/**
 *
 * @author LuisaM
 */
public class Parent extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    protected static FachadaControlUsuarios fachadaUsuarios;
    protected static UsuarioDTO user;
    
    public Parent(){
        setTitle("Juatsapp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(393, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        fachadaUsuarios=new FachadaControlUsuarios();
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.add(new LoginPanel(), "login");
        cardPanel.add(new StartPanel(), "start");
        cardPanel.add(new SignInPanel(), "signIn");
        cardPanel.add(new ChatPanel(), "chat");
        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }
    
    public void mostrarVentana(String nombrePanel) {
        cardLayout.show(cardPanel, nombrePanel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Parent parent = new Parent();
                parent.setVisible(true);
            }
        });
    }
}

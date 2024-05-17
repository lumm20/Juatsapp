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
    
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Parent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Parent().setVisible(true);
            }
        });
    }

    
//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Parent parent = new Parent();
//                parent.setVisible(true);
//            }
//        });
//    }
}

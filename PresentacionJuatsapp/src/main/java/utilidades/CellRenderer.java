/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import DTOs.ChatDTO;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author LuisaM
 */
public class CellRenderer extends JLabel implements ListCellRenderer<ChatDTO> {

        public CellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ChatDTO> list, ChatDTO chat, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(chat.getNombreContacto() + " - " + chat.getMensajes().peek().getTexto());
            setPreferredSize(new Dimension(280, 50)); // Ajustar el tamaño del componente de la celda
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
}

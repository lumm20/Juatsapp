/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EntidadChat;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author LuisaM
 */
public interface IChatDAO {
    EntidadChat agregarChat(EntidadChat chat)throws PersistenciaException;
    boolean eliminarChat(EntidadChat chat)throws PersistenciaException;
    EntidadChat vaciarChat(EntidadChat chat)throws PersistenciaException;
    EntidadChat buscarChat(EntidadChat chat)throws PersistenciaException;
    List<EntidadChat> buscarChats()throws PersistenciaException;
}

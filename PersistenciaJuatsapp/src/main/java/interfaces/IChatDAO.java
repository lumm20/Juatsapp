/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EntidadChat;
import entidades.EntidadMensaje;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author LuisaM
 */
public interface IChatDAO {
    EntidadChat agregarChat(EntidadChat chat)throws PersistenciaException;
    EntidadChat editarContacto(EntidadChat chat,EntidadUsuario contactoEditado)throws PersistenciaException;
    boolean eliminarChat(EntidadChat chat)throws PersistenciaException;
    EntidadChat agregarMensaje(EntidadChat chat,EntidadMensaje mensaje)throws PersistenciaException;
    EntidadChat vaciarChat(EntidadChat chat)throws PersistenciaException;
    EntidadChat buscarChat(EntidadChat chat)throws PersistenciaException;
    List<EntidadChat> buscarChats(EntidadUsuario usuario)throws PersistenciaException;
}

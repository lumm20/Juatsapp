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
import java.util.Set;

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
    /**
     * Busca un chat en especifico. Como criterio usa el telefono del creador y del
     * contacto del parametro 'chat' para encontrar el chat que incluya ambos numeros
     * en su registro.
     * @param chat
     * @return
     * @throws PersistenciaException 
     */
    EntidadChat buscarChat(EntidadChat chat)throws PersistenciaException;
    /**
     * Busca todos los chats del usuario del parametro. Como criterio usa el telefono
     * del usuario para buscar los chats en los que sea participante, tanto los que
     * haya creado como a los que fue invitado
     * @param usuario El usuario del cual se buscaran los chats. Su campo
     * 'telefono' no debe ser null
     * @return La lista de chats donde el usuario es participante
     * @throws PersistenciaException En caso de que haya ocurrido un error en la base de datos
     * en el proceso de busqueda
     */
    Set<EntidadChat> buscarChats(EntidadUsuario usuario)throws PersistenciaException;
}

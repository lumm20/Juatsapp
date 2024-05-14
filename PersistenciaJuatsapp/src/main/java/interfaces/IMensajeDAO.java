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
public interface IMensajeDAO {
    /**
     * 
     * @param mensaje
     * @return
     * @throws PersistenciaException 
     */
    EntidadMensaje guardarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    /**
     * 
     * @param mensaje
     * @param mensajeNuevo
     * @return
     * @throws PersistenciaException 
     */
    EntidadMensaje editarMensaje(EntidadMensaje mensaje, EntidadMensaje mensajeNuevo)throws PersistenciaException;
    /**
     * 
     * @param mensaje
     * @return
     * @throws PersistenciaException 
     */
    boolean eliminarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    /**
     * Busca un mensaje en especifico dentro de la coleccion de mensajes. 
     * Usa el id del mensaje como criterio de busqueda
     * @param mensaje Mensaje con el id a buscar en la coleccion
     * @return La entidad mensaje encontrada, null si no se encontró
     * @throws PersistenciaException En caso de que ocurra un error de base de datos
     * en el proceso de busqueda
     */
    EntidadMensaje buscarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    /**
     * Busca todos los mensajes de la coleccion donde el remitente 
     * @param usuario
     * @return
     * @throws PersistenciaException 
     */
    List<EntidadMensaje> buscarMensajes(EntidadUsuario usuario)throws PersistenciaException;
    /**
     * 
     * @param chat
     * @return
     * @throws PersistenciaException 
     */
    List<EntidadMensaje> buscarMensajes(EntidadChat chat)throws PersistenciaException;
    /**
     * 
     * @param chat
     * @param mensajeFiltrado
     * @return
     * @throws PersistenciaException 
     */
    List<EntidadMensaje> buscarMensajesFiltrados(EntidadChat chat,EntidadMensaje mensajeFiltrado)throws PersistenciaException;
    /**
     * 
     * @param mensajeFiltrado
     * @return
     * @throws PersistenciaException 
     */
    List<EntidadMensaje> buscarMensajesFiltrados(EntidadMensaje mensajeFiltrado)throws PersistenciaException;
}

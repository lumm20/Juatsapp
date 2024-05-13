/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EntidadChat;
import entidades.EntidadMensaje;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author LuisaM
 */
public interface IMensajeDAO {
    EntidadMensaje guardarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    EntidadMensaje editarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    EntidadMensaje eliminarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    EntidadMensaje buscarMensaje(EntidadMensaje mensaje)throws PersistenciaException;
    List<EntidadMensaje> buscarMensajes()throws PersistenciaException;
    List<EntidadMensaje> buscarMensajes(EntidadChat chat)throws PersistenciaException;
    List<EntidadMensaje> buscarMensajesFiltrados(EntidadMensaje mensajeFiltrado)throws PersistenciaException;
}

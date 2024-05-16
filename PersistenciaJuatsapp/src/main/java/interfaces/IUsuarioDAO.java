/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EntidadChat;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author LuisaM
 */
public interface IUsuarioDAO {
    boolean registrarUsuario(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario iniciarSesion(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarNombre(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarContrasena(EntidadUsuario usuario, EntidadUsuario usuarioActualizado)throws PersistenciaException;
    boolean actualizarFechaNacimiento(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarSexo(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarDireccion(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarTelefono(EntidadUsuario usuario)throws PersistenciaException;
    boolean actualizarImagenPerfil(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario obtenerUsuario(EntidadUsuario usuario)throws PersistenciaException;
    List<EntidadUsuario> obtenerUsuarios(EntidadUsuario usuario)throws PersistenciaException;
    Set<EntidadChat> buscarChats(EntidadUsuario usuario)throws PersistenciaException; 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author LuisaM
 */
public interface IUsuarioDAO {
    EntidadUsuario registrarUsuario(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarNombre(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarContrasena(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarFechaNacimiento(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarSexo(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarDireccion(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario actualizarTelefono(EntidadUsuario usuario)throws PersistenciaException;
    EntidadUsuario obtenerUsuario(EntidadUsuario usuario)throws PersistenciaException;
    List<EntidadUsuario> obtenerUsuarios()throws PersistenciaException;
}

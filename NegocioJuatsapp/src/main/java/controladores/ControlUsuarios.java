/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DTOs.UsuarioDTO;
import daoColecciones.UsuarioDAO;
import entidades.Direccion;
import entidades.EntidadUsuario;
import entidades.SexoEnum;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author LuisaM
 */
public class ControlUsuarios {
    private IUsuarioDAO dao;
    private ObjectId idUsuario;
    
    public ControlUsuarios(){
        dao=new UsuarioDAO();
    }
    
    public boolean registrarUsuario(UsuarioDTO usuario)throws NegocioException{
        try {
            //si ya hay un usuario con ese telefono registrado
            if(obtenerUsuario(usuario)!=null)return false;
            //si no, se registra
            if(Validador.validar(Validador.NOMBRE, usuario.getNombre())){
                return dao.registrarUsuario(Convertidor.crearEntidadUsuario(usuario));
            }
            return false;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public UsuarioDTO iniciarSesion(UsuarioDTO usuario)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario(usuario.getTelefono(), usuario.getContrasena());
            u=dao.iniciarSesion(u);
            if(u!=null){
                idUsuario=u.getId();
                UsuarioDTO dto= Convertidor.crearUsuarioDTO(u);
                dto.setContrasena(usuario.getContrasena());
                return dto;
            }return null;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public UsuarioDTO obtenerUsuario(UsuarioDTO usuario)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setTelefono(usuario.getTelefono());
            u=dao.obtenerUsuario(u);
            if(u!=null){
                return Convertidor.crearUsuarioDTO(u);
            }return null;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public List<UsuarioDTO> obtenerUsuarios(UsuarioDTO usuario)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setTelefono(usuario.getTelefono());
            List<EntidadUsuario> usuarios=dao.obtenerUsuarios(u);
            if(!usuarios.isEmpty()){
                return Convertidor.crearListaUsuariosDTO(usuarios);
            }return null;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarNombreUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            u.setNombre(usuarioOriginal.getNombre());
            return dao.actualizarNombre(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarContrasenaUsuario(UsuarioDTO usuarioOriginal, UsuarioDTO usuarioActualizado)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            u.setContrasena(usuarioActualizado.getContrasena());
            return dao.actualizarContrasena(
                    new EntidadUsuario(usuarioOriginal.getTelefono(), usuarioOriginal.getContrasena()),
                    u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarDireccionUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            String[] dirStr=usuarioOriginal.getDireccion().split(";");
            Direccion direccion=new Direccion(dirStr[0], dirStr[1], dirStr[2]);
            u.setDireccion(direccion);
            return dao.actualizarDireccion(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarTelefonoUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            u.setTelefono(usuarioOriginal.getTelefono());
            return dao.actualizarTelefono(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarSexoUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            SexoEnum sexo=null;
            switch (usuarioOriginal.getSexo()) {
                case "hombre" ->sexo = SexoEnum.HOMBRE;
                case "mujer" ->sexo = SexoEnum.MUJER;
                case "otro" ->sexo = SexoEnum.OTRO;
            }
            u.setSexo(sexo);
            return dao.actualizarSexo(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarFechaNacimientoUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            u.setFechaNacimiento(usuarioOriginal.getFechaNacimiento());
            return dao.actualizarFechaNacimiento(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean editarNombreImagenUsuario(UsuarioDTO usuarioOriginal)throws NegocioException{
        try {
            EntidadUsuario u=new EntidadUsuario();
            u.setId(this.idUsuario);
            u.setFechaNacimiento(usuarioOriginal.getFechaNacimiento());
            return dao.actualizarImagenPerfil(u);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
}

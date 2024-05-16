/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DTOs.ChatDTO;
import DTOs.UsuarioDTO;
import entidades.Direccion;
import entidades.EntidadChat;
import entidades.EntidadUsuario;
import entidades.SexoEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author LuisaM
 */
public class Convertidor {
    
    protected static UsuarioDTO crearUsuarioDTO(EntidadUsuario entidadUsuario){
        UsuarioDTO usuario= new UsuarioDTO();
        usuario.setNombre(entidadUsuario.getNombre());
        //usuario.setContrasena(entidadUsuario.getContrasena());
        
        Direccion direccion=entidadUsuario.getDireccion();
        String dirStr=direccion.getCalle()+";"+direccion.getColonia()+";"+direccion.getNumero();
        usuario.setDireccion(dirStr);
        usuario.setFechaNacimiento(entidadUsuario.getFechaNacimiento());
        usuario.setNombreImagenPerfil(entidadUsuario.getNombreImagenPerfil());
        usuario.setTelefono(entidadUsuario.getTelefono());
        if(!entidadUsuario.getChats().isEmpty())
            usuario.setChats(crearChatsDTO(entidadUsuario.getChats()));
        usuario.setSexo(entidadUsuario.getSexo().toString());
        return usuario;
    }
    
    private static Set<ChatDTO> crearChatsDTO(Set<EntidadChat> setChatsEntidad){
        Set<ChatDTO> chats=new TreeSet<>();
        for (EntidadChat entidadChat : setChatsEntidad) {
            ChatDTO dto=new ChatDTO();
            dto.setNombreContacto(entidadChat.getContacto().getNombre());
            dto.setNombreImagenContacto(entidadChat.getContacto().getNombreImagenPerfil());
            dto.setTelefonoContacto(entidadChat.getContacto().getTelefono());
            dto.setUltimaActualizacion(entidadChat.getUltimaActualizacion());
            chats.add(dto);
        }
        return chats;
    }
    
    protected static List<UsuarioDTO> crearListaUsuariosDTO(List<EntidadUsuario> usuarios){
        List<UsuarioDTO> usuariosDTO=new ArrayList<>();
        UsuarioDTO dto;
        for (EntidadUsuario usuario : usuarios) {
            dto=new UsuarioDTO();
            dto.setNombre(usuario.getNombre());
            dto.setTelefono(usuario.getTelefono());
            dto.setNombreImagenPerfil(usuario.getNombreImagenPerfil());
            usuariosDTO.add(dto);
        }
        return usuariosDTO;
    }
    
    protected static EntidadUsuario crearEntidadUsuario(UsuarioDTO usuarioDTO){
        String[] dirArr=usuarioDTO.getDireccion().split(";");
        Direccion direccion=new Direccion(dirArr[0], dirArr[1], dirArr[2]);
        SexoEnum sexo=null;
        switch (usuarioDTO.getSexo()) {
            case "hombre" -> sexo=SexoEnum.HOMBRE;
            case "mujer" -> sexo=SexoEnum.MUJER;
            case "otro" -> sexo=SexoEnum.OTRO;
        }
        return new EntidadUsuario(usuarioDTO.getNombre(), usuarioDTO.getTelefono(), 
                usuarioDTO.getContrasena(), usuarioDTO.getNombreImagenPerfil(),
                direccion, usuarioDTO.getFechaNacimiento(), sexo);
    }
}

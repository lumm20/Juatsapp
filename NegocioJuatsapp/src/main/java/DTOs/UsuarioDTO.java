/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author LuisaM
 */
public class UsuarioDTO {
    private String nombre;
    private String telefono;
    private String contrasena;
    private String nombreImagenPerfil;
    private String direccion;
    private LocalDateTime fechaNacimiento;
    private String sexo;
    private Set<ChatDTO> chats;

    public UsuarioDTO(){
        this.chats=new TreeSet<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreImagenPerfil() {
        return nombreImagenPerfil;
    }

    public void setNombreImagenPerfil(String nombreImagenPerfil) {
        this.nombreImagenPerfil = nombreImagenPerfil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<ChatDTO> getChats() {
        return chats;
    }

    public void setChats(Set<ChatDTO> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsuarioDTO{");
        sb.append("nombre=").append(nombre);
        sb.append(", telefono=").append(telefono);
        sb.append(", contrasena=").append(contrasena);
        sb.append(", nombreImagenPerfil=").append(nombreImagenPerfil);
        sb.append(", direccion=").append(direccion);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", sexo=").append(sexo);
        sb.append(", cantidad de chats=").append(chats.size());
        sb.append('}');
        return sb.toString();
    }
    
    public String chatsToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Chats[\n");
        for (ChatDTO chat : chats) {
            sb.append("nombre=").append(chat.getNombreContacto());
            sb.append(", telefono=").append(chat.getTelefonoContacto()).append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}

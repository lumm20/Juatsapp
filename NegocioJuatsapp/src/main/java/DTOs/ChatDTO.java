/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author LuisaM
 */
public class ChatDTO {
    private String nombreContacto;
    private String telefonoContacto;
    private String nombreImagenContacto;
    private Queue<MensajeDTO> mensajes;
    private LocalDateTime ultimaActualizacion;

    public ChatDTO(){
        this.mensajes=new LinkedList<>();
    }
    
    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombreImagenContacto() {
        return nombreImagenContacto;
    }

    public void setNombreImagenContacto(String nombreImagenContacto) {
        this.nombreImagenContacto = nombreImagenContacto;
    }

    public Queue<MensajeDTO> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Queue<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }

    public void agregarMensaje(MensajeDTO mensaje){
        this.mensajes.add(mensaje);
    }
    
    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chat {");
        sb.append("nombreContacto=").append(nombreContacto);
        sb.append(", telefonoContacto=").append(telefonoContacto);
        sb.append(", nombreImagenContacto=").append(nombreImagenContacto);
        sb.append(", cantidad de mensajes=").append(mensajes.size());
        sb.append(", ultimaActualizacion=").append(ultimaActualizacion);
        sb.append('}');
        return sb.toString();
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 *
 * @author LuisaM
 */
public class MensajeDTO {
    private UsuarioDTO remitente;
    private ChatDTO chat;
    private LocalDateTime fechaHora;
    private String texto;
    private String nombreImagen;

    public UsuarioDTO getRemitente() {
        return remitente;
    }

    public void setRemitente(UsuarioDTO remitente) {
        this.remitente = remitente;
    }

    public ChatDTO getChat() {
        return chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MensajeDTO[");
        sb.append("remitente{");
        sb.append("nombre=").append(remitente.getNombre());
        sb.append(", chat=").append(chat);
        sb.append(", fechaHora=").append(fechaHora);
        sb.append(", texto=").append(texto);
        sb.append(", nombreImagen=").append(nombreImagen);
        sb.append(']');
        return sb.toString();
    }
    
    
}

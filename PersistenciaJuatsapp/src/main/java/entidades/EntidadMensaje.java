/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.bson.types.ObjectId;

/**
 *
 * @author LuisaM
 */
    public class EntidadMensaje implements Serializable{
    private ObjectId id;
    private EntidadUsuario remitente;
    private EntidadChat chat;
    private Date fechaHora;
    private String texto;
    private String nombreImagen;

    public EntidadMensaje() {
    }

    public EntidadMensaje(EntidadUsuario remitente, EntidadChat chat, Date fechaHora, String texto, String nombreImagen) {
        this.remitente = remitente;
        this.chat = chat;
        Calendar fecha=Calendar.getInstance(TimeZone.getTimeZone("America/Arizona"));
        fecha.setTime(fechaHora);
        this.fechaHora = fecha.getTime();
        this.texto = texto;
        this.nombreImagen = nombreImagen;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public EntidadUsuario getRemitente() {
        return remitente;
    }

    public void setRemitente(EntidadUsuario remitente) {
        this.remitente = remitente;
    }

    public EntidadChat getChat() {
        return chat;
    }

    public void setChat(EntidadChat chat) {
        this.chat = chat;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        Calendar fecha=Calendar.getInstance(TimeZone.getTimeZone("America/Arizona"));
        fecha.setTime(fechaHora);
        this.fechaHora = fecha.getTime();
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
        sb.append("EntidadMensaje{");
        sb.append("id=").append(id);
        sb.append(", remitente=").append(remitente.toStringCorto());
        sb.append(", chat=").append(chat);
        sb.append(", fechaHora=").append(fechaToString());
        if(texto!=null)
            sb.append(", texto=").append(texto);
        if(nombreImagen!=null)
            sb.append(", nombreImagen=").append(nombreImagen);
        sb.append('}');
        return sb.toString();
    }
    
    
    public String fechaToString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
        return formatoFecha.format(fechaHora);
    }
}

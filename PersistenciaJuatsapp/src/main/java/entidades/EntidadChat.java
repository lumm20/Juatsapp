/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author LuisaM
 */
public class EntidadChat implements Serializable{
    private ObjectId id;
    private String rutaImagen;
    private EntidadUsuario contacto;
    private EntidadUsuario creador;
    private List<ObjectId> mensajes;

    public EntidadChat() {
        mensajes=new ArrayList<>();
    }

    public EntidadChat(String rutaImagen, EntidadUsuario contacto, EntidadUsuario creador) {
        this.rutaImagen = rutaImagen;
        this.contacto = contacto;
        this.creador = creador;
        this.mensajes=new ArrayList<>();
    }

    public List<ObjectId> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<ObjectId> mensajes) {
        this.mensajes = mensajes;
    }

    public void agregarMensaje(ObjectId idMensaje){
        this.mensajes.add(idMensaje);
    }
    
    public void vaciarChat(){
        this.mensajes.clear();
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public EntidadUsuario getContacto() {
        return contacto;
    }

    public void setContacto(EntidadUsuario contacto) {
        this.contacto = contacto;
    }

    public EntidadUsuario getCreador() {
        return creador;
    }

    public void setCreador(EntidadUsuario creador) {
        this.creador = creador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadChat{");
        sb.append("id=").append(id);
        sb.append(", rutaImagen=").append(rutaImagen);
        sb.append(", contacto=").append(contacto.toStringCorto());
        sb.append(", creador=").append(creador.toStringCorto());
        if(!mensajes.isEmpty())
            sb.append(", mensajes[").append(printMensajes()).append(']');
        sb.append('}');
        return sb.toString();
    }
    
    private String printMensajes(){
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (ObjectId obId : mensajes){
            if(mensajes.indexOf(obId)>0 || mensajes.indexOf(obId)<mensajes.size()-1)
                sb.append(", ");
            sb.append("idMensaje=").append(id.toHexString());
//            if(mensaje.getTexto()!=null)
//                sb.append("texto=").append(mensaje.getTexto()).append(", ");
//            if(mensaje.getNombreImagen()!=null)
//                sb.append("imagen=").append(mensaje.getNombreImagen()).append(", ");;
//            sb.append(", remitente=").append(mensaje.getRemitente().getNombre());
        }
        sb.append('}');
        return sb.toString();
    }
}

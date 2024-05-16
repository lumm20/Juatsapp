/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;
import java.util.TimeZone;

/**
 *
 * @author LuisaM
 */
public class EntidadChat implements Serializable{
    private ObjectId id;
    private EntidadUsuario contacto;
    private ObjectId creador;
    private List<ObjectId> mensajes;
    //private List<EntidadMensaje> mensajes2;
    private LocalDateTime ultimaActualizacion;

    public EntidadChat() {
        mensajes=new ArrayList<>();
//        mensajes2=new ArrayList<>();
        ultimaActualizacion=LocalDateTime.now();
    }

    public EntidadChat(String rutaImagen, EntidadUsuario contacto, ObjectId creador) {
        //this.rutaImagen = rutaImagen;
        this.contacto = contacto;
        this.creador = creador;
        this.mensajes=new ArrayList<>();
//        this.mensajes2=new ArrayList<>();
        ultimaActualizacion=LocalDateTime.now();
    }

//    public List<EntidadMensaje> getMensajes2() {
//        return mensajes2;
//    }
//
//    public void setMensajes2(List<EntidadMensaje> mensajes2) {
//        this.mensajes2 = mensajes2;
//    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
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
    
//    public void agregarMensaje(EntidadMensaje mensaje){
//        this.mensajes2.add(mensaje);
//    }
//    
    public void vaciarChat(){
        this.mensajes.clear();
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

//    public String getRutaImagen() {
//        return rutaImagen;
//    }
//
//    public void setRutaImagen(String rutaImagen) {
//        this.rutaImagen = rutaImagen;
//    }

    public EntidadUsuario getContacto() {
        return contacto;
    }

    public void setContacto(EntidadUsuario contacto) {
        this.contacto = contacto;
    }

    public ObjectId getCreador() {
        return creador;
    }

    public void setCreador(ObjectId creador) {
        this.creador = creador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadChat{");
        sb.append("id=").append(id);
//        sb.append(", rutaImagen=").append(rutaImagen);
        sb.append(", contacto=").append(contacto.toStringCorto());
        sb.append(", creador=").append(creador.toHexString());
        if(!mensajes.isEmpty())
            sb.append(", mensajes[").append(printMensajes()).append(']');
//        if(!mensajes2.isEmpty())
//            sb.append(", mensajes2[").append(printMensajes2()).append(']');
        sb.append(", ultima actualizacion=").append(ultimaActualizacion);
        sb.append('}');
        return sb.toString();
    }
    
    public String fechaToString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
        return formatoFecha.format(ultimaActualizacion);
    }
    
    public String toStringCorto(){
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("id creador=").append(creador.toHexString());
//        sb.append("nombre=").append(creador.getNombre());
//        sb.append(", telefono=").append(creador.getTelefono()).append('}');
        sb.append(", contacto{");
        sb.append("nombre=").append(contacto.getNombre());
        sb.append(", telefono=").append(contacto.getTelefono()).append('}');
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
//    private String printMensajes2(){
//        StringBuilder sb = new StringBuilder();
//        sb.append('[');
//        for (EntidadMensaje mensaje : mensajes2){
//            sb.append('{');
//            if(mensajes2.indexOf(mensaje)>0 || mensajes2.indexOf(mensaje)<mensajes2.size()-1)
//                sb.append(", ");
//            if(mensaje.getTexto()!=null)
//                sb.append("texto=").append(mensaje.getTexto()).append(", ");
////            if(mensaje.getNombreImagen()!=null)
////                sb.append("imagen=").append(mensaje.getNombreImagen()).append(", ");;
//            sb.append("remitente=").append(mensaje.getRemitente().getNombre()).append('}');
//        }
//        sb.append(']');
//        return sb.toString();
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compara el id de dos chats para evaluar si son iguales
     * @param obj El objeto a comparar
     * @return True si tienen el mismo id, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntidadChat other = (EntidadChat) obj;
        return Objects.equals(this.id, other.id);
    }
    
    public static class ComparadorChat implements Comparator<EntidadChat> {

        @Override
        public int compare(EntidadChat chat1, EntidadChat chat2) {
            return chat2.getUltimaActualizacion().compareTo(chat1.getUltimaActualizacion());
        }
    }
}

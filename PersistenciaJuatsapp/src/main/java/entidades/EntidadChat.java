/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
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
    private List<EntidadMensaje>

    public EntidadChat(String rutaImagen, EntidadUsuario contacto, EntidadUsuario creador) {
        this.rutaImagen = rutaImagen;
        this.contacto = contacto;
        this.creador = creador;
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
}

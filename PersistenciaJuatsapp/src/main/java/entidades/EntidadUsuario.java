/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import entidades.EntidadChat.ComparadorChat;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import org.bson.types.ObjectId;

/**
 *
 * @author LuisaM
 */
public class EntidadUsuario implements Serializable{
    private ObjectId id;
    private String nombre;
    private String telefono;
    private String contrasena;
    private String nombreImagenPerfil;
    private Direccion direccion;
    private LocalDateTime fechaNacimiento;
    private SexoEnum sexo;
    private Set<EntidadChat> chats;
    
    public EntidadUsuario() {
        chats=new TreeSet<>(new ComparadorChat());
    }

    public EntidadUsuario(String telefono, String contrasena) {
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public EntidadUsuario(String nombre, String telefono, String contrasena,String nombreImagenPerfil,
            Direccion direccion, LocalDateTime fechaNacimiento, SexoEnum sexo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.nombreImagenPerfil = nombreImagenPerfil;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.chats=new TreeSet<>(new ComparadorChat());
    }

    public String getNombreImagenPerfil() {
        return nombreImagenPerfil;
    }

    public void setNombreImagenPerfil(String nombreImagenPerfil) {
        this.nombreImagenPerfil = nombreImagenPerfil;
    }

    public Set<EntidadChat> getChats() {
        return chats;
    }

    public void setChats(Set<EntidadChat> chats) {
        this.chats = chats;
    }

    public void agregarChat(EntidadChat chat){
        this.chats.add(chat);
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdString(){
        if(id!=null)
            return this.id.toHexString();
        return null;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public void actualizarFechaUltimaActualizacion(EntidadChat chat, LocalDateTime nuevaFecha) {
        if (chats.remove(chat)) {
            chat.setUltimaActualizacion(nuevaFecha); 
            chats.add(chat);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadUsuario{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", telefono=").append(telefono);
        sb.append(", contrasena=").append(contrasena);
        if(nombreImagenPerfil!=null)
            sb.append(", imagen=").append(nombreImagenPerfil);
        sb.append(", direccion=").append(direccion);
        if(fechaNacimiento!=null)
            sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", sexo=").append(sexo);
        sb.append('}');
        return sb.toString();
    }
    
    public String toStringCorto(){
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("nombre=").append(nombre);
        sb.append(", telefono=").append(telefono);
        sb.append('}');
        return sb.toString();
    }
    
    public String fechaToString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        //formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
        return formatoFecha.format(fechaNacimiento);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import entidades.EntidadChat;
import entidades.EntidadMensaje;
import excepciones.PersistenciaException;
import interfaces.IMensajeDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

/**
 *
 * @author LuisaM
 */
public class MensajeDAO implements IMensajeDAO{

    private final MongoCollection<EntidadMensaje> coleccion;
    private final static Logger LOG = Logger.getLogger(MensajeDAO.class.getName());

    public MensajeDAO() {
        this.coleccion = Conexion.getDatabase().getCollection("Mensajes", EntidadMensaje.class);
    }
    
    @Override
    public EntidadMensaje guardarMensaje(EntidadMensaje mensaje) throws PersistenciaException {
        try {
            InsertOneResult result=coleccion.insertOne(mensaje);
            if(result.getInsertedId()!=null){
                mensaje.setId(result.getInsertedId().asObjectId().getValue());
                return mensaje;
            }
            LOG.log(Level.SEVERE,"no se guardo el mensaje");
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al guardar el mensaje");
        }
    }

    @Override
    public EntidadMensaje editarMensaje(EntidadMensaje mensaje) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EntidadMensaje eliminarMensaje(EntidadMensaje mensaje) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EntidadMensaje buscarMensaje(EntidadMensaje mensaje) throws PersistenciaException {
        try {
            return coleccion.find(Filters.eq("_id", mensaje.getId())).first();
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar el mensaje");
        }
    }

    @Override
    public List<EntidadMensaje> buscarMensajes() throws PersistenciaException {
        try {
            List<EntidadMensaje> mensajes=new ArrayList<>();
            return coleccion.find().into(mensajes);
        } catch (MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los mensajes");
        }
    }
    
    @Override
    public List<EntidadMensaje> buscarMensajes(EntidadChat chat) throws PersistenciaException {
        try {
            List<EntidadMensaje> mensajes=new ArrayList<>();
            chat.getMensajes().forEach(i->{
                mensajes.add(coleccion.find(Filters.eq("_id", i)).first());
            });
            return mensajes;
//            Bson filtro = Filters.and(
//                    Filters.eq("chat.creador.telefono", chat.getCreador().getTelefono()),
//                    Filters.eq("chat.contacto.telefono", chat.getContacto().getTelefono())
//            );
//            return coleccion.find(filtro).into(mensajes);
        } catch (MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los mensajes");
        }
    }

    @Override
    public List<EntidadMensaje> buscarMensajesFiltrados(EntidadMensaje mensajeFiltrado) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

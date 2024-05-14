/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entidades.EntidadChat;
import entidades.EntidadMensaje;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import interfaces.IMensajeDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
    public EntidadMensaje editarMensaje(EntidadMensaje mensaje, EntidadMensaje mensajeNuevo) throws PersistenciaException {
        try {
            UpdateResult result=coleccion.updateOne(Filters.eq("_id", mensaje.getId()), 
                    Updates.set("texto", mensajeNuevo.getTexto()));
            if(result.getModifiedCount()>0){
                mensaje.setTexto(mensajeNuevo.getTexto());
                return mensaje;
            }
            LOG.log(Level.SEVERE,"no se edito el mensaje");
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al editar el mensaje");
        }
    }

    @Override
    public boolean eliminarMensaje(EntidadMensaje mensaje) throws PersistenciaException {
        try {
            DeleteResult result=coleccion.deleteOne(Filters.eq("_id", mensaje.getId()));
            return result.getDeletedCount()>0;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al borrar el mensaje");
        }
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
    public List<EntidadMensaje> buscarMensajes(EntidadUsuario usuario) throws PersistenciaException {
        try {
            List<EntidadMensaje> mensajes=new ArrayList<>();
            return coleccion.find(Filters.and(
                    Filters.eq("remitente.nombre", usuario.getNombre()),
                    Filters.eq("remitente.telefono", usuario.getTelefono()))).into(mensajes);
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
        } catch (MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los mensajes");
        }
    }

    @Override
    public List<EntidadMensaje> buscarMensajesFiltrados(EntidadChat chat,EntidadMensaje mensajeFiltrado) throws PersistenciaException {
        try {
            List<Bson> pipeline=new ArrayList<>();
            Pattern pattern=Pattern.compile(mensajeFiltrado.getTexto(), Pattern.CASE_INSENSITIVE);
            pipeline.add(Aggregates.match(Filters.regex("texto", pattern)));
            pipeline.add(Aggregates.lookup("Chats", "_id", "mensajes._id", "chatJoin"));
            pipeline.add(Aggregates.unwind("$chatJoin"));
            pipeline.add(Aggregates.project( Projections.fields(
                    Projections.include("texto","fechaHora","remitente.nombre"),
                    Projections.computed("chat", "$chatJoin.contacto")
            )
            ));
            AggregateIterable<EntidadMensaje> aggregate=coleccion.aggregate(pipeline);
            if(aggregate.first()!=null){
                List<EntidadMensaje> mensajes=new ArrayList<>();
                aggregate.into(mensajes);
                return mensajes;
            }return null;
        } catch (MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los mensajes");
        }
    }
    
    @Override
    public List<EntidadMensaje> buscarMensajesFiltrados(EntidadMensaje mensajeFiltrado) throws PersistenciaException {
        try {
            List<EntidadMensaje> todosLosMensajes=new ArrayList<>();
            return coleccion.find(Filters.eq("texto", mensajeFiltrado.getTexto())).into(todosLosMensajes);
        } catch (MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los mensajes");
        }
    }
    
}

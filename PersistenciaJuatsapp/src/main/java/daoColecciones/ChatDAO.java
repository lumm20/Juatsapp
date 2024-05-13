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
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entidades.EntidadChat;
import entidades.EntidadMensaje;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import interfaces.IChatDAO;
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
public class ChatDAO implements IChatDAO{

    private final MongoCollection<EntidadChat> coleccion;
    private final static Logger LOG = Logger.getLogger(ChatDAO.class.getName());

    public ChatDAO() {
        this.coleccion = Conexion.getDatabase().getCollection("Chats", EntidadChat.class);
    }
    
    @Override
    public EntidadChat agregarChat(EntidadChat chat) throws PersistenciaException {
        try {
            InsertOneResult result=coleccion.insertOne(chat);
            if (result.getInsertedId() != null)return chat;
            LOG.log(Level.SEVERE, "No se creo el chat");
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al crear el chat");
        }
    }
    
    @Override
    public EntidadChat editarContacto(EntidadChat chat,EntidadUsuario contactoEditado) throws PersistenciaException {
        EntidadChat c=chat;
        try {
            Bson filtro = Filters.and(
                    Filters.eq("creador.telefono", chat.getCreador().getTelefono()),
                    Filters.eq("contacto.telefono", chat.getContacto().getTelefono())
            );
            UpdateResult result = coleccion.updateOne(filtro, Updates.set("contacto", contactoEditado));
            if (result.getModifiedCount() > 0) {
                c.setContacto(contactoEditado);
                return c;
            }
            LOG.log(Level.SEVERE, "No se edito el contacto");
            return null;
//            List<Bson> pipeline=new ArrayList<>();
//            pipeline.add(Aggregates.match(Filters.eq("creador.telefono", chat.getCreador().getTelefono())));
//            pipeline.add(Aggregates.match(Filters.eq("contacto.telefono", chat.getContacto().getTelefono())));
//            //pipeline.add(Aggregates.pr);
//            pipeline.add(Aggregates.set(new Field("contacto", contactoEditado)));
//            AggregateIterable<EntidadChat> aggregate=coleccion.aggregate(pipeline);
//            if(aggregate.first()!=null){
//                EntidadChat chatEditado=aggregate.first();
//                return chatEditado;
//            }
//            LOG.log(Level.SEVERE, "No se edito el contacto");
//            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al editar el contacto");
        }
    }

    @Override
    public boolean eliminarChat(EntidadChat chat) throws PersistenciaException {
        try {
            InsertOneResult result=coleccion.insertOne(chat);
            return result.getInsertedId() != null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al eliminar el chat");
        }
    }
    @Override
    public EntidadChat agregarMensaje(EntidadChat chat, EntidadMensaje mensaje) throws PersistenciaException {
        IMensajeDAO mensajeDao=new MensajeDAO();
        EntidadChat c=chat;
        try {
            Bson filtro = Filters.and(
                    Filters.eq("creador.telefono", chat.getCreador().getTelefono()),
                    Filters.eq("contacto.telefono", chat.getContacto().getTelefono())
            );
            mensaje.setChat(chat);
            EntidadMensaje m=mensajeDao.guardarMensaje(mensaje);
            if(m!=null){
                UpdateResult result = coleccion.updateOne(filtro, Updates.push("mensajes", m.getId()));
                if (result.getModifiedCount()>0){
                    c.agregarMensaje(m.getId());
                    return c;
                }else LOG.log(Level.SEVERE,"no se actualizo el chat");
            }
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al eliminar el chat");
        }
    }

    @Override
    public EntidadChat vaciarChat(EntidadChat chat) throws PersistenciaException {
        EntidadChat c=chat;
        try {
            Bson filtro=Filters.and(
                    Filters.eq("creador.telefono", chat.getCreador().getTelefono()),
                    Filters.eq("contacto.telefono", chat.getContacto().getTelefono())
                    );
            UpdateResult result=coleccion.updateOne(filtro, Updates.pull("mensajes", ""));
            if(result.getModifiedCount()>0){
                c.vaciarChat();
                return c;
            }
            LOG.log(Level.SEVERE, "No se vacio el chat");
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al vaciar el chat");
        }
    }

    @Override
    public EntidadChat buscarChat(EntidadChat chat) throws PersistenciaException {
        try {
            Bson filtro=Filters.and(
                    Filters.eq("creador.telefono", chat.getCreador().getTelefono()),
                    Filters.eq("contacto.telefono", chat.getContacto().getTelefono())
                    );
            EntidadChat c= coleccion.find(filtro).first();
            if(c!=null)return c;
            LOG.log(Level.SEVERE, "No se encontro el chat");
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar el chat");
        }
    }

    @Override
    public List<EntidadChat> buscarChats(EntidadUsuario usuario) throws PersistenciaException {
        try {
            Bson filtro=Filters.eq("creador.telefono", usuario.getTelefono());
            List<EntidadChat> chats=new ArrayList<>();
            coleccion.find(filtro).into(chats);
            if(!chats.isEmpty())return chats;
            LOG.log(Level.SEVERE, "No hay chats");
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar el chat");
        }
    }
    
}

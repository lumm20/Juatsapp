/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import entidades.EntidadChat;
import excepciones.PersistenciaException;
import interfaces.IChatDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al crear el chat");
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
    public EntidadChat vaciarChat(EntidadChat chat) throws PersistenciaException {
        try {
            
        } catch (Exception e) {
        }
    }

    @Override
    public EntidadChat buscarChat(EntidadChat chat) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntidadChat> buscarChats() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

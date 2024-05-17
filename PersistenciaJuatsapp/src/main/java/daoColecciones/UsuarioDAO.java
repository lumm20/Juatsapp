/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import encriptado.Encriptacion;
import entidades.EntidadChat;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author LuisaM
 */
public class UsuarioDAO implements IUsuarioDAO{
    private final MongoCollection<EntidadUsuario> coleccion;
    private final static Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
    private final encriptado.Encriptacion crypt;
    
    public UsuarioDAO() {
        this.coleccion = Conexion.getDatabase().getCollection("Usuarios", EntidadUsuario.class);
        coleccion.createIndex(Indexes.hashed("contrasena"),new IndexOptions().name("contrasenaIndex"));
        crypt=new Encriptacion();
    }

    @Override
    public boolean registrarUsuario(EntidadUsuario usuario) throws PersistenciaException {
        try {
            usuario.setContrasena(crypt.genrarHash(usuario.getContrasena()));
            InsertOneResult result = coleccion.insertOne(usuario);  
            return result.getInsertedId() != null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al registrar el usuario");
        }
    }

    @Override
    public EntidadUsuario iniciarSesion(EntidadUsuario usuario) throws PersistenciaException {
        try {
            EntidadUsuario u=coleccion.find(Filters.eq("telefono",usuario.getTelefono())).first();
            //si la contraseña es correcta
            if(u!=null){
                if (crypt.verificarContra(usuario.getContrasena(), u.getContrasena())) 
                    return u;
                return null;//si la contra no es correcta
            }
            throw new PersistenciaException("No hay un usuario registrado con ese telefono");
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al iniciar sesion");
        }
    }
    
    @Override
    public EntidadUsuario obtenerUsuario(EntidadUsuario usuario) throws PersistenciaException {
        try {
            EntidadUsuario u=coleccion.find(Filters.eq("telefono",usuario.getTelefono())).first();
            return u;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar el usuario");
        }
    }

    @Override
    public List<EntidadUsuario> obtenerUsuarios(EntidadUsuario usuario) throws PersistenciaException {
        try {
            List<EntidadUsuario> usuarios=new ArrayList<>();
            Pattern pattern=Pattern.compile("^"+usuario.getTelefono());
            coleccion.find(Filters.regex("telefono", pattern)).into(usuarios);
            return usuarios;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los usuarios");
        }
    }

    @Override
    public boolean actualizarNombre(EntidadUsuario usuario) throws PersistenciaException {
        //EntidadUsuario u=obtenerUsuario(usuario);
        //if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("nombre",usuario.getNombre()));
                return (result.getModifiedCount() > 0);
                    //usuario.setNombre(usuario.getNombre());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo el nombre");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el usuario");
            }
        //}
       // return null;
    }

    @Override
    public boolean actualizarContrasena(EntidadUsuario usuario, EntidadUsuario usuarioActualizado) throws PersistenciaException {
        //EntidadUsuario u=obtenerUsuario(usuario);
        // if(u!=null){
        if(iniciarSesion(usuario)!=null){
            Bson filtro = Filters.eq("_id", usuarioActualizado.getId());
            UpdateResult result;
            try {
                String contra=crypt.genrarHash(usuarioActualizado.getContrasena());
                result = coleccion.updateOne(filtro, Updates.set("contrasena", contra));
                return (result.getModifiedCount() > 0);
                    //usuarioActualizado.setContrasena(usuario.getContrasena());
//                    return usuarioActualizado;
//                }
                //throw new PersistenciaException("No se actualizo la contraseña");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la contraseña");
            }
        }throw new PersistenciaException("Contraseña incorrecta");
        // }
        // return null;
    }

    @Override
    public boolean actualizarFechaNacimiento(EntidadUsuario usuario) throws PersistenciaException {
//        EntidadUsuario u=obtenerUsuario(usuario);
//        if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("fechaNacimiento",usuario.getFechaNacimiento()));
                return (result.getModifiedCount() > 0);
                    //u.setFechaNacimiento(usuario.getFechaNacimiento());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo la fecha de nacimiento");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la fecha de nacimiento");
            }
//        }
//        return null;
    }

    @Override
    public boolean actualizarSexo(EntidadUsuario usuario) throws PersistenciaException {
//        EntidadUsuario u=obtenerUsuario(usuario);
//        if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("sexo",usuario.getSexo()));
                return (result.getModifiedCount() > 0);
                    //u.setSexo(usuario.getSexo());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo el sexo");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el sexo");
            }
//        }
//        return null;
    }

    @Override
    public boolean actualizarDireccion(EntidadUsuario usuario) throws PersistenciaException {
//        EntidadUsuario u=obtenerUsuario(usuario);
//        if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("direccion",usuario.getDireccion()));
                return (result.getModifiedCount() > 0) ;
                    //u.setDireccion(usuario.getDireccion());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo la direccion");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la direccion");
            }
//        }
//        return null;
    }

    @Override
    public boolean actualizarTelefono(EntidadUsuario usuario) throws PersistenciaException {
//        EntidadUsuario u=coleccion.find(Filters.eq("nombre", usuario.getNombre())).first();
//        if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("telefono",usuario.getTelefono()));
                return (result.getModifiedCount() > 0);
                    //u.setTelefono(usuario.getTelefono());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo el telefono");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el telefono");
            }
//        }
//        return null;
    }
    
    @Override
    public boolean actualizarImagenPerfil(EntidadUsuario usuario) throws PersistenciaException {
//        EntidadUsuario u=coleccion.find(Filters.eq("nombre", usuario.getNombre())).first();
//        if(u!=null){
            Bson filtro = Filters.eq("_id", usuario.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("nombreImagenPerfil",usuario.getNombreImagenPerfil()));
                return (result.getModifiedCount() > 0);
                    //u.setTelefono(usuario.getTelefono());
//                    return usuario;
//                }throw new PersistenciaException("No se actualizo el telefono");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la imagen del perfil");
            }
//        }
//        return null;
    }
    

    @Override
    public Set<EntidadChat> buscarChats(EntidadUsuario usuario) throws PersistenciaException {
        try {
            Set<EntidadChat> chats = new TreeSet<>();
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.eq("_id", usuario.getId())));
            pipeline.add(Aggregates.lookup("Chats", "mensajes._id", "_id", "chatsJoin"));
            pipeline.add(Aggregates.project(Projections.fields(
                    Projections.include("contacto", ""),
                    Projections.computed("id_mensajes", "$usuarioJoin.mensajes")
            )));
            AggregateIterable agg = coleccion.aggregate(pipeline);
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar el chat");
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entidades.EntidadUsuario;
import excepciones.PersistenciaException;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

/**
 *
 * @author LuisaM
 */
public class UsuarioDAO implements IUsuarioDAO{
    private final MongoCollection<EntidadUsuario> coleccion;
    private final static Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO() {
        this.coleccion = Conexion.getDatabase().getCollection("Usuarios", EntidadUsuario.class);
    }

    @Override
    public EntidadUsuario registrarUsuario(EntidadUsuario usuario) throws PersistenciaException {
        try {
            InsertOneResult result = coleccion.insertOne(usuario);
            if (result.getInsertedId() != null)return usuario;
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al registrar el usuario");
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
    public List<EntidadUsuario> obtenerUsuarios() throws PersistenciaException {
        try {
            List<EntidadUsuario> usuarios=new ArrayList<>();
            coleccion.find().into(usuarios);
            return usuarios;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaException("Hubo un error al buscar los usuarios");
        }
    }

    @Override
    public EntidadUsuario actualizarNombre(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=obtenerUsuario(usuario);
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("nombre",usuario.getNombre()));
                if (result.getModifiedCount() > 0) {
                    u.setNombre(usuario.getNombre());
                    return u;
                }throw new PersistenciaException("No se actualizo el usuario");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el usuario");
            }
        }
        return null;
    }

    @Override
    public EntidadUsuario actualizarContrasena(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=obtenerUsuario(usuario);
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("contrasena",usuario.getContrasena()));
                if (result.getModifiedCount() > 0) {
                    u.setContrasena(usuario.getContrasena());
                    return u;
                }throw new PersistenciaException("No se actualizo la contraseña");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la contraseña");
            }
        }
        return null;
    }

    @Override
    public EntidadUsuario actualizarFechaNacimiento(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=obtenerUsuario(usuario);
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("fechaNacimiento",usuario.getFechaNacimiento()));
                if (result.getModifiedCount() > 0) {
                    u.setFechaNacimiento(usuario.getFechaNacimiento());
                    return u;
                }throw new PersistenciaException("No se actualizo la fecha de nacimiento");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la fecha de nacimiento");
            }
        }
        return null;
    }

    @Override
    public EntidadUsuario actualizarSexo(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=obtenerUsuario(usuario);
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("sexo",usuario.getSexo()));
                if (result.getModifiedCount() > 0) {
                    u.setSexo(usuario.getSexo());
                    return u;
                }throw new PersistenciaException("No se actualizo el sexo");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el sexo");
            }
        }
        return null;
    }

    @Override
    public EntidadUsuario actualizarDireccion(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=obtenerUsuario(usuario);
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("direccion",usuario.getDireccion()));
                if (result.getModifiedCount() > 0) {
                    u.setDireccion(usuario.getDireccion());
                    return u;
                }throw new PersistenciaException("No se actualizo la direccion");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar la direccion");
            }
        }
        return null;
    }

    @Override
    public EntidadUsuario actualizarTelefono(EntidadUsuario usuario) throws PersistenciaException {
        EntidadUsuario u=coleccion.find(Filters.eq("nombre", usuario.getNombre())).first();
        if(u!=null){
            Bson filtro = Filters.eq("_id", u.getId());
            UpdateResult result;
            try {
                result = coleccion.updateOne(filtro, Updates.set("telefono",usuario.getTelefono()));
                if (result.getModifiedCount() > 0) {
                    u.setTelefono(usuario.getTelefono());
                    return u;
                }throw new PersistenciaException("No se actualizo el telefono");
            } catch (MongoException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                throw new PersistenciaException("Hubo un error al actualizar el telefono");
            }
        }
        return null;
    }
    
    
}

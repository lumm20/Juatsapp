/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lum.pruebas;

import daoColecciones.ChatDAO;
import daoColecciones.MensajeDAO;
import daoColecciones.UsuarioDAO;
import encriptado.Encriptacion;
import entidades.Direccion;
import entidades.EntidadChat;
import entidades.EntidadMensaje;
import entidades.EntidadUsuario;
import entidades.SexoEnum;
import excepciones.PersistenciaException;
import interfaces.IChatDAO;
import interfaces.IMensajeDAO;
import interfaces.IUsuarioDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author LuisaM
 */
public class PersistenciaJuatsapp {

    public static void main(String[] args) {
        IUsuarioDAO dao=new UsuarioDAO();
//-------------------Agregar usuario-------------
//        Calendar fechaNacimiento=Calendar.getInstance();
//        fechaNacimiento.set(2024, 10, 20, 0, 0, 0);
//        EntidadUsuario usuario=new EntidadUsuario("luisa", "6441445522", 
//                "1234", new Direccion("calle 1","colonia 1","1"), fechaNacimiento.getTime(), SexoEnum.MUJER);
//        try {
//            usuario=dao.registrarUsuario(usuario);
//            if(usuario!=null)System.out.println(usuario);
//        } catch (PersistenciaException e) {
//            System.out.println(e);
//        }
//-------------------Buscar usuario-------------
//        EntidadUsuario usuario2=new EntidadUsuario();
//        usuario2.setTelefono("6441445522");
//        try {
//            usuario2=dao.obtenerUsuario(usuario2);
//            if(usuario2!=null)System.out.println(usuario2);
//        } catch (PersistenciaException e) {
//            System.out.println(e);
//        }
//------------------Probando cifrado---------------------
//        interfaces.IEncriptacion cript=new Encriptacion();
//        String password =usuario2.getContrasena();
//        String hashedPassword = cript.genrarHash(password);
//        System.out.println("Contraseña encriptada: " + hashedPassword);
//
//        // Ejemplo de verificación de contraseña
//        String passwordToCheck = "1234";
//        if (cript.verificarContra(passwordToCheck, hashedPassword)) {
//            System.out.println("La contraseña es correcta");
//        } else {
//            System.out.println("La contraseña es incorrecta");
//        }
//-------------------Agregar chat-------------        
        IChatDAO cDao=new ChatDAO();
//        EntidadChat chat=new EntidadChat();
//        EntidadUsuario user1=new EntidadUsuario();
//        user1.setNombre("luisa");
//        user1.setTelefono("6441445522");
//        EntidadUsuario user2=new EntidadUsuario();
//        user2.setNombre("usuario 2");
//        user2.setTelefono("6442110022");
//        chat.setContacto(user2);
//        chat.setCreador(user1);
//        try {
//            chat=cDao.agregarChat(chat);
//            System.out.println("chat: "+chat);
//        } catch (PersistenciaException e) {
//            System.out.println(e);
//        }
//-------------------Buscar chat-------------        
        EntidadChat chat=new EntidadChat();
        EntidadUsuario user1=new EntidadUsuario();
        user1.setNombre("luisa");
        user1.setTelefono("6441445522");
        EntidadUsuario user2=new EntidadUsuario();
        user2.setNombre("usuario 2");
        user2.setTelefono("6442110022");
        chat.setContacto(user2);
        chat.setCreador(user1);
        try {
            chat=cDao.buscarChat(chat);
            System.out.println("chat: "+chat);
        } catch (PersistenciaException e) {
            System.out.println(e);
        }
//-------------------Agregar mensaje a chat------------- 
//        EntidadMensaje mensaje=new EntidadMensaje();
//        Calendar fecha=Calendar.getInstance();
//        mensaje.setFechaHora(fecha.getTime());
//        mensaje.setTexto("mensaje 1");
//        mensaje.setRemitente(user2);
//        try {
//            chat=cDao.agregarMensaje(chat, mensaje);
//            System.out.println("chat con mensaje: "+chat);
//        } catch (PersistenciaException e) {
//            System.out.println(e);
//        }
        //---------------buscar mensajes de un chat----------
        IMensajeDAO mDao=new MensajeDAO();
        try{
            List<EntidadMensaje> mensajes=mDao.buscarMensajes(chat);
            mensajes.forEach(m->{
                System.out.println(m);
            });
        }catch(PersistenciaException e){
            System.out.println(e);
        }
    }
}

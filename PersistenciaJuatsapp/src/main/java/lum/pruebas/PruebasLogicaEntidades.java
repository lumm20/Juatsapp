/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lum.pruebas;

import entidades.EntidadChat;
import entidades.EntidadMensaje;
import entidades.EntidadUsuario;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author LuisaM
 */
public class PruebasLogicaEntidades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntidadUsuario creador=new EntidadUsuario();
        creador.setNombre("luisa");
        
        EntidadUsuario contacto1=new EntidadUsuario();
        contacto1.setNombre("fernanda");
        
        EntidadUsuario contacto2=new EntidadUsuario();
        contacto2.setNombre("maria");
        
        EntidadChat chat=new EntidadChat("imagen.png", contacto1, creador.getId());
        
        LocalDateTime fecha1=LocalDateTime.of(2024, Month.MARCH, 19, 8, 0, 0);
        EntidadMensaje mensaje1=new EntidadMensaje(creador, chat,fecha1 ,
                "hola", null);
        
//        chat.agregarMensaje(mensaje1);
        System.out.println("primer mensaje:");
//        chat.getMensajes2().forEach(m->{
//            System.out.println("mensaje: "+m);
//        });
//        
        LocalDateTime fecha2=LocalDateTime.of(2024, Month.MARCH, 19, 9, 0, 0);
        EntidadMensaje mensaje2=new EntidadMensaje(contacto1, chat,fecha2 ,
                "hola que tal", null);
        
//        chat.agregarMensaje(mensaje2);
        System.out.println("mensajes actualizados:");
//        chat.getMensajes2().forEach(m->{
//            System.out.println("mensaje: "+m);
//        });
        chat.setUltimaActualizacion(mensaje2.getFechaHora());
        
        creador.agregarChat(chat);
        System.out.println("chats del creador v1");
        creador.getChats().forEach(c->{
            System.out.println("chat: "+c);
        });
        
        
        EntidadChat chat2=new EntidadChat("imagen2.png", contacto2, creador.getId());
        LocalDateTime fecha3=LocalDateTime.of(2024, Month.MAY, 28, 13, 30, 0);
        EntidadMensaje mensaje1Chat2=new EntidadMensaje(creador, chat2, fecha3, "holaa", null);
        
//        chat2.agregarMensaje(mensaje1Chat2);
        chat2.setUltimaActualizacion(mensaje1Chat2.getFechaHora());
        System.out.println("mensajes del chat 2:");
//        chat2.getMensajes2().forEach(m->{
//            System.out.println("mensaje: "+m);
//        });
        
        creador.agregarChat(chat2);
        System.out.println("chats del creador v2");
        creador.getChats().forEach(c->{
            System.out.println("chat: "+c);
        });
        
        EntidadUsuario contacto3=new EntidadUsuario();
        contacto3.setNombre("Josue");
        
        EntidadChat chat3=new EntidadChat("imagen2.png", contacto3, creador.getId());
        
        creador.agregarChat(chat3);
        System.out.println("chats del creador v3");
        creador.getChats().forEach(c->{
            System.out.println("chat: "+c);
        });
        
        LocalDateTime fecha4=LocalDateTime.of(2024, Month.JUNE, 4, 6, 40, 0);
        EntidadMensaje mensaje3Chat1=new EntidadMensaje(creador, chat, fecha4, "como estasss fer", null);
//        chat.agregarMensaje(mensaje3Chat1);
        chat.setUltimaActualizacion(mensaje3Chat1.getFechaHora());
        
        System.out.println("chats del creador v4");
        creador.getChats().forEach(c->{
            System.out.println("chat: "+c);
        });
        
    }
    
}

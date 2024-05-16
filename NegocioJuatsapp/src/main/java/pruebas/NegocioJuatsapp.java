/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pruebas;

import DTOs.UsuarioDTO;
import controladores.ControlUsuarios;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author LuisaM
 */
public class NegocioJuatsapp {

    public static void main(String[] args) {
        ControlUsuarios control=new ControlUsuarios();
        //primera prueba. Registre un usuario nuevo y 
        //luego inicie sesion con sus credenciales (telefono y contra)
//        UsuarioDTO user=new UsuarioDTO();
//        user.setTelefono("6441445522");
//        user.setContrasena("1234");
//        user.setDireccion("calle 1;colonia 1;1");
//        LocalDateTime fecha=LocalDateTime.of(2003, Month.NOVEMBER, 20, 0, 0, 0);
//        user.setFechaNacimiento(fecha);
//        user.setSexo("mujer");
//        user.setNombre("luisa morales");
//        user.setNombreImagenPerfil("imagen.png");
//        try {
//            if(control.registrarUsuario(user))
//                user=control.iniciarSesion(user);
//            else System.out.println("error al registrar");
//        } catch (NegocioException e) {
//            System.out.println(e);
//        }
//
//        if(user!=null)
//            System.out.println(user);
//        else System.out.println("fallido");
//        

        //--------prueba de actualizar un campo
//        UsuarioDTO user2=new UsuarioDTO();
//        user2.setTelefono("6441445522");
//        user2.setContrasena("1234");
//        
//        try {
//            user2=control.iniciarSesion(user2);
//        } catch (NegocioException e) {
//            System.out.println(e);
//        }
//        if(user2!=null){
//            user2.setNombre("Luisa Fernanda");
//            try {
//                if(control.editarNombreUsuario(user2))
//                    System.out.println("se edito");
//                else System.out.println("no se edito");
//            } catch (NegocioException e) {
//                System.out.println(e);
//            }
//        }
        
        //--------prueba de actualizar la contraseña
        
//        UsuarioDTO user3=new UsuarioDTO();
//        user3.setTelefono("6441445522");
//        user3.setContrasena("1234");
//        
//        try {
//            UsuarioDTO userLoggedIn=control.iniciarSesion(user3);
//            if(userLoggedIn!=null){
//                user3.setContrasena("1234");
//                userLoggedIn.setContrasena("meLlamoLuisa");
//                if(control.editarContrasenaUsuario(user3, userLoggedIn))
//                    System.out.println("se edito");
//                else System.out.println("no se edito");
//            }else
//                System.out.println("contraseña incorrecta");
//        } catch (NegocioException e) {
//            System.out.println(e);
//        }
//        
        //prueba de login
        UsuarioDTO user3=new UsuarioDTO();
        user3.setTelefono("6441445522");
        user3.setContrasena("meLlamoLuisa");
        
        try {
            user3=control.iniciarSesion(user3);
            if(user3!=null){
                System.out.println(user3);
            }else
                System.out.println("contraseña incorrecta");
        } catch (NegocioException e) {
            System.out.println(e);
        }
    }
}

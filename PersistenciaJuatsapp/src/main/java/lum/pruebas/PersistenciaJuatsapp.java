/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lum.pruebas;

import daoColecciones.UsuarioDAO;
import encriptado.Encriptacion;
import entidades.Direccion;
import entidades.EntidadUsuario;
import entidades.SexoEnum;
import excepciones.PersistenciaException;
import interfaces.IUsuarioDAO;
import java.util.Calendar;

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
        EntidadUsuario usuario2=new EntidadUsuario();
        usuario2.setTelefono("6441445522");
        try {
            usuario2=dao.obtenerUsuario(usuario2);
            if(usuario2!=null)System.out.println(usuario2);
        } catch (PersistenciaException e) {
            System.out.println(e);
        }
//------------------Probando cifrado---------------------
        interfaces.IEncriptacion cript=new Encriptacion();
        String password =usuario2.getContrasena();
        String hashedPassword = cript.genrarHash(password);
        System.out.println("Contraseña encriptada: " + hashedPassword);

        // Ejemplo de verificación de contraseña
        String passwordToCheck = "1234";
        if (cript.verificarContra(passwordToCheck, hashedPassword)) {
            System.out.println("La contraseña es correcta");
        } else {
            System.out.println("La contraseña es incorrecta");
        }
    }
}

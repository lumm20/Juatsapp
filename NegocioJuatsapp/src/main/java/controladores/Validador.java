/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DTOs.UsuarioDTO;
import excepciones.NegocioException;
import java.util.regex.Pattern;

/**
 *
 * @author LuisaM
 */
public class Validador {
    protected static final String NOMBRE="^[A-Za-záéóúüíÁñ ]{0,80}$";
    protected static final String TELEFONO="^\\d{10}$";
    protected static final String CONTRASENA="^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W\\_])[A-Za-z0-9\\W\\_]{8,}$";
    protected static final String NOMBRE_IMAGEN="^[\\w]{1,20}\\.(png|jpg)$";
    protected static final String DIRECCION="^[a-zA-Z ]{3,20};[a-zA-Z ]{3,15};\\d{1,5}$";
    protected static final String FECHA_NACIMIENTO="^\\d|([12]\\d)|3[01] ";
    /**
     * 
     * @param regex
     * @param input
     * @return 
     */
    protected static boolean cumpleConFormato(String regex,String input){
        return Pattern.matches(regex, input);
    }
    /**
     * 
     * @param usuario
     * @return
     * @throws NegocioException 
     */
    protected static int cumpleConCampos(UsuarioDTO usuario)throws NegocioException{
        Object[] campos={
            usuario.getNombre(),
            usuario.getTelefono(),
            usuario.getContrasena(),
            usuario.getDireccion(),
            usuario.getSexo(),
            usuario.getFechaNacimiento()
        };
        int contador=0;
        for (Object campo : campos) {
            contador++;
            if(campo==null)return contador;
        }
        return 0;
    }
    /**
     * 
     * @param usuario
     * @return
     * @throws NegocioException 
     */
    protected static int cumpleConFormato(UsuarioDTO usuario)throws NegocioException{
        String[][] campos={{usuario.getNombre(),NOMBRE}, {usuario.getTelefono(),TELEFONO},
            {usuario.getContrasena(),CONTRASENA}};
        int contador=0;
        for (String[] campo : campos) {
            contador++;
             if (!cumpleConFormato(campo[1], campo[0]))  return contador;
        }
        return esDireccionValida(usuario.getDireccion());
    }
    /**
     * 
     * @param direccion
     * @return 
     */
    protected static int esDireccionValida(String direccion){
        String[] dirArr=direccion.split(";");
        int campo=0;
        if(!Pattern.matches("^[a-zA-Z \\d]{3,20}$",dirArr[0]))campo=5;
        else if(!Pattern.matches("^[a-zA-Z ]{3,15}$",dirArr[1]))campo= 6;
        else if(!Pattern.matches("[\\d&&[^0]]\\d{0,4}$",dirArr[2]))campo= 7;
        return campo;
    }
    
    //protected static void validarInfoUsuario()
}

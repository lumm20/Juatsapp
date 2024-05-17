/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.regex.Pattern;

/**
 *
 * @author LuisaM
 */
public class Validador {
    protected static final String NOMBRE="^[A-Za-záéóúüíÁñ ]{0,80}";
    protected static final String TELEFONO="^\\d{10}";
    protected static final String CONTRASENA="^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W\\_])[A-Za-z0-9\\W\\_]{8,}$";
    protected static final String NOMBRE_IMAGEN="^[\\w]{1,20}\\.(png|jpg)$";
    protected static final String DIRECCION="^[a-zA-Z ]{3,20};[a-zA-Z ]{3,15};\\d{1,5}$";
    protected static final String FECHA_NACIMIENTO="^\\d|([12]\\d)|3[01] ";
    
    protected static boolean validar(String regex,String input){
        return Pattern.matches(regex, input);
    }
}

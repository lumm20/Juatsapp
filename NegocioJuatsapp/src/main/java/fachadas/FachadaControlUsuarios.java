/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import DTOs.UsuarioDTO;
import controladores.ControlUsuarios;
import excepciones.NegocioException;

/**
 *
 * @author LuisaM
 */
public class FachadaControlUsuarios {
    private ControlUsuarios control;
    
    public FachadaControlUsuarios(){
        this.control=new ControlUsuarios();
    }
    
    public UsuarioDTO login(UsuarioDTO usuario)throws NegocioException{
        try {
            return control.iniciarSesion(usuario);
        } catch (NegocioException e) {
            throw e;
        }
    }
    
    public boolean registrarUsuario(UsuarioDTO usuario)throws NegocioException{
        try {
            return control.registrarUsuario(usuario);
        } catch (NegocioException e) {
            throw e;
        }
    }
    
}

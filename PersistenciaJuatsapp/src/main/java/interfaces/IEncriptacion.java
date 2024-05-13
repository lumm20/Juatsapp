/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author LuisaM
 */
public interface IEncriptacion {
    String genrarHash(String contra);
    boolean verificarContra(String contra, String hash);
}

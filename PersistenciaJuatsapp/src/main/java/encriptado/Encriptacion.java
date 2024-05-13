/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptado;

import interfaces.IEncriptacion;
import java.security.SecureRandom;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author LuisaM
 */
public class Encriptacion implements IEncriptacion{
    
    @Override
    public String genrarHash(String contra) {
        SecureRandom random=new SecureRandom();
        String sal = BCrypt.gensalt(12, random);
        return BCrypt.hashpw(contra, sal);
    }

    @Override
    public boolean verificarContra(String contra, String hash) {
        return BCrypt.checkpw(contra, hash);
    }

}

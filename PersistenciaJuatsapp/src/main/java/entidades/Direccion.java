/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;

/**
 *
 * @author LuisaM
 */
public class Direccion implements Serializable{
    private String calle;
    private String colonia;
    private String numero;

    public Direccion() {
    }

    public Direccion(String calle, String colonia, String numero) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Direccion{");
        sb.append("calle=").append(calle);
        sb.append(", colonia=").append(colonia);
        sb.append(", numero=").append(numero);
        sb.append('}');
        return sb.toString();
    }
}

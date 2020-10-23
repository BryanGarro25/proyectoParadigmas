/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Lencho-PC
 */
public class Expresion {
    private String expresion;
    private String canonica;
    private String resultado;

    public Expresion() {
    }

    public Expresion(String expresion, String canonica, String resultado) {
        this.expresion = expresion;
        this.canonica = canonica;
        this.resultado = resultado;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getCanonica() {
        return canonica;
    }

    public void setCanonica(String canonica) {
        this.canonica = canonica;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Expresion{" + "expresion=" + expresion + ", canonica=" + canonica + ", resultado=" + resultado + '}';
    }
    
}

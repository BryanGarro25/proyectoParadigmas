/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Lencho-PC
 */
public class Evaluador {
    private String[] regex;
    private List<Expresion> expresiones;

    public Evaluador() {
    }

    public Evaluador(String[] regex, List<Expresion> expresiones) {
        this.regex = regex;
        this.expresiones = expresiones;
    }

    public String[] getRegex() {
        return regex;
    }

    public void setRegex(String[] regex) {
        this.regex = regex;
    }

    public List<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(List<Expresion> expresiones) {
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
        return "Evaluador{" + "regex=" + regex + ", expresiones=" + expresiones + '}';
    }
    
    
    
    
}

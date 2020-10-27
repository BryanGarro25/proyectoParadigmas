/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean verificar(String expresion){
        
        String regex = "[-\\(-]*[a-z]([>!#\\+∧][-\\(]*-*[a-z]([>!#\\+∧][-\\(]*-*[a-z]\\)*)*)+\\)*([>!#\\+∧][-\\(-]*[a-z][>!#\\+∧]-*[a-z]\\)*)*";
        Pattern patron = Pattern.compile(regex);
        Matcher acepta = patron.matcher(expresion);
        
        return acepta.matches();
    }
    
    public boolean counterP(String line){
        int counterA = 0;
        int counterC = 0;
        for(int i = 0; i<line.length();i++){
            if(line.charAt(i)=='('){
                counterA++;
            }else if(line.charAt(i)==')'){
                counterC++;
            }
        }
        return counterA==counterC;
    }
    
    @Override
    public String toString() {
        return "Evaluador{" + "regex=" + regex + ", expresiones=" + expresiones + '}';
    }
    
}

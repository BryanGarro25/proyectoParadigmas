/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Vista1;
import java.util.Observer;

/**
 *
 * @author Lencho-PC
 */
public class Modelo extends java.util.Observable {

    public static void addObserver(Vista1 aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Expresion laExpresion;
    private Evaluador elEvaluador;

    public Modelo() {
        this.laExpresion = new Expresion();
        this.elEvaluador = new Evaluador();
    }

    public Expresion getLaExpresion() {
        return laExpresion;
    }

    public void setLaExpresion(Expresion laExpresion) {
        this.laExpresion = laExpresion;
    }

    public Evaluador getElEvaluador() {
        return elEvaluador;
    }

    public void setElEvaluador(Evaluador elEvaluador) {
        this.elEvaluador = elEvaluador;
    }
    
    /**
     *
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }
    
    public void commit(){
        setChanged();
        notifyObservers();       
    }   
}

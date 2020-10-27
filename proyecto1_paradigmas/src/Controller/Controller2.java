/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.CreaTabla;
import Modelo.Modelo;
import Vista.Vista2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fiorella Salgado
 */

//Control de la Vista
public class Controller2 {
    private Modelo elmodelo;
    private Vista2 laVista;
   
    //Constructor
    public Controller2(Modelo elmodelo, Vista2 laVista) {
        this.elmodelo = elmodelo;
        this.laVista = laVista;
        laVista.setController(this);
        laVista.setModel(elmodelo);
        
        
    }
    
    //retorna la hilera Posfija
    public String getPostfija(String m){
        return elmodelo.getPostFija(m);
    }
    
    //Borra los datos que se encuentere en la tabla
    public void limpiarTabla(){
        elmodelo.getLaExpresion().setTable(new DefaultTableModel());
        elmodelo.commit();
    }
    
    //LLena la tabla con la informacion solicitada
    public void llenarTabla(Vista2 v,String formula){
        elmodelo.getLaExpresion().generarColumnas(v,formula);
        elmodelo.getLaExpresion().generarFilas(v,formula);
        elmodelo.getLaExpresion().setCanonicas();
        elmodelo.commit();
    }
    
    //Devuelve la expresion
    public boolean expCorrecta(String f){
        return elmodelo.expresionValida(f);
    }
}

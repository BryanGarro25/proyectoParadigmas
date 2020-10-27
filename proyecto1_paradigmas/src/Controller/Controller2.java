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
public class Controller2 {
    private Modelo elmodelo;
    private Vista2 laVista;
   

    public Controller2(Modelo elmodelo, Vista2 laVista) {
        this.elmodelo = elmodelo;
        this.laVista = laVista;
        laVista.setController(this);
        laVista.setModel(elmodelo);
        
        
    }
    
    public String getPostfija(String m){
        return elmodelo.getPostFija(m);
    }
    public void limpiarTabla(){
        elmodelo.getLaExpresion().setTable(new DefaultTableModel());
        elmodelo.commit();
    }
    public void llenarTabla(Vista2 v,String formula){
        elmodelo.getLaExpresion().generarColumnas(v,formula);
        elmodelo.getLaExpresion().generarFilas(v,formula);
        elmodelo.commit();
    }
    
    public boolean expCorrecta(String f){
        return elmodelo.expresionValida(f);
    }
}

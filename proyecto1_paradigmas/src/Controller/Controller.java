/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Modelo;
import Vista.Vista1;
import Vista.Vista3;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Lencho-PC
 */
public class Controller {
    private Modelo elmodelo;
    private Vista1 laVista;

    public Controller(Modelo elmodelo, Vista1 laVista) {
        this.elmodelo = elmodelo;
        this.laVista = laVista;
        
        laVista.setController(this);
        laVista.setModel(elmodelo);
    }
    
   
    public ArrayList<String> cargarXML(File file){
        ArrayList<String> expresiones = elmodelo.cargarXML(file);
        
        if(expresiones != null){
        return expresiones;
        }else{
            return null;
        }
    }
    
}

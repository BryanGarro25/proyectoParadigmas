/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Modelo;
import Vista.Vista1;

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
    
    
}

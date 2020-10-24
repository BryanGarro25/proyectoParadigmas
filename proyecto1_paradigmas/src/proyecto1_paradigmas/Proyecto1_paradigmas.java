/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_paradigmas;

import Controller.Controller;
import Modelo.Modelo;
import Vista.Vista1;

/**
 *
 * @author Bryan
 */
public class Proyecto1_paradigmas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("hola");
        Modelo domainModel = new Modelo();
       
        Vista1 loginView= new Vista1();
        Controller logincontroller = new Controller(domainModel,loginView);
        
        loginView.setVisible(true);
    }
    
}

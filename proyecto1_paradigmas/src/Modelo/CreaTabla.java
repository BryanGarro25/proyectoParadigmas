/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Vista2;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fiorella Salgado
 */
public class CreaTabla {
    private Expresion exp;
    
    public void CreaTable(){
        exp = new Expresion();
    }
    
    public void generarColumnas(Vista2 vista, String formula){
        exp = new Expresion(formula); 
        exp.getPostFija(formula);
        DefaultTableModel table = new DefaultTableModel();
        exp.getVariables().forEach((var) -> {
            table.addColumn(var);
        });
        
        table.addColumn(formula);
        vista.getTablaVerdad().setModel(table);
    }
    public String completarCeros (String binario,int tam){
        while(binario.length()!=tam)
            binario = "0"+binario;
    
        return binario;
    }
    
    public void generarFilas(Vista2 vista, String f){
        exp = new Expresion(f);
        exp.getPostFija(f);
        DefaultTableModel table = (DefaultTableModel) vista.getTablaVerdad().getModel();
        Object filas[];
        ArrayList<Boolean> valores = new ArrayList();
        int maximo = (int) Math.pow(2, exp.getVariables().size()); //maximo es la cantidad de numeros binarios
        int tam = exp.getVariables().size(); //cantidad de variables
        String binario;
        int ceros;
        
        for(int i = 0; i < maximo; i++ ){ //cuenta en binario hasta 4
            filas = new Object[tam + 1];
            binario = Integer.toString(i,2);
            ceros = tam - binario.length();
            
            binario = this.completarCeros(binario,tam);
            
            for(int x=0;x<binario.length();x++){
                if(binario.charAt(x) == '1'){
                    filas[x] = "V";
                    valores.add(true);
                    System.out.println(filas[x]);
                }else{
                    filas[x] = "F";
                    valores.add(false);
                    System.out.println(filas[x]);
                }
            }
            filas[tam] = exp.evaluar(f,valores);
            table.addRow(filas);
              valores = new ArrayList();
        }
        vista.getTablaVerdad().setModel(table);
        
    }
}

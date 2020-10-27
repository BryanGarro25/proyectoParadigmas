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
    
    public void generarFilas(Vista2 vista, String f){
        exp = new Expresion(f);
        exp.getPostFija(f);
        DefaultTableModel table = (DefaultTableModel) vista.getTablaVerdad().getModel();
        Object filas[];
        ArrayList<Boolean> valores = new ArrayList();
        int maximo = (int) Math.pow(2, exp.getVariables().size());
        int tam = exp.getVariables().size();
        String binario;
        int ceros;
        
        for(int i = 0; i < maximo; i++ ){
            filas = new Object[tam + 1];
            binario = Integer.toString(i,2);
            ceros = tam - binario.length();
            
            for(int j = 0; j < ceros; j++){
                filas[j] = "F";
                System.out.println(filas[j]);
            }
            
            for(int n = tam - 1, m = binario.length()-1; m >= 0; m--,n--){
                if(binario.charAt(m) == '1'){
                    filas[n] = "V";
                    valores.add(true);
                    System.out.println(filas[n]);
                }else{
                    filas[n] = "F";
                    valores.add(false);
                    System.out.println(filas[n]);
                }
            }
            filas[tam] = exp.evaluar(f,valores);
            table.addRow(filas);
        }
        vista.getTablaVerdad().setModel(table);
    }
}

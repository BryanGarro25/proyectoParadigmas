/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Vista2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fiorella Salgado
 */
public class CreaTabla {
    
    
    public void CreaTable(){
        
    }
    
    public void generarColumnas(Vista2 vista, List<String> v, String formula){
        DefaultTableModel table = new DefaultTableModel();
        
        v.forEach((var) -> {
            table.addColumn(var);
        });
        
        table.addColumn(formula);
        vista.getTablaVerdad().setModel(table);
    }
    
    public void generarFilas(Vista2 vista, String f, List<String> v, String resultado){
        DefaultTableModel table = (DefaultTableModel) vista.getTablaVerdad().getModel();
        Object filas[];
        int maximo = (int) Math.pow(2, v.size());
        int tam = v.size();
        String binario;
        int ceros;
        
        for(int i = 0; i < maximo; i++ ){
            filas = new Object[tam + 1];
            binario = Integer.toString(i,2);
            ceros = tam - binario.length();
            
            for(int j = 0; j < ceros; j++){
                filas[j] = "F";
            }
            
            for(int n = tam - 1, m = binario.length()-1; m >= 0; m--,n--){
                if(binario.charAt(m) == '1'){
                    filas[n] = "V";
                }else{
                    filas[n] = "F";
                }
            }
            filas[tam] = resultado;
            table.addRow(filas);
        }
        vista.getTablaVerdad().setModel(table);
    }
}

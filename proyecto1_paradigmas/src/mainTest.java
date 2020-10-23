/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryan
 */

import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

public class mainTest {
    public static boolean counterP(String line){
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
    public static void reader(String path,String regex){ 
        Pattern patron = Pattern.compile(regex);
        boolean bandera = true;
        try {
            Scanner input = new Scanner(new File(path));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                //line = line.replaceAll("\\(", "w");
                //line = line.replaceAll("\\)", "o");
                Matcher acepta = patron.matcher(line);
                
                if(counterP(line) && acepta.matches()){
                    System.out.println("Hilera " + line + " aceptada");
                }else{
                    System.out.println("Hilera " + line + " rechazada");
                }
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
           
    }
    //(p^q)v(p^r)v(q^r)
    public static void main(String[] args) {
        System.out.println("******************Hileras Aceptadas******************");
        String regex = "[-\\(-]*[pqr][v∧]-*[pqr]\\)*([∧v][-\\(-]*[pqr][v∧]-*[pqr]\\)*)*";
        
        reader("C:/Users/Bryan/Desktop/proyecto/ejemplo 1 (aceptar).txt",regex);//Respuesta 
        
        
    
    }
    
}
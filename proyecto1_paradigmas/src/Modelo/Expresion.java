/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.Vista2;
import static java.lang.Character.isSpace;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lencho-PC
 */


public class Expresion {
    private String expresion;
    private String canonicaD;
    private String canonicaC;
    private String resultado;
    private List<String> variables;
    private DefaultTableModel table;
    public Expresion() {
    }

    //Constructor
    public Expresion(String expresion, String canonica , String resultado) {
        this.expresion = expresion;
        this.canonicaD = canonica;
        this.resultado = resultado;
        variables = new ArrayList<>();
        this.table = new DefaultTableModel();
    }
    
    //Constructor 
    public Expresion(String expresion) {
        this.expresion = expresion;
        this.canonicaD = "";
        this.canonicaC = "";
        this.resultado = "";
        variables = new ArrayList<>();
        this.table = new DefaultTableModel();
    }

    //retorna la tabla
    public DefaultTableModel getTable() {
        return table;
    }
    
    //set de la tabla
    public void setTable(DefaultTableModel table) {
        this.table = table;
    }

    //retorna la expresión
    public String getExpresion() {
        return expresion;
    }

    //set de la expresion
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    //retorna la expresion canonica disyuntiva
    public String getCanonicaD() {
        return canonicaD;
    }

    //set de la expresion canonica disyuntiva
    public void setCanonicaD(String canonica) {
        this.canonicaD = canonica;
    }
    
    //set de la expresion canonica
    public void setCanonicaH(String canonicaH) {
        this.canonicaC = canonicaH;
    }
    
    //retorna la expresion canonica
    public String getCanonicaH() {
        //s.substring(0, s.length() - 1);
        return canonicaC;
    }

    //retorna el resultado de la expresion
    public String getResultado() {
        return resultado;
    }

    //set del reusltado
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    //retorna la variables
    public List<String> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Expresion{" + "expresion=" + expresion + ", canonica distuntiva=" + canonicaD + ", resultado=" + resultado + '}';
    }
    
    //Retorna la expresion de manera postfija de una expresion infija
    public String getPostFija(String Infija){
       Stack<Character> stack = new Stack<>();
        String PosFija = "";
        stack.push('(');

	for (int i = 0, l = Infija.length(); i < l; ++i) {
		char Actual = Infija.charAt(i);

		if (Actual == ' ') {
			// lo ignora
		}
		// Si es un dígito es '.' o una letra ("variables"), agréguelo a la salida
		else if (esNum(Actual) || '.' == Actual) {
			PosFija += String.valueOf(Actual);
		}

		else if ('(' == Actual) {
			stack.push(Actual);
		}

		else if (esOperador(Actual)) {
               
			if (Actual == '-' && esSigno(i, Infija)) {
				PosFija += String.valueOf(Actual);
			}
			else {
                        //este else decide que signo va primero
				char operadorDerecho = Actual;
				while (!stack.empty() && esOperador(stack.peek()) && precede(stack.peek(), operadorDerecho)) {
				
					PosFija += " " + String.valueOf(stack.peek());
					stack.pop();
				}
				PosFija += " ";
				stack.push(operadorDerecho);
			}
		}


		else if (')' == Actual) {
			// Mientras que la parte superior de la pila no es un paréntesis izquierdo
			while (!stack.empty() && '(' != stack.peek()) {
				PosFija += " " + String.valueOf(stack.peek());
				stack.pop();
			}
			if (stack.empty()) {
				System.out.print( "falta parentesis izquierdo\n");
			}
			// Descartar el parentesis izquierdo
			stack.pop();
			PosFija += " ";
		}
		else {
                        System.out.print(String.valueOf(Actual)+  "caracter de entrada inválido \n");
		}
	}


	// Comenzó con un paréntesis izquierdo, ahora ciérralo
	// Mientras que arriba de la pila no es un parentesis izquierdo
	while (!stack.empty() && '(' != stack.peek()) {
                PosFija += " " + String.valueOf(stack.peek());
		stack.pop();
	}
	if (stack.empty()) {
	
                System.out.print( "falta parentesis izquierdo");
	}
	// Descartar el parentesis izquierdo
	stack.pop();

	// todos los parens abiertos deberían estar cerrados ahora -> pila vacía
	if (!stack.empty()) {
                System.out.print( "falta parentesis derecho");
	}

	return PosFija;
    }
    
    //manejador de operadores
    boolean precede(char operadorIzquierdo, char operadorDerecho){
            //si izquierdo tiene mayor precedencia que derecho retorna true
            
     if (operadorIzquierdo == '!' && operadorDerecho == '-') {
		return false;
	}
	else if (operadorDerecho == '!'&& operadorIzquierdo == '-') {
		return true;
	}

        else if (operadorIzquierdo == '!') {
		return true;
	}
	else if (operadorDerecho == '!') {
		return false;
	}
        
        
        
        else if(operadorIzquierdo == '>'){
            if(  operadorDerecho == '+' || operadorDerecho == '∧' ||  operadorDerecho == '#' )
            return true;
        }
        else if(operadorDerecho == '>' ){
            if(operadorIzquierdo == '+' || operadorIzquierdo == '∧'|| operadorIzquierdo == '#')
            return false;
        }
         if (operadorIzquierdo == '>' && operadorDerecho == '-') {
		return false;
	}
	else if (operadorDerecho == '>'&& operadorIzquierdo == '-') {
		return true;
	}
        
        
        
        else if (operadorIzquierdo == '#') {
            if(  operadorDerecho == '+' || operadorDerecho == '∧' )
		return true;
            
	}
        else if (operadorDerecho == '#') {
            if( operadorIzquierdo == '+' || operadorIzquierdo == '∧' )
		return false;
	}
        if (operadorIzquierdo == '#' && operadorDerecho == '-') {
           return false;
	}
	else if (operadorDerecho == '#'&& operadorIzquierdo == '-') {
		return true;
	}
       
 
        
	else if (operadorIzquierdo == '+' || operadorIzquierdo == '∧' && operadorDerecho == '-') {
		return false;
	}
	else if (operadorDerecho == '+' || operadorDerecho == '∧' && operadorIzquierdo == '-') {
		return true;
	}

	return true;
}
    
    //Verifica si el siguiente caracter es un numero
    boolean esNum(char charActual){
        if (Character.isLetter(charActual)){
            
            if (!this.variables.contains(String.valueOf(charActual)))
                this.variables.add(String.valueOf(charActual));
            
            return true;
        }
        return false;
    }
    
    //Verifica si el siguiente caraacter es un operador
    boolean esOperador(char charActual){
	switch (charActual) {
	case '+':
	case '-':
	case '∧':
	case '>':
	case '!':
        case '#':
		return true;
	default:
		return false;
	}
    }
    
    //Verificia si el siguiente caracter es un signo
    boolean esSigno(int i, String Infija) {
        //-(p∧r) i = 0
	int posAnt;
	if ( esNum(Infija.charAt(i+1)) || Infija.charAt(i+1)=='-')
		return true;
	else { return false;}


    }
    
    //Manjador de logica de operadores
    boolean resuelveOperador(ArrayList<Boolean> valores,String operador,String variableIzquierda, String variableDerecha){
        //tomar en cuenta que el menos funciona distinto y hay qeu jalar ambos datos
        //operador +, izq true, der g
        boolean valorDerecha;
        if(variableDerecha.equals("true") || variableDerecha.equals("false")){
            valorDerecha= Boolean.parseBoolean(variableDerecha);  
        }else
            valorDerecha = valores.get(this.variables.indexOf(variableDerecha));
        
    
       
        if(!operador.equals("-") ){ //caso de que haya un - como operador
      
            boolean valorIzquierda;
            if(variableIzquierda.equals("true") || variableIzquierda.equals("false")){
                valorIzquierda= Boolean.parseBoolean(variableIzquierda);  
            }else
                valorIzquierda = valores.get(this.variables.indexOf(variableIzquierda));
        
            
            
            
            switch (operador) { 
                case "+":{//V
                    return valorIzquierda || valorDerecha;
                }
                case "∧":{
                    return valorIzquierda && valorDerecha;
                }
                case ">":{// ->
                    if(valorIzquierda==false && valorDerecha==false) return true;
                    if(valorIzquierda==false && valorDerecha==true) return true;
                    if(valorIzquierda==true && valorDerecha==false) return false;
                    if(valorIzquierda==true && valorDerecha==true) return true;
                }
                case "!":{// <->
                    if(valorIzquierda==false && valorDerecha==false) return true;
                    if(valorIzquierda==false && valorDerecha==true) return false;
                    if(valorIzquierda==true && valorDerecha==false) return false;
                    if(valorIzquierda==true && valorDerecha==true) return true;
                
                }
                case "#":{// or exclusivo ⊻
                    if(valorIzquierda==false && valorDerecha==false) return false;
                    if(valorIzquierda==false && valorDerecha==true) return false;
                    if(valorIzquierda==true && valorDerecha==false) return false;
                    if(valorIzquierda==true && valorDerecha==true) return true;
                
                }
                        return true;
                default:
                        return false;
            }
            
        }else{
        //menos como oeprador, se debe tomar el valor 
            return !valorDerecha;
        }
    }
    
    //Evaluador de la expresion
    public boolean evaluar(String infija, ArrayList<Boolean> valores){
        //valores debe contener los valores true o false para cada variable, estos varian muchas veces y se generan aleatoreamente
        /*
            Ejemplo: 
                lista de variables: a b c
                lista de valores: true true false
        
                por lo tanto se asume que a=true b=true c=false
        stack = "true"
        */
        infija = infija.replaceAll("<->", "!");
        infija = infija.replaceAll("->", ">");
        infija = infija.replaceAll("\\*", "∧");
        infija = infija.replaceAll("--", ""); //elimina doble negacion
        String postFija = this.getPostFija(infija);
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i<postFija.length();i++){
            
            char actual = postFija.charAt(i); 
            
            if(esNum(actual)){
                stack.push(String.valueOf(actual)); //inserta la variable 
            }
            else if(actual == '-'&& i+1<postFija.length() && esNum(postFija.charAt(i+1))){
                stack.push(
                    String.valueOf( 
                        !valores.get(
                                this.variables.indexOf(String.valueOf(postFija.charAt(i+1)))
                        )//extrae el valor de la variable y lo niega
                    ) //toma el valor de la variable negada y lo inserta al stack
                );
                i++;
            }
            else if(esOperador(actual)){
                
                String varDerecha =  stack.pop();
                String varIzquierda = "";
                
           
                if(actual != '-'){   
                    varIzquierda =  stack.pop();
                }
                
                boolean respuesta = resuelveOperador(valores,String.valueOf(actual),varIzquierda,varDerecha);
                //pasar respuesta a string y ponerla en el stack para seguir evaluando
                stack.push(String.valueOf(respuesta));
            }
        }
        return Boolean.parseBoolean(stack.pop());
    }
    
    //Generador de columnas para la tabla en la vista
    public void generarColumnas(Vista2 vista, String formula){
        
        this.getPostFija(formula);
        
        this.variables.forEach((var) -> {
            table.addColumn(var);
        });
        
        table.addColumn(formula);
        //vista.getTablaVerdad().setModel(table);
    }
    
    //generador de 0
    public String completarCeros (String binario,int tam){
        while(binario.length()!=tam)
            binario = "0"+binario;
    
        return binario;
    }
    
    //Generador de columnas para la tabla en la vista
    public void generarFilas(Vista2 vista, String f){
        
        this.getPostFija(f);
        //DefaultTableModel table = (DefaultTableModel) vista.getTablaVerdad().getModel();
        Object filas[];
        ArrayList<Boolean> valores = new ArrayList();
        int maximo = (int) Math.pow(2, this.getVariables().size()); //maximo es la cantidad de numeros binarios
        int tam = this.getVariables().size(); //cantidad de variables
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
            filas[tam] = this.evaluar(f,valores);
            table.addRow(filas);
              valores = new ArrayList();
        }
        vista.getTablaVerdad().setModel(table);
        
    }
    
    //Constructor de la expresiones canonicas
    public void setCanonicas(){
        int cant_cols = table.getColumnCount();
        int columna_con_resultados = cant_cols;
        cant_cols--;
        int cant_rows = table.getRowCount();
        int row_con_variables = 0;  
        
        //canonicaD
        //canonicaC
        for(int row=1; row<cant_rows+1 ; row++){
            String D = "(";
            String C = "(";
            for(int col=0;col<cant_cols;col++){
                String variable_Actual = variables.get(col);
                String resultado_de_row = String.valueOf((Boolean)table.getValueAt(row-1, columna_con_resultados-1));
                String actual = (String)table.getValueAt(row-1, col);
                
                if(resultado_de_row.equals("true")){
                    if(D.length()>=2){ //aqui se arma la Disyuntiva
                        D+="∧";
                    }
                    if(actual.equals("V")){
                        D+=variable_Actual;
                        
                    }
                    else if(actual.equals("F")){
                        D+="-"+variable_Actual;
                        
                    }
                }else if(resultado_de_row.equals("false")){
                    if(C.length()>=2){//aqui se arma la conjuntiva
                        C+="v";
                       
                    }
                    if(actual.equals("V")){
                        C+="-"+variable_Actual;
                    }
                    else if(actual.equals("F")){
                        C+=variable_Actual;
                    }
                }
            }//second for
            if(D.equals("(")){
                C+=")";
                this.canonicaC += C + "∧";
                
            }else{
                D+=")";
                this.canonicaD += D + "v";
            }
            
        }//first for j
    
        if(this.canonicaD.length()>0){
            canonicaD = canonicaD.substring(0,canonicaD.length()-1);
        }
        if(this.canonicaC.length()>0){
            canonicaC = canonicaC.substring(0,canonicaC.length()-1);
        }
    }
}

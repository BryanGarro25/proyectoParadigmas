/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import static java.lang.Character.isSpace;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 *
 * @author Lencho-PC
 */
public class Expresion {
    private String expresion;
    private String canonica;
    private String resultado;
    private List<String> variables;
    public Expresion() {
    }

    public Expresion(String expresion, String canonica , String resultado) {
        this.expresion = expresion;
        this.canonica = canonica;
        this.resultado = resultado;
        variables = new ArrayList<>();
    }
    
    public Expresion(String expresion) {
        this.expresion = expresion;
        this.canonica = "";
        this.resultado = "";
        variables = new ArrayList<>();
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getCanonica() {
        return canonica;
    }

    public void setCanonica(String canonica) {
        this.canonica = canonica;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<String> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Expresion{" + "expresion=" + expresion + ", canonica=" + canonica + ", resultado=" + resultado + '}';
    }
    
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
    
    
    
    
    boolean esNum(char charActual){
        if (Character.isLetter(charActual)){
            
            if (!this.variables.contains(String.valueOf(charActual)))
                this.variables.add(String.valueOf(charActual));
            
            return true;
        }
        return false;
    }
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
    boolean esSigno(int i, String Infija) {
        //-(p∧r) i = 0
	int posAnt;
	if ( esNum(Infija.charAt(i+1)) || Infija.charAt(i+1)=='-')
		return true;
	else { return false;}


    }
    boolean resuelveOperador(List<Boolean> valores,String operador,String variableIzquierda, String variableDerecha){
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
                    if(valorIzquierda==false && valorDerecha==true) return true;
                    if(valorIzquierda==true && valorDerecha==false) return true;
                    if(valorIzquierda==true && valorDerecha==true) return false;
                
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
    public boolean evaluar(String infija, List<Boolean> valores){
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
}

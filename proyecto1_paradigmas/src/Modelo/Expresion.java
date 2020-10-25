/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import static java.lang.Character.isSpace;
import java.util.Stack;
/**
 *
 * @author Lencho-PC
 */
public class Expresion {
    private String expresion;
    private String canonica;
    private String resultado;

    public Expresion() {
    }

    public Expresion(String expresion, String canonica , String resultado) {
        this.expresion = expresion;
        this.canonica = canonica;
        this.resultado = resultado;
    }
    
    public Expresion(String expresion) {
        this.expresion = expresion;
        this.canonica = "";
        this.resultado = "";
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
            if(  operadorDerecho == 'v' || operadorDerecho == '∧' ||  operadorDerecho == '#' )
            return true;
        }
        else if(operadorDerecho == '>' ){
            if(operadorIzquierdo == 'v' || operadorIzquierdo == '∧'|| operadorIzquierdo == '#')
            return false;
        }
         if (operadorIzquierdo == '>' && operadorDerecho == '-') {
		return false;
	}
	else if (operadorDerecho == '>'&& operadorIzquierdo == '-') {
		return true;
	}
        
        
        
        else if (operadorIzquierdo == '#') {
            if(  operadorDerecho == 'v' || operadorDerecho == '∧' )
		return true;
            
	}
        else if (operadorDerecho == '#') {
            if( operadorIzquierdo == 'v' || operadorIzquierdo == '∧' )
		return false;
	}
        if (operadorIzquierdo == '#' && operadorDerecho == '-') {
           return false;
	}
	else if (operadorDerecho == '#'&& operadorIzquierdo == '-') {
		return true;
	}
       
 
        
	else if (operadorIzquierdo == 'v' || operadorIzquierdo == '∧' && operadorDerecho == '-') {
		return false;
	}
	else if (operadorDerecho == 'v' || operadorDerecho == '∧' && operadorIzquierdo == '-') {
		return true;
	}

	return true;
}
    
    
    
    
    boolean esNum(char charActual){
//	switch (charActual) {
//	case 'p':
//	case 'q':
//	case 'r':
//
//		return true;
//	default:
//		return false;
//	}
    if(charActual == 'v') return false;
        return Character.isLetter(charActual);
    }
    boolean esOperador(char charActual){
	switch (charActual) {
	case 'v':
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
	if ( esNum(Infija.charAt(i+1)))
		return true;
	else { return false;}


    }
}

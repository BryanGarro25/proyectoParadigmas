/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lencho-PC
 */
public class Modelo extends java.util.Observable {

    
    private Expresion laExpresion;
    private Evaluador elEvaluador;

    //Constructor del modelo
    public Modelo(String expresion) {
        this.laExpresion = new Expresion(expresion);
        this.elEvaluador = new Evaluador();
    }

    //retorna la expresión
    public Expresion getLaExpresion() {
        return laExpresion;
    }

    //set de la expresión
    public void setLaExpresion(Expresion laExpresion) {
        this.laExpresion = laExpresion;
    }

    //retorna el evaluador
    public Evaluador getElEvaluador() {
        return elEvaluador;
    }

    //set del evaluador
    public void setElEvaluador(Evaluador elEvaluador) {
        this.elEvaluador = elEvaluador;
    }
    
    
    //carga el documento XML a un string de arraylist
    public ArrayList<String> cargarXML(File file){
        ArrayList<String> expresiones = null;
        expresiones = new ArrayList();
        try{  
            //creating a constructor of file class and parsing an XML file  
            
            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            // nodeList is not iterable, so we are using for loop   
        //}
            NodeList nodeList = doc.getElementsByTagName("expresion");
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Expresion: " + eElement.getElementsByTagName("formula").item(0).getTextContent());
                    System.out.println("Forma Simplificada: " + eElement.getElementsByTagName("simplificada").item(0).getTextContent());
                    System.out.println("Forma canónica: " + eElement.getElementsByTagName("fndcanonica").item(0).getTextContent());
                    System.out.println("Forma canónica: " + eElement.getElementsByTagName("fnccanonica").item(0).getTextContent());
                    
                    expresiones.add(eElement.getElementsByTagName("formula").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("simplificada").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("fndcanonica").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("fnccanonica").item(0).getTextContent());
                }
            } 
        }catch (Exception e)   {  
            e.printStackTrace();  
        }  
        
        return expresiones;
    }
    
    //Creador de elementos
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    //Creador de elementos
    private static Node createUserElement(Document doc, String formula, String simplificada,String fndcanonica,String fnccanonica) {
        Element expresion = doc.createElement("expresion");

        // set formula attribute
       expresion.appendChild(createUserElements(doc, expresion, "formula", formula));

        // create inversa element
        expresion.appendChild(createUserElements(doc, expresion, "simplificada", simplificada));

        // create canonica element
        expresion.appendChild(createUserElements(doc, expresion, "fndcanonica", fndcanonica));

        
        expresion.appendChild(createUserElements(doc, expresion, "fnccanonica", fnccanonica));
        return expresion;
    }
    
    //Creador de documentos XML
    public void crearXML(String nombreArch, String formula, String postfija, String simplificada,String fndcanonica,String fnccanonica){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // add elements to Document
            Element rootElement = doc.createElement("class");
            // append root element to document
            doc.appendChild(rootElement);

            // append first child element to root element
            rootElement.appendChild(createUserElement(doc, formula, simplificada, fndcanonica,fnccanonica));

            // for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("Formulas\\"+nombreArch+".xml"));

            // write data
            transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //retorna la expresion de manera postfija
    public String getPostFija(String ex){
        return laExpresion.getPostFija(ex);
    }
    
    //Validador de expresiones 
    public boolean expresionValida(String expresion){
        if(elEvaluador.verificar(expresion) && elEvaluador.counterP(expresion)){
            return true;
        }else{
            return false;
        }
    }
    /**
     *
     * @param o
     */
    
    //Observer
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }
    
    public void commit(){
        setChanged();
        notifyObservers();       
    }   
}

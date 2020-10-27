/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Modelo;
import Vista.Vista1;
import Vista.Vista3;
import java.io.File;
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

/**
 *
 * @author Fiorella Salgado
 */
public class Controller3 {
    private Modelo elmodelo;
    private Vista3 laVista;
    
    
    //Constructor
    public Controller3(Modelo elmodelo, Vista3 laVista) {
        this.elmodelo = elmodelo;
        this.laVista = laVista;
        
   
    }
    
    //Constructor
    public Controller3(){
    
    }
    
    //Crea un nodo
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    //Crea el element
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
    
    //Crea el documentos XML
    public void crearXML(String nombreArch, String formula, String simplificada,String fndcanonica, String fnccanonica){
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
}
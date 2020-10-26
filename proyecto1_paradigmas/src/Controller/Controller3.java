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
    
    public Controller3(Modelo elmodelo, Vista3 laVista) {
        this.elmodelo = elmodelo;
        this.laVista = laVista;
        
   
    }
    
    public Controller3(){
    
    }
    
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    private static Node createUserElement(Document doc, String formula, String postfija, String simplificada,String canonica) {
        Element expresion = doc.createElement("expresion");

        // set formula attribute
        expresion.appendChild(createUserElements(doc, expresion, "formula", formula));

        // create postfija element
        expresion.appendChild(createUserElements(doc, expresion, "postfija", postfija));

        // create inversa element
        expresion.appendChild(createUserElements(doc, expresion, "simplificada", simplificada));

        // create canonica element
        expresion.appendChild(createUserElements(doc, expresion, "canonica", canonica));


        return expresion;
    }
    
    public void crearXML(String nombreArch, String formula, String postfija, String simplificada,String canonica){
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
            rootElement.appendChild(createUserElement(doc, formula, postfija, simplificada, canonica));

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
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

    public Modelo() {
        this.laExpresion = new Expresion();
        this.elEvaluador = new Evaluador();
    }

    public Expresion getLaExpresion() {
        return laExpresion;
    }

    public void setLaExpresion(Expresion laExpresion) {
        this.laExpresion = laExpresion;
    }

    public Evaluador getElEvaluador() {
        return elEvaluador;
    }

    public void setElEvaluador(Evaluador elEvaluador) {
        this.elEvaluador = elEvaluador;
    }
    
    public ArrayList<String> cargarXML(File file){
        ArrayList<String> expresiones = null;
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
            expresiones = new ArrayList();
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Expresion: " + eElement.getElementsByTagName("formula").item(0).getTextContent());
                    System.out.println("Forma postfija: " + eElement.getElementsByTagName("postfija").item(0).getTextContent());
                    System.out.println("Forma Simplificada: " + eElement.getElementsByTagName("simplificada").item(0).getTextContent());
                    System.out.println("Forma canónica: " + eElement.getElementsByTagName("canonica").item(0).getTextContent());
                    
                    expresiones.add(eElement.getElementsByTagName("formula").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("postfija").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("simplificada").item(0).getTextContent());
                    expresiones.add(eElement.getElementsByTagName("canonica").item(0).getTextContent());
                }
            } 
        }catch (Exception e)   {  
            e.printStackTrace();  
        }  
        
        return expresiones;
    }
    
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    private static Node createUserElement(Document doc, String formula, String postfija, String simplificada,String canonica) {
        Element expresion = doc.createElement("expresion");

        // set formula attribute
        expresion.setAttribute("formula", formula);

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
    
    public String getPostFija(String ex){
        return laExpresion.getPostFija(ex);
    }
    
    /**
     *
     * @param o
     */
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

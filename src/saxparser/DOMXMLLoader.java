/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author julia
 */
public class DOMXMLLoader {
    static Node root;
    public static Node load(File xmlCourseFile) throws Exception {
//        Node root = new Node();
        ArrayList nodeList = new ArrayList<Node>();
        Stack elementStack = new Stack<Node>();
        Node currentNode = new Node();
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                Stack<Node> stack;
                Node currentNode = null;
                Node poppedNode = null;
                
                @Override
                 public void startDocument() throws SAXException {
                    root = null;
                    System.out.println("start of the document   : ");  
                }
 
                @Override
                public void endDocument() throws SAXException {
                    System.out.println("end of the document document     : ");
                }
    
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // qName is the tag name, attributes are the attributes on the tag, localName and uri are not things that we need here
                    System.out.println("qName: " + qName);
                    System.out.println("attributes: " + attributes.getValue("id"));
                    Node node = new Node();
                    
                    elementStack.push(node);
                   
                    if(attributes != null && attributes.getLength() == 1){
                        node.addAttributes(attributes.getValue(0));
                    }
                    
                    if(qName != null){
                        node.addName(qName);
                    }
                    
                    if(currentNode != null){
                        currentNode.addProperty(node);
                    }
                     
                    if (root == null){
                        root = node;
                        nodeList.add(node);
                    }
                    
                    currentNode = node;
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("qName: " + qName);
                    if(elementStack != null){
                        Node poppedNode = (Node)elementStack.pop();
                        poppedNode.addContent(poppedNode.getContent().trim());
                        if(elementStack.empty()){
                            root = poppedNode;
                            currentNode = null;
                        } else{
                            currentNode = (Node)elementStack.lastElement();
                        }
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    String content = new String(ch, start, length).trim();
                    if(content.length() == 0){
                        return;
                    }
                    currentNode.addContent(content);
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return root; 
    }
}

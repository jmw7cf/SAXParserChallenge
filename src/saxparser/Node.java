/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author julia
 */
public class Node {
    private ArrayList<Node> nodes;
    private String name = "";
    private String content = "";
    private String attributes = "";
    private ArrayList<Node> properties = null;
    
    public Node() {
        nodes =  new ArrayList<Node>();
    }
    
    public ArrayList<Node> getChildren() {
        return nodes;
    }
    
    public void addNode(Node child){
        nodes.add(child);
    }
    
    public void addName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void addContent(String content){
        this.content = content;
    }
    
    public String getContent(){
        return content;
    }
    
    public void addAttributes(String attributes){
        this.attributes = attributes;
    }
    
    public String getAttributes(){
        return attributes;
    }
    
    public void addProperty(Node node){
        if (properties == null){
            properties = new ArrayList();
        }
        this.properties.add(node);
    }
    
    public ArrayList<Node> getProperty(){
        return this.properties;
    }
    
//    public boolean hasProperty(){
//        if(properties.getProp == true){
//            
//            return false;
//        } else {
//            System.out.println("Testing");
//            return true;
//        }
//    }
}

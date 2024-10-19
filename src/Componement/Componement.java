/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componement;

/**
 * This class will represent a componement (after being processed by the circuit processing)
 * Thoses componement will be of 3 main types : 
 *  - Passives ones
 *  - Voltage / Current Sources
 *  - Active (power electronic componement)
 * @author LECOURT Camille
 */
public class Componement {
    private String name;
    private int imputNode;
    private int outputNode;
    private String type;
    private String number;
    
    /**
     * This is the builder of the componement
     * 
     * @param name / name of the componement (we will process type and number variable)
     * @param imputNode / name of the imput node for this componement
     * @param outputNode  / name of the output node for this componement
     */
    public Componement(String name,int imputNode,int outputNode){
        this.name=name;
        this.imputNode=imputNode;
        this.outputNode=outputNode;
        
        
        this.type = "";
        this.number = "";

        for (char c : name.toCharArray()) {
            
            if (Character.isDigit(c)) {
                this.number += c;
            } else {
                this.type += c;
            }
        }
    }
 
   

    
    
    
    
}

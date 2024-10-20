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
    private final String name;
    private final int imputNode;
    private final int outputNode;
    private final String type;
    private final String number;
    
    /**
     * This is the builder of the componement
     * 
     * @param name / name of the componement (we will process type and number variable)
     * @param imputNode / name of the imput node for this componement
     * @param outputNode  / name of the output node for this componement
     */
    protected Componement(String name,int imputNode,int outputNode){
        this.name=name;
        this.imputNode=imputNode;
        this.outputNode=outputNode;
        
        
        String type_constuctor = "";
        String number_constuctor = "";

        for (char c : name.toCharArray()) {
            
            if (Character.isDigit(c)) {
                number_constuctor += c;
            } else {
                type_constuctor += c;
            }
        }
        this.number=number_constuctor;
        this.type=type_constuctor;
    }
    
    

    public String getName() {
        return name;
    }

    public int getImputNode() {
        return imputNode;
    }

    public int getOutputNode() {
        return outputNode;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
    
    @Override
    /**
     * This method will retunrn information on the componement base on his subtype (active, passive, source)
     */
    public String toString(){
        return "This compnement type is not Specified ";
    }
    
    
    public String genCode(){
        
        return "";
    }
    
    
    
    
    
    
}

/*
 * CouplingComponent.java
 *
 * Created on 8 april 2010, 13:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mage.cades.model.execute.data;

import java.util.ArrayList;
/**
 *
 * @author laurent gerbaud
 */
public class CouplingComponent extends ElectricalCircuitComponent{
    protected String primaryComponent;
    protected String secondaryComponent;
    protected int numberInCouplingComponentVector;
    // Add by LG on febrary 22nd, 2011
    protected String type; // "BondGraphTransformer" or "BondGraphGyrator"
    
    /** Creates a new instance of CouplingComponent */
    /**
     * basic constructor
     */
    public CouplingComponent() {
        super();
    }
    
    /**
     * constructor with:
     * @param name
     * @param type 
     */
    public CouplingComponent(String name, String type) {
        super(name);
        this.primaryComponent = "P"+name;
        this.secondaryComponent = "S"+name;
        this.stringValue = null;
        this.type = type;
    }
     
    // Add by LG on febrary 22nd, 2011
    /**
     * constructor with:
     * @param name
     * @param value
     * @param type 
     */
    public CouplingComponent(String name, String value, String type) {
        super(name);
        this.primaryComponent = "P"+name;
        this.secondaryComponent = "S"+name;
        this.stringValue = value;
        this.type = type;
    }
    	
            
    // Add by LG on febrary 22nd, 2011
    public String getType() {
        return this.type;
    }
	
    // Add by LG on febrary 22nd, 2011
    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryComponent() {
        return this.primaryComponent;
    }
	
    public void setPrimaryComponent(String subCouplingComponent) {
        this.primaryComponent = subCouplingComponent;
    }
	
    public String getSecondaryComponent() {
        return this.secondaryComponent;
    }
	
    public void setSecondaryComponent(String subCouplingComponent) {
        this.secondaryComponent = subCouplingComponent;
    }
        
    public void setNumberInCouplingComponentVector(int numberInCouplingComponentVector){
        this.numberInCouplingComponentVector=numberInCouplingComponentVector;
    }
    
    public int getNumberInCouplingComponentVector(){
        return this.numberInCouplingComponentVector;
    }      
    
    /**
     * 
     * @return an xml string representing the data of the component 
     */
    public String toXmlString() {
        String xml = new String("");
        xml += "<Coupling component name=\""+getName()+"<Coupling component type=\""+getType();
        // Add by LG on febrary 22nd, 2011, modified on march 4, 2011
        xml += "\" primaryComponent=\""+getPrimaryComponent()+"\"";
        xml += " secondaryComponent=\""+getSecondaryComponent()+"\" value=\""+getStringValue()+"\"/>\r\n";
        return xml;
    }

    /**
     * allows to set the index of the component in primaryComponent
     * @param components 
     */
    public void setNumberInCouplingComponentVector(ArrayList<PhysicalComponent> components){
        int ret=0;
        while (this.primaryComponent.compareTo(components.get(ret).getName())!=0){
            ret++;
        }
        numberInCouplingComponentVector=ret;
    }

    /**
     * LG on March 10, 2015
     * @param prefix
     * @param separtorIfComplex
     * @param postfix
     * @return a string for argument of function or declaration, for the parameter of the component
    */
    public String getParameterName(String prefix, String separtorIfComplex, String postfix){
        return prefix+"K_"+getName()+postfix;
    }

    
    
}// end of the class

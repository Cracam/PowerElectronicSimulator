/*
 * Mutual.java
 *
 * Created on march 31th, 2003, 10:27
 */

package mage.cades.model.execute.data;

import java.util.ArrayList;
/**
 *
 * @author  gerbaud
 */
public class Mutual extends ElectricalCircuitComponent{
    
    protected String component1;
    protected PhysicalComponent physicalComponent1;
    protected String component2;
    protected PhysicalComponent physicalComponent2;
    protected int numberInInductorVector1;
    protected int numberInInductorVector2;
    //////    private double doubleValue;    // Add by LG on july 3rd, 2013
    // on July 5th, 2012 
        
    /** Creates a new instance of Mutual */
    /**
     * basic constructor
     */
    public Mutual() {
        super();
    }
    
    /**
     * constructor with:
     * @param name
     * @param component1
     * @param component2
     * @param value 
     */
    public Mutual(String name, String component1, String component2, String value) {
	super(name);
        this.component1 = component1;
        this.component2 = component2;
        this.stringValue = value;
        this.type="Mutual";
        this.parameterPrefix="K_";
        this.parameterName=parameterPrefix+component1+"_"+component2;
    }

     
    public String getComponent1() {
        return this.component1;
    }

    public void setComponent1(String component) {
        this.component1 = component;
    }

    public String getComponent2() {
        return this.component2;
    }

    public void setComponent2(String component) {
        this.component2 = component;
    }

    public void setNumberInInductorVector1(int numberInInductorVector1){
        this.numberInInductorVector1=numberInInductorVector1;
    }
        public int getNumberInInductorVector1(){
        return this.numberInInductorVector1;
    }

    public void setNumberInInductorVector2(int numberInInductorVector2){
        this.numberInInductorVector2=numberInInductorVector2;
    }
        public int getNumberInInductorVector2(){
        return this.numberInInductorVector2;
    }      

    // LG, march 13, 2013 
    public void setPhysicalComponent1(PhysicalComponent physicalComponent){
        this.physicalComponent1=physicalComponent;
    }
    public PhysicalComponent getPhysicalComponent1(){
        return this.physicalComponent1;
    }
    // LG, march 13, 2013 
    public void setPhysicalComponent2(PhysicalComponent physicalComponent){
        this.physicalComponent2=physicalComponent;
    }
    public PhysicalComponent getPhysicalComponent2(){
        return this.physicalComponent2;
    }

    public String toXmlString() {
        String xml = new String("");

        xml += "<MUTUAL name=\""+getName()+"\" component1=\""+getComponent1()+"\"";
        xml += " component2=\""+getComponent2()+"\" value=\""+getStringValue()+"\"/>\r\n";

        return xml;
    }

    public void setNumberInInductorVector1(ArrayList<PhysicalComponent> physicalComponents){
        int ret=0;
        while (this.component1.compareTo(physicalComponents.get(ret).getName())!=0){
            ret++;
        }
        numberInInductorVector1=ret;
        physicalComponent1=physicalComponents.get(ret);
    }
    public void setNumberInInductorVector2(ArrayList<PhysicalComponent> physicalComponents){
        int ret=0;
        while (this.component2.compareTo(physicalComponents.get(ret).getName())!=0){
            ret++;
        }
        numberInInductorVector2=ret;
        physicalComponent2=physicalComponents.get(ret);
    }
    public void setNumberInInductorVector(ArrayList<PhysicalComponent> physicalComponents){
        int ret=0;
        while (this.component1.compareTo(physicalComponents.get(ret).getName())!=0){
            ret++;
        }
        numberInInductorVector1=ret;
        physicalComponent1=physicalComponents.get(ret);
        ret=0;
        while (this.component2.compareTo(physicalComponents.get(ret).getName())!=0){
            ret++;
        }
        numberInInductorVector2=ret;
        physicalComponent2=physicalComponents.get(ret);
    }

    /**
     * LG on March 10, 2015
     * @param prefix
     * @param separtorIfComplex
     * @param postfix
     * @return a string for argument of function or declaration, for the parameter of the component
    */
    public String getParameterName(String prefix, String separtorIfComplex, String postfix){
        return prefix+"K_"+getComponent1()+"_"+getComponent2()+postfix;
    }


}

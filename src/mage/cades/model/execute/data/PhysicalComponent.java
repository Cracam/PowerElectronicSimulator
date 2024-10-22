/*
 * PhysicalComponent.java
 *
 * Created on april 29th, 2002, 10:22
 */

package mage.cades.model.execute.data;
import java.util.ArrayList;

/** Description d'un composant du convertisseur.
 * @author verdiere
 * modified by L. Gerbaud, since 2008
 */
public class PhysicalComponent extends ElectricalCircuitComponent{
	
    //information for the building of the graph
    protected int hotNodeNumber;
    protected int coldNodeNumber;
//////////////        private NodeByPhysicalComponentCollection hotNodeByPhysicalComponentCollection;
//////////////        private NodeByPhysicalComponentCollection coldNodeByPhysicalComponentCollection;

    
    // on july 5th, 2012
    protected String option;
    protected int componentNumber;
    protected int currentSourceNumber;
    protected int voltageSourceNumber;
    protected boolean currentToCompute=false;
    protected boolean voltageToCompute=false;
    protected int colPositionV;
    protected int colPositionI;
    protected String fileName=null; //LG on June 24th, 2013
    protected boolean rectangularFormatFile=false; // LG on January 18th, 2016
    //on April 25th, 2013
    protected boolean implicitGround=false;
    // LG on March 18, 2015 ===>>
    protected String voltageName;
    protected String realVoltageName; 
    protected String realCurrentName;
    protected String currentName;
    protected String imagVoltageName;
    protected String imagCurrentName;
    // <<=== LG on March 18, 2015

    /** Create a new instance of PhysicalComponent.
     */
    public PhysicalComponent() {
        super();
    }

    /** Create a new instance of PhysicalComponent.
     * @param type Type of the component.
     * @param name Name of the component.
     */    
    public PhysicalComponent(String type, String name) {
        super(name);
        this.type = type;
        setVariableNames();// LG on Match 18, 2015
    }

    /** Create a new instance of PhysicalComponent.
     * @param type Type of the component.
     * @param name Name of the component.
     * @param value Numerical value of the PhysicalComponent (without unit, so ISU is supposed).
     */    
    public PhysicalComponent(String type, String name, String value) {
        this(type,name);
        this.stringValue = value;
    }

    /** Create a new instance of PhysicalComponent.
     * @param type Type of the component.
     * @param name Name of the component.
     * @param value Numerical value of the PhysicalComponent (without unit, so ISU is supposed).
     * @param option Option of the component (control, group).
     */    
    public PhysicalComponent(String type, String name, String value, String option) {
        this(type,name,value);
        this.option = option;
    }
    
    //>>>>> LG january 14th, 2010
    /**
     * Create a new instance of PhysicalComponent.
     * @param type Type of the component.
     * @param name Name of the component.
     * @param value Numerical value of the PhysicalComponent (without unit, so ISU is supposed).
     * @param option Option of the component (control, group).
     * @param n1 input node of the current or flux
     * @param n2 output node of the current or flux
     */
    public PhysicalComponent(String type, String name, String value, String option, int n1, int n2) {
        this(type,name,value);
        this.option = option;
//////////                this.setHotNodeNumber(n1);
//////////                this.setColdNodeNumber(n2);
    }
    
    /**
     * Create a new instance of PhysicalComponent.
     * @param type Type of the component.
     * @param name Name of the component.
     * @param n1 input node of the current or flux
     * @param n2 output node of the current or flux
     */
    public PhysicalComponent(String type, String name, int n1, int n2) {
        this(type,name);
////////////                this.setHotNodeNumber(n1);
////////////                this.setColdNodeNumber(n2);
    }
    //<<<<<<<< LG january 14th, 2010

    /**
     * 
     * @return true if the component is a source, false else
     */
    public boolean isSource() {
//            if ( type.equals("SourceTension") || type.equals("SourceTensionLiee") ||
//                    type.equals("SourceCourant") || type.equals("SourceCourantLiee") ||
//                    type.equals("SourceTensionEquivalente") || type.equals("SourceCourantEquivalente") )
        if ( type.equals("VoltageSource") || type.equals("LinkedVoltageSource") ||
            type.equals("CurrentSource") || type.equals("LinkedCurrentSource") ||
            type.equals("EquivalentVoltageSource") || type.equals("EquivalentCurrentSource") )
            return true;
        return false;
    }

    /**
     * 
     * @return the voltage name of the component
     */
    public String getModelicaVoltageName() {
        String ret = "";
        if ( type.equals("Inductor") )
            ret = name+"*der(i_"+name+")";
        else if (type.equals("VoltageSource") || type.equals("LinkedVoltageSource")) 
            ret = "-"+name;		
        else	if ( type.equals("EquivalentVoltageSource") )
            ret = "("+this.stringValue+")";
        else
            ret = "v_"+name;
        return ret;
    }

    /**
     * 
     * @return the current name of the component
     */
    public String getModelicaCurrentName() {
        String ret = "";
        if ( type.equals("Capacitor") )
            ret = name+"*der(v_"+name+")";
        else if ( type.equals("CurrentSource") || type.equals("LinkedCurrentSource")) 
            ret = name;
        else if ( type.equals("EquivalentCurrentSource") )
            ret = "("+this.stringValue+")";
        else
            ret = "i_"+name;
        return ret;	
    }


    /** return the number of the input node of the component.
        * @return number of the input node of the component.
        */    
    public int getHotNodeNumber() {
        return hotNodeNumber;
    }

    /** set the number of the input node of the component.
        * @param node number of the input node of the component.
        */    
    public void setHotNodeNumber(int node) {
        this.hotNodeNumber = node;
    }

    /** return the number of the output node of the component.
        * @return number of the output node of the component.
        */    
    public int getColdNodeNumber() {
        return coldNodeNumber;
    }

    /** set the number of the output node of the component.
        * @param node number of the output node of the component.
        */    
    public void setColdNodeNumber(int node) {
        this.coldNodeNumber = node;
    }

//////////////        // LG june 14th, 2012
//////////////        public NodeByPhysicalComponentCollection getHotNodeByPhysicalComponentCollection(){
//////////////            return this.hotNodeByPhysicalComponentCollection;
//////////////        }
//////////////        public void setHotNodeByPhysicalComponentCollection(NodeByPhysicalComponentCollection hotNodeByPhysicalComponentCollection){
//////////////            this.hotNodeByPhysicalComponentCollection=hotNodeByPhysicalComponentCollection;
//////////////        }
//////////////        public NodeByPhysicalComponentCollection getColdNodeByPhysicalComponentCollection(){
//////////////            return this.coldNodeByPhysicalComponentCollection;
//////////////        }
//////////////        public void setColdNodeByPhysicalComponentCollection(NodeByPhysicalComponentCollection coldNodeByPhysicalComponentCollection){
//////////////            this.coldNodeByPhysicalComponentCollection=coldNodeByPhysicalComponentCollection;
//////////////        }



    
    /** return the numerical value of the PhysicalComponent (without unit, so ISU is supposed).
        * @return Numerical value of the PhysicalComponent (without unit, so ISU is supposed).
        */    
    public void setComponentNumber(int componentNumber) {
        this.componentNumber = componentNumber;
    }

    public int getComponentNumber() {
        return this.componentNumber;
    }

    public void setCurrentSourceNumber(int currentSourceNumber) {
        this.currentSourceNumber = currentSourceNumber;
    }

    public int getCurrentSourceNumber() {
        return this.currentSourceNumber;
    }

    public void setVoltageSourceNumber(int voltageSourceNumber) {
        this.voltageSourceNumber = voltageSourceNumber;
    }
    public int getVoltageSourceNumber() {
        return this.voltageSourceNumber;
    }

    public void setCurrentToCompute (boolean a){
        this.currentToCompute=a;
    }
    public boolean getCurrentToCompute (){
        return this.currentToCompute;
    }
    
    public void setVoltageToCompute (boolean a){
        this.voltageToCompute=a;
    }
    public boolean getVoltageToCompute (){
        return this.voltageToCompute;
    }

    public void setColPositionV(int colPositionV){
        this.colPositionV=colPositionV;
    }
    public int getColPositionV(){
        return this.colPositionV;
    }
    
    public void setColPositionI(int colPositionI){
        this.colPositionI=colPositionI;
    }
    public int getColPositionI(){
        return this.colPositionI;
    }

    /** Return the option of the component (control, group).
        * @return Option of the component (control, group).
        */    
    public String getOption() {
        return this.option;
    }
    public void setOption(String s) {
        this.option = s;
    }

    /**
     * 
     * @param physicalComponents
     * @return the index of this in the ArrayList<PhysicalComponent> physicalComponents
     */
    public int getPhysicalComponentIndexInVector(ArrayList<PhysicalComponent> physicalComponents){
        int ret=0;
        while ((physicalComponents.get(ret).getName()).compareTo(this.getName())!=0){
            ret++;
        }
        return ret;
    }

    //on April 25th, 2013
    public void setImplicitGround(boolean implicitGround){
        this.implicitGround=implicitGround;
    }
    public boolean getImplicitGround(){
        return this.implicitGround;
    }

    // <= LG on june 7th, 2013
    // LG on June 24th, 2013 =>
    public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    
    public String getRealVoltageName() {
        return realVoltageName;
    }

    public void setRealVoltageName(String realVoltageName) {
        this.realVoltageName = realVoltageName;
    }

    public String getRealCurrentName() {
        return realCurrentName;
    }

    public void setRealCurrentName(String realCurrentName) {
        this.realCurrentName = realCurrentName;
    }

    public String getImagVoltageName() {
        return imagVoltageName;
    }

    public void setImagVoltageName(String imagVoltageName) {
        this.imagVoltageName = imagVoltageName;
    }

    public String getImagCurrentName() {
        return imagCurrentName;
    }

    public void setImagCurrentName(String imagCurrentName) {
        this.imagCurrentName = imagCurrentName;
    }

    public String getVoltageName() {
        return voltageName;
    }

    public void setVoltageName(String voltageName) {
        this.voltageName = voltageName;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
    
    
    // <= LG on June 24th, 2013

    //  LG on January 18th, 2016 =>
    public boolean isRectangularFormatFile() {
        return rectangularFormatFile;
    }

    public void setRectangularFormatFile(boolean rectangularFormatFile) {
        this.rectangularFormatFile = rectangularFormatFile;
    }
    //  <= LG on January 18th, 2016
    
    /**
     * 
     * @return a string for the xml representation of the component
     */
    public String toXmlString() {
        String xml = new String("");
        xml += "<PhysicalComponent name=\""+getName()+"\" type=\""+getType()+"\"";
        xml += " value=\""+getStringValue()+"\" option=\""+getOption()+"\"";
        xml += " hotNodeNumber=\""+hotNodeNumber+"\" coldNodeNumber=\""+coldNodeNumber+"\"/>\r\n";
        return xml;
    }
    
    /**
     * LG on March 10, 2015
     * @param prefix
     * @param separtorIfComplex
     * @param postfix
     * @return a string for argument of function or declaration, for the parameter of the component
     * if the component is complex, separtorIfComplex is used
     * the component type that are considered are:
     * "Resistor"
     * "Inductor"
     * "Capacitor"
     * "VoltageSource"
     * "CurrentSource"
     * "BondGraphTransformer"
     * "BondGraphGyrator"
     */
    public String getParameterName(String prefix, String separtorIfComplex, String postfix){
        String ret=new String();
        if (type.compareTo("VoltageSource")==0){
            ret+=prefix+"v_"+name+"_re"+separtorIfComplex+"v_"+name+"_im"+postfix;
        }
        if (type.compareTo("Capacitor")==0){
            ret+=prefix+"R_"+name+postfix;
        }
        if (type.compareTo("Resistor")==0){
            ret+=prefix+"R_"+name+postfix;
        }
        if (type.compareTo("Inductor")==0){
            ret+=prefix+"L_"+name+postfix;
        }
        if (type.compareTo("BondGraphTransformer")==0){
            ret+=prefix+"M_"+name+postfix;
        }
        if (type.compareTo("BondGraphGyrator")==0){
            ret+=prefix+"K_"+name+postfix;
        }
        if (type.compareTo("CurrentSource")==0){
            ret+=prefix+"i_"+name+"_re"+separtorIfComplex+"i_"+name+"_im"+postfix;
        }
        return ret;
    }
    
    
    /**
     * LG on March 18, 2015
     * @param prefix
     * @param separtorIfComplex
     * @param postfix
     * @return 
     */
    public final void setVariableNames(){
        this.voltageName="v_"+name;
        this.realVoltageName="v_"+name+"_re";
        this.imagVoltageName="v_"+name+"_im";
        this.currentName="i_"+name;
        this.realCurrentName="i_"+name+"_re";
        this.imagCurrentName="i_"+name+"_im";
        if (type.compareTo("VoltageSource")==0){
            this.parameterPrefix="E_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("Capacitor")==0){
            this.parameterPrefix="C_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("Resistor")==0){
            this.parameterPrefix="R_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("Inductor")==0){
            this.parameterPrefix="L_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("BondGraphTransformer")==0){
            this.parameterPrefix="M_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("BondGraphGyrator")==0){
            this.parameterPrefix="K_";
            this.parameterName=parameterPrefix+name;
        }
        if (type.compareTo("CurrentSource")==0){
            this.parameterPrefix="J_";
            this.parameterName=parameterPrefix+name;
        }
    }
    
    
    
       
    
    
    
    
    
    
    
    
    
    
}// end of the class

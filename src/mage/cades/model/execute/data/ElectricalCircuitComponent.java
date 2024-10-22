/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.cades.model.execute.data;

/**
 *
 * @author gerbaudl
 * on July 12th, 2013
 */

/**
 * initialy ConverterComponent
 * refactor as ElectricalCircuitComponent on February 17, 2015
 */

public abstract class ElectricalCircuitComponent {
    protected String name;
    protected double numericalValue;
    protected String stringValue;  // refactoring by LG, on july 3rd, 2013
    protected boolean isParameterInTheModelJacobian=false; // LG on june 7th, 2013 to deal with selectivity
    protected int indexInInputVector; //LG on May 5, 2014
    protected String parameterPrefix; //LG on March 18, 2015
    protected String parameterName; //LG on March 18, 2015
    //information on the graph PhysicalComponent
    protected String type;
    protected boolean isAnArrayAccordingTheFrequency=false;// LG on October 19th, 2016
    /**
     * type = 
     * "Resistor"
     * "Inductor"
     * "Capacitor"
     * "VoltageSource"
     * "CurrentSource"
     * "CurrentControlledVoltageSource"  "outputLinkedVoltageSource"
     * "VoltageControlledVoltageSource"  "outputLinkedCurrentSource"
     * "CurrentControlledCurrentSource"
     * "VoltageControlledCurrentSource"
     * "Diode"
     * "Thyristor"
     * "Mos"
     * "ThyristorDual"
     * "Transistor"
     * "TransistorDiodeSerie"
     * "BidirectionnalBicontrolable1"
     * "BidirectionnalBicontrolable2"
     * "PerfectCouplingPrimary"
     * "PerfectCouplingSecondary"
     * "inputLinkedVoltageSource"
     * "inputLinkedCurrentSource"
     * "BondGraphTransformer"
     * "BondGraphTransformerPrimary"
     * "BondGraphTransformerSecondary"
     * "BondGraphGyrator"
     * "BondGraphGyratorPrimary"
     * "BondGraphGyratorSecondary"
     * 
     */
    
    /**
     * basic constructor
     */
    public ElectricalCircuitComponent(){   
    }
    
    /**
     * constructor with:
     * @param name 
     */
    public ElectricalCircuitComponent(String name) {
	this.name = name;
    }
    
    /**
     * constructor with:
     * @param name
     * @param value 
     */
    public ElectricalCircuitComponent(String name, double value) {
	this.name = name;
        this.numericalValue=value;
    }
    
    public String getName() {
            return this.name;
    }
    public void setName(String newName) {
            this.name = newName;
    }
    
    public void setNumericalValue(double numericalValue){
        this.numericalValue=numericalValue;
    }
    public double getNumericalValue(){
        return this.numericalValue;
    }
    public void setNumericalValueFromStringValue(){
        this.numericalValue=(new Double(this.stringValue)).doubleValue();
    }
    
    public void setStringValue(String s) {
        this.stringValue = s;
    }
    public String getStringValue() {
        return this.stringValue;
    }

    public boolean getIsParameterInTheModelJacobian(){
        return this.isParameterInTheModelJacobian;
    }
    public void setIsParameterInTheModelJacobian(boolean isParameterInTheModelJacobian){
        this.isParameterInTheModelJacobian=isParameterInTheModelJacobian;
    }
    
    //LG on May 5, 2014
    public int getIndexInInputVector() {
        return indexInInputVector;
    }
    public void setIndexInInputVector(int indexInInputVector) {
        this.indexInInputVector = indexInInputVector;
    }

    public String getParameterPrefix() {
        return parameterPrefix;
    }

    public void setParameterPrefix(String parameterPrefix) {
        this.parameterPrefix = parameterPrefix;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    
    /** return the type of the component.
     * @return Type of the component.
     */    
    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    /**
     * 
     * @return true if the parameter of the component is an array, of the same size of the frequency array, else the parameter is a scalar
     */
    public boolean isIsAnArrayAccordingTheFrequency() {
        return isAnArrayAccordingTheFrequency;
    }

    /**7
     * 
     * @param isAnArrayAccordingTheFrequency true if the parameter of the component is an array, of the same size of the frequency array, else the parameter is a scalar
     */
    public void setIsAnArrayAccordingTheFrequency(boolean isAnArrayAccordingTheFrequency) {
        this.isAnArrayAccordingTheFrequency = isAnArrayAccordingTheFrequency;
    }

    
    /**
     *
     * @param prefix
     * @param separtorIfComplex
     * @param postfix
     * @return
     */
    public abstract String getParameterName(String prefix, String separtorIfComplex, String postfix);
    
}// end of the class

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mage.cades.model.execute.data;

/**
 * created on March 10, 2015
 * @author gerbaudl
 */
public class FrequencySelectivity {
    private String name="f";
    private boolean derivateAccordingTheFrequency=false;// LG on March 25th, 2016
    private boolean areThereSourcesOrImpendancesDependingOnFrequency=false;// LG on February 25th, 2019
    private int indexInInputVector; 

    public FrequencySelectivity() {
    }
    
    public boolean isDerivateAccordingTheFrequency() {
        return derivateAccordingTheFrequency;
    }

    public void setDerivateAccordingTheFrequency(boolean derivateAccordingTheFrequency) {
        this.derivateAccordingTheFrequency = derivateAccordingTheFrequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndexInInputVector() {
        return indexInInputVector;
    }

    public void setIndexInInputVector(int indexInInputVector) {
        this.indexInInputVector = indexInInputVector;
    }

    // LG on February 25th, 2019 ==>
    public boolean isAreThereSourcesOrImpendancesDependingOnFrequency() {
        return areThereSourcesOrImpendancesDependingOnFrequency;
    }

    public void setAreThereSourcesOrImpendancesDependingOnFrequency(boolean areThereSourcesOrImpendancesDependingOnFrequency) {
        this.areThereSourcesOrImpendancesDependingOnFrequency = areThereSourcesOrImpendancesDependingOnFrequency;
    }
    // <== LG on February 25th, 2019
}

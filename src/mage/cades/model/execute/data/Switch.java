/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mage.cades.model.execute.data;

import java.util.Set;

/**
 *
 * @author LECOURT Camille
 */
public class Switch extends PhysicalComponent {

         protected boolean state;
         //Parameter of the switch state transition:
         private final boolean openableForPositiveTension; // can the componement be able to be opened for positive tension
         private final boolean openableForNegativeTension;
         private final boolean commandClosingForPositiveTension; // can we commant the componement Off-->On for positive tension 
         private final boolean commandOpeningForPositiveTension; // can we command the componement On--> Off for positive tension
         private final boolean commandClosingForNegativeTension;
         private final boolean commandOpeningForNegativeTension;
         // The variable to state the validity of the test :
         //-----for closing test :
         private final boolean closingPassivePositive;
         private final boolean closingPassiveNegative;
         private final boolean closingActivePositive;
         private final boolean closingActiveNegative;
         //-----for opening test :
         private final boolean openingPassivePositive;
         private final boolean openingPassiveNegative;
         private final boolean openingActivePositive;
         private final boolean openingActiveNegative;

         //Physical state our switch can have
         protected double stateOnResistance = 0;
         protected double stateOffResistance = 100000;
         protected double positiveTensionThreshold = 0;
         protected double negativeTensionThreshold = 0;
         //

         public Switch(boolean openableForPositiveTension, boolean openableForNegativeTension, boolean commandClosingForPositiveTension, boolean commandOpeningForPositiveTension, boolean commandClosingForNeagativeTension, boolean commandOpeningForNegativeTension, boolean initialState, String type, String name, int n1, int n2) {
                  super(type, name, n1, n2);
                  this.openableForPositiveTension = openableForPositiveTension;
                  this.openableForNegativeTension = openableForNegativeTension;
                  this.commandClosingForPositiveTension = commandClosingForPositiveTension;
                  this.commandOpeningForPositiveTension = commandOpeningForPositiveTension;
                  this.commandClosingForNegativeTension = commandClosingForNeagativeTension;
                  this.commandOpeningForNegativeTension = commandOpeningForNegativeTension;

//determining transition to closing state
                  if (!this.openableForPositiveTension) {// if the opened state doesn't exit, 
                           this.closingPassivePositive = true;// it's a passive closing
                           this.closingActivePositive = false; //it' not commandable
                  } else {
                           this.closingPassivePositive = false; // no passive clocing
                           this.closingActivePositive = this.commandClosingForPositiveTension; // take the command (if the transition exist
                  }

                  if (!this.openableForNegativeTension) {// if the opened state doesn't exit, 
                           this.closingPassiveNegative = true;// it's a passive closing
                           this.closingActiveNegative = false; //it' not commandable
                  } else {
                           this.closingPassiveNegative = false; // no passive clocing
                           this.closingActiveNegative = this.commandClosingForNegativeTension; // take the command (if the transition exist
                  }

// Determining trasition to opened state :
                  if (this.openableForPositiveTension) {// Is openable for positive tension ?
                           if (this.commandOpeningForPositiveTension) { //Can we command on closing ?
                                    this.openingPassivePositive = false; // no passive changes
                                    this.openingActivePositive = true; // commanded closing transition
                           } else {
                                    this.openingPassivePositive = !this.commandClosingForPositiveTension; // if not commandable in both direction : passive transtion
                                    this.openingActivePositive = false;
                           }
                  } else {// we cannot open so we invalidate the transitions
                           this.openingPassivePositive = false;
                           this.openingActivePositive = false;
                  }

                  if (this.openableForNegativeTension) {// Is openable for positive tension ?
                           if (this.commandOpeningForNegativeTension) { //Can we command on closing ?
                                    this.openingPassiveNegative = false; // no passive changes
                                    this.openingActiveNegative = true; // commanded closing transition
                           } else {
                                    this.openingPassiveNegative = !this.commandClosingForNegativeTension; // if not commandable in both direction : passive transtion
                                    this.openingActiveNegative = false;
                           }
                  } else {// we cannot open so we invalidate the transitions
                           this.openingPassiveNegative = false;
                           this.openingActiveNegative = false;
                  }

                  //Set the initial state of the componement
                  this.state = initialState;
                  if (state) {
                           this.setNumericalValue(this.stateOnResistance);
                  } else {
                           this.setNumericalValue(this.stateOffResistance);
                  }
         }
         
     

         public void setSwitchCaracteristics(double stateOnResistance, double stateOffResistance, double positiveTensionThreshold, double negativeTensionThreshold) {
                  this.stateOnResistance = stateOnResistance;
                  this.stateOffResistance = stateOffResistance;
                  this.positiveTensionThreshold = positiveTensionThreshold;
                  this.negativeTensionThreshold = negativeTensionThreshold;
         }

         /**
          * Toogle the state of colsing the switch (setting the value of the
          * resistance,
          */
         public void close() {
                  this.setNumericalValue(this.stateOnResistance);
                  this.state = false;
                  //set current to 0
         }

         public void open() {
                  this.setNumericalValue(this.stateOffResistance);
                  this.state = true;
                  //set tension to 0
         }
         
         
         

         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
   
         
         /**
          * this program will compute the state of the system using it's command + tension or current (depending on his state)
          * 
          * @return true if a change false if not 
          */
         public boolean computeState() {

                  boolean cmd = ;

                  if (state) { // test to go from close to open
                           double Icomp = ;
                           if (Icomp > 0) {// Positive current
                                    if (openingPassivePositive | (cmd && openingActivePositive)) {
                                             open();
                                             return true;
                                    }

                           } else { //negative current 
                                    if (openingPassiveNegative | (cmd && openingActiveNegative)) {
                                             open();
                                             return true;
                                    }
                           }

                  } else { // test to go from open to close
                           double Vcomp = ;
                           if (Vcomp > positiveTensionThreshold) { // positive tension
                                    if (closingPassivePositive | (cmd && closingActivePositive)) {
                                             close();
                                             return true;
                                    }

                           } else if (Vcomp < negativeTensionThreshold) {
                                    if (closingPassiveNegative | (cmd && closingActiveNegative)) {
                                             close();
                                             return true;
                                    }
                           }

                  }
                  return false;

         }

         
         
         /**
          * This method will determine the next date at wich the componement will compute
          * @return the next date  or 0.0 if not able to predict
          */
         public double computeNextDate() {
                  double nextDate;
                  double time;
                  double ancien_time;
                  
                  if(state){
                           if(openingPassivePositive ){
                                    //compute passive
                                    if(!openingPassiveNegative){
                                             //compute cmd
                                    }
                           }else{
                                    //compute cmd
                                    if(openingPassiveNegative){
                                             //compute passive
                                    }
                           }
                  }else{
                           if(!(closingPassivePositive ^ closingPassiveNegative)){
                                    if(closingPassivePositive){
                                             //compute passing by 0
                                    }else{
                                             //compute cmd=0
                                    }
                           }else{
                                    //compute the 2
                           }
                                    
                  }
         }        

;

// changelent
}

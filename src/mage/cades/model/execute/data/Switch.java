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
         private final boolean OpenableForPositiveTension; // can the componement be able to be opened for positive tension
         private final boolean OpenableForNegativeTension;
         private final boolean  CommandClosingForPositiveTension; // can we commant the componement Off-->On for positive tension 
         private final boolean CommandOpeningForPositiveTension; // can we command the componement On--> Off for positive tension
         private final boolean CommandClosingForNegativeTension;
         private final boolean CommandOpeningForNegativeTension;
         
         
         
         //Physical state our switch can have
         protected double stateOnResistance=0;
         protected double stateOffResistance=100000;
         protected double positiveTensionThreshold=0;
         protected double negativeTensionThreshold=0;
         //

         public Switch(boolean OpenableForPositiveTension, boolean OpenableForNegativeTension, boolean CommandClosingForPositiveTension, boolean CommandOpeningForPositiveTension, boolean CommandClosingForNeagativeTension, boolean CommandOpeningForNegativeTension,boolean initialState, String type, String name, int n1, int n2) {
                  super(type, name, n1, n2);
                  this.OpenableForPositiveTension = OpenableForPositiveTension;
                  this.OpenableForNegativeTension = OpenableForNegativeTension;
                  this.CommandClosingForPositiveTension = CommandClosingForPositiveTension;
                  this.CommandOpeningForPositiveTension = CommandOpeningForPositiveTension;
                  this.CommandClosingForNegativeTension = CommandClosingForNeagativeTension;
                  this.CommandOpeningForNegativeTension = CommandOpeningForNegativeTension;
                  this.state=initialState;
                    if(state) this.setNumericalValue(this.stateOnResistance);
                  else this.setNumericalValue(this.stateOffResistance);
         }
         
         
         

         
         public void setSwitchCaracteristics(double stateOnResistance, double stateOffResistance, double positiveTensionThreshold, double negativeTensionThreshold) {
                  this.stateOnResistance = stateOnResistance;
                  this.stateOffResistance = stateOffResistance;
                  this.positiveTensionThreshold = positiveTensionThreshold;
                  this.negativeTensionThreshold = negativeTensionThreshold;
         }

  
         
         /**
          * Toogle the state of colsing the switch (setting the value of the resistance, 
          */
         public void close(){
                  this.setNumericalValue(this.stateOnResistance);
                  this.state=false;
                  //set current to 0
         }
         
         public void open(){
                  this.setNumericalValue(this.stateOffResistance);
                  this.state=true;
                  //set tension to 0
         }
         
         public boolean computeState(){
                 
                  
                  boolean cmd=;
                  
                  if(state){ // test to go from close to open
                           double Icomp=;
                           if(Icomp>0){
                                    if(OpenableForPositiveTension){
                                             if(CommandOpeningForPositiveTension){
                                                      if(cmd)open();
                                             }else{
                                                      if(!CommandClosingForPositiveTension)open();
                                             }
                                    }
                           }else{
                                    if(OpenableForNegativeTension){
                                             if(CommandOpeningForNegativeTension){
                                                      if(cmd)open();
                                             }else{
                                                      if(!CommandClosingForNegativeTension)open();
                                             }
                                    }
                           }
                           
                  }else{ // test to go from open to close
                            double Vcomp=;
                           if(Vcomp>0){
                                    
                                    if(OpenableForPositiveTension){
                                             if(cmd & CommandClosingForPositiveTension)close();
                                    }else{
                                             close();
                                    }
                           }else{
                                     if(OpenableForNegativeTension){
                                             if(cmd & CommandClosingForNegativeTension)close();
                                    }else{
                                             close();
                                    }
                           }
                
                           
                  }
         }
         
         
         // changelent
}

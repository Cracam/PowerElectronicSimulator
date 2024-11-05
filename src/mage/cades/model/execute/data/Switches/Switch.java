/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mage.cades.model.execute.data.Switches;





/* list of the componement I need to create
  * "Diode" ----
     * "Thyristor"
     * "Mos"
     * "ThyristorDual"
     * "Transistor" ----
     * "TransistorDiodeSerie"
     * "BidirectionnalBicontrolable1"
     * "BidirectionnalBicontrolable2"
*/











import java.util.Set;
import mage.cades.model.execute.data.PhysicalComponent;

/**
 * this class represent any switch componement in power electronic (in an ideal way
 *  the componement is defined by 6 parameter :
 *  openableForPositiveTension and openableForNegativeTension
 * commandClosingForPositiveTension and commandOpeningForPositiveTension
 * commandClosingForNegativeTension and commandOpeningForNegativeTension*
 * 
 * there will have 2 ways of defining it with 2 constructors.
 * - manually write the 6 variable + the componement placing in the project
 * - get the 6 variable by the tupe of the switch
 * 
 * 
 * @author LECOURT Camille
 */
public abstract class Switch extends PhysicalComponent {
         private double time;
         private double old_time;
         
         private boolean state;
         private boolean old_state;
         
         private double tension;
         private double old_tension;
         
         private double current;
         private double old_current;
         
         private double control;
         private double old_control;
         private boolean state_control;
        
         
         
         //Parameter of the switch state transition:
         private final boolean turnableOffForPositiveTension; // can the componement be able to be opened for positive tension
         private final boolean turnableOffForNegativeTension;
         private final boolean turnableOnForPositiveTension;
         private final boolean turnableOnForNegativeTension;
         private final boolean commandTurnOnForPositiveTension; // can we commant the componement Off-->On for positive tension 
         private final boolean commandTurnOffForPositiveTension; // can we command the componement On--> Off for positive tension
         private final boolean commandTurnOnForNegativeTension;
         private final boolean commandTurnOffForNegativeTension;
         
         // The variable to state the validity of the test : (this generic way is not usefull for the state computation, but maybe for the comutation cell computation.
         //-----for closing test :
         private final boolean turnOnPassivePositive;
         private final boolean turnOnPassiveNegative;
         private final boolean turnOnActivePositive;
         private final boolean turnOnActiveNegative;
         //-----for opening test :
         private final boolean turnOffPassivePositive;
         private final boolean turnOffPassiveNegative;
         private final boolean turnOffActivePositive;
         private final boolean tunrOffActiveNegative;

         //Physical state our switch can have
         protected double stateOnResistance = 0;
         protected double stateOffResistance = 100000;
         protected double positiveTensionThreshold = 0;
         protected double negativeTensionThreshold = 0;
         //
         
         /**
          * this is a constructor for a switch
          * @param turnableOffForPositiveTension 1st carateristic of the swith
          * @param turnableOffForNegativeTension 2nd carateristic of the swith
          * @param commandTurnOnForPositiveTension 3rd carateristic of the swith 
          * @param commandTurnOffForPositiveTension 4th carateristic of the swith
          * @param commandTurnOnForNeagativeTension 5th carateristic of the swith
          * @param commandTurnOffForNegativeTension 6th carateristic of the swith
          * @param initialState is on or is off
          * @param type give the type og the conductor
          * @param name name of the switch
          * @param n1 node 1
          * @param n2  node 2
          * 
          * it will after initialize all the 8 variable corresponding to the possible transition for the switch
          * this avoid making 3 true false test
          */
         protected Switch(String type, String name, int n1, int n2,boolean turnableOffForPositiveTension, boolean turnableOffForNegativeTension, boolean commandTurnOnForPositiveTension, boolean commandTurnOffForPositiveTension, boolean commandTurnOnForNeagativeTension, boolean commandTurnOffForNegativeTension) {
                  super(type, name, n1, n2);
                  
         //Componement caracterisation
                  this.turnableOffForPositiveTension = turnableOffForPositiveTension;
                  this.turnableOffForNegativeTension = turnableOffForNegativeTension;
                  
                  this.commandTurnOnForPositiveTension = commandTurnOnForPositiveTension;
                  this.commandTurnOnForNegativeTension = commandTurnOnForNeagativeTension;
                  
                  this.commandTurnOffForPositiveTension = commandTurnOffForPositiveTension;
                  this.commandTurnOffForNegativeTension = commandTurnOffForNegativeTension;
                  
                  //this tow are computed 
                  this.turnableOnForPositiveTension=!this.turnableOffForPositiveTension | this.commandTurnOffForPositiveTension | this.commandTurnOnForPositiveTension;
                  this.turnableOnForNegativeTension=!this.turnableOffForNegativeTension | this.commandTurnOffForNegativeTension | this.commandTurnOnForNegativeTension;

                  
                  
                  
                  // Compute the different possibles transition :
//determining transition to closing state
                  this.turnOnPassivePositive = !this.turnableOffForPositiveTension;
                  this.turnOnPassiveNegative = !this.turnableOffForNegativeTension;
                  
                  this.turnOnActivePositive = this.turnableOffForPositiveTension && this.commandTurnOnForPositiveTension; 
                  this.turnOnActiveNegative = this.turnableOffForNegativeTension && this.commandTurnOnForNegativeTension; 

// Determining trasition to opened state :
                  this.turnOffPassivePositive = this.turnableOffForPositiveTension && this.commandTurnOffForPositiveTension ; 
                  this.turnOffPassiveNegative = this.turnableOffForNegativeTension && this.commandTurnOffForNegativeTension;
                  
                  this.tunrOffActiveNegative =this.turnableOffForPositiveTension &&  !this.commandTurnOffForPositiveTension && !this.commandTurnOnForPositiveTension;
                  this.turnOffActivePositive = this.turnableOffForNegativeTension && !this.commandTurnOnForNegativeTension && !this.commandTurnOffForNegativeTension; 


                  //Set the initial state of the componement
                  this.state = false;
                  this.setNumericalValue(this.stateOffResistance);
         }
         
     private void setControlDemmand(){
              if (this.control>5) this.state_control=true;
              else this.state_control=false;
     }
         
         
         
         /**
          * this program will set the physical caracteristic of the switch 
          * It's optinal because the ideal value will be used if not initialized
          * @param stateOnResistance 
          * @param stateOffResistance
          * @param positiveTensionThreshold
          * @param negativeTensionThreshold 
          */
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
          * this code is used to actualise the new state of the componement
          * set old value, and after set the new ones
          * 
          * WARNING ! ---> USE AFTER state computation
          * 
          */
         private void setOldState(){
                 this.old_time=this.time;
                  this.old_tension=this.tension;
                  this.old_current=this.current;
                  this.old_control=this.control;
                  this.old_state=this.state;
         }
         
         
         
         /**
          * Set the new state of the componement (exept state that will e computed)
          * WARNING --- Use Before state computation
          * @param time
          * @param tension
          * @param current
          * @param control 
          */
         private void setNewEntries(double time, double tension,double current,double control){
                  this.time = time;
                  this.tension = tension;
                  this.current = current;
                  this.control=control;
         }


         
         
   //--------------------------------------------------------------------------------------------------------
  //Compute nextDates  methods :   
         
         
         /**
          * This method will compute the next date at wich the value will cross 0.
          *  It will return 0.0 if there will not have zero crossing
          * And the estimation of the zero corssing date if ths one is positive.
          * @param time
          * @param old_time
          * @param var
          * @param old_var
          * @return 
          */
         private double computeNextDate(double time, double old_time, double var, double old_var){
                  double next_date=time-var*(time-old_time)/(var-old_var);
                  if (next_date>time) return next_date;
                  else return 0.0;
         }
         
         
         /**
          * this program compute the next zero crossing of the tension
          * @return 
          */
         private double computeNextDateWithTension(){
                  return computeNextDate(this.time, this.old_time,this.tension,this.old_tension);
         }
         
         /**
          * this program compute the next zero crossing of the current
          * @return 
          */
          private double computeNextDateWithCurrent(){
                  return computeNextDate(this.time, this.old_time,this.current,this.old_current);
         }


          
   //--------------------------------------------------------------------------------------------------------
  //Compute state methods :
          
              /**
          * this program will make the transition tests for the switch
          * 
          * @return true if a change false if not 
          */
         protected abstract boolean computeStateTurnOffTransition();
         
         /**
          * this program will make the transition tests for the switch
          * 
          * @return true if a change false if not 
          */
         protected abstract boolean computeStateTurnOnTransition();
          
          
          
          
          
 /**
          * this program will compute the state of the system using it's command + tension or current (depending on his state)
          * 
          * @param time
          * @param current
          * @param tension
          * @param control
          * @return true if a change false if not 
          */
                   public boolean ComputeState(double time ,double current, double tension, double control){
                            setNewEntries(time,current,tension, control);
                            setControlDemmand(); // to convert the double variable of control into a boolean (this.state_control)
                                      
                             // State computation : (we compute the new state using the abstracts methods computeStateTurnOffTransition and computeStateTurnOnTransition 
                            if (this.old_state){
                                     this.state=computeStateTurnOffTransition();
                            }else{
                                     this.state=computeStateTurnOnTransition();   
                            }
                            
                            
                            if(this.state != this.old_state){ // if change no dates need to be processed
                                     setOldState(); // refresh the old values
                                     
                                     //exit, the state have changed reset of step
                                     return true;
                            }else{ // ne changes of state so next Date computation :
                                     
                                     setOldState(); // refresh the old values
                                     
                                             // Next Date computation :
                                    if (this.state){
                                             AJOUTERPDI(computeNextDateWithTension()); // A remplacer 
                                    }else{
                                             AJOUTERPDI(computeNextDateWithCurrent()); // A remplacer 
                                    }
                                    
                                    return false;
                            }

                   }
                   
                   
     
         
//Turning On transition
         /**
          * Make the turnOn  passive   transition for positive tension 
          * @return 
          */
         protected boolean turnOnPassivePositiveTransition(){
                  return (this.tension>this.positiveTensionThreshold);
         }
         
         /**
          * Make the turnOn  passive transition for negative  tension 
          * @return 
          */
         protected boolean turnOnPassiveNegativeTransition(){
                  return (this.tension<negativeTensionThreshold);
         }
         
         /**
          * Make the turnOn  active transition for positive  tension 
          * @return 
          */
         protected boolean turnOnActivePositiveTransition(){
                  return ((this.tension>this.positiveTensionThreshold) && this.state_control);
         }
         
         /**
          * Make the turnOn  active transition for positive  tension 

          * @return 
          */
         protected boolean turnOnActiveNegativeTransition(){
                  return ((this.tension<this.negativeTensionThreshold) && this.state_control);
         }
//-----
         
//Turning Off transition :
         
         
         
         /**
          * Make the turn Off passive transiton for a positive tension
          * @return 
          */
         protected boolean turnOffPassivePositiveTransition(){
                  return (this.current >0);
         }
         
         
         /**
          * Make the turn Off passive transiton for a negative tension
          * @return 
          */
         protected boolean turnOffPassiveNegativeTransition(){
                  return (this.current <0);
         }
         
         
          /**
          * Make the turnOff  active transition for positive  tension 
          * @return 
          */
         protected boolean turnOffActivePositiveTransition(){
                  return ((this.current>0) && this.state_control);
         }
         
         /**
          * Make the turnOff  active transition for positive  tension 
          * @return 
          */
         protected boolean turnOffActiveNegativeTransition(){
                  return ((this.current<0) && this.state_control);
         }
         
         
}

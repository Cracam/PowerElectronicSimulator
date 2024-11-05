/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mage.cades.model.execute.data.Switches;


/**
 *
 * @author LECOURT Camille
 */
public class Diode extends Switch{

         public Diode(String name, int n1, int n2) {
                  super("Diode", name, n1, n2, false, true, false, false, false, false);
         }
         
    
         @Override
         protected boolean computeStateTurnOffTransition() {
                  return turnOffPassiveNegativeTransition();
         }

         @Override
         protected boolean computeStateTurnOnTransition() {
                  return turnOnPassivePositiveTransition();
         }
         
}

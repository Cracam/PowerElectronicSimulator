/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.Componement;


/**
 *This exeption is showed when a code for an undefinet componement is executed  
 * @author Administrateur
 */
public class ComponementNotDefinedException extends Exception{

    public ComponementNotDefinedException() {
        super();
    }

    public ComponementNotDefinedException(String string) {
        super(string);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Execptions;


/**
 *This exeption is showed when a code for an undefinet componement is executed  
 * @author Administrateur
 */
public class ComponementNotDefinedExeption extends Exception{

    public ComponementNotDefinedExeption() {
        super();
    }

    public ComponementNotDefinedExeption(String string) {
        super(string);
    }

}

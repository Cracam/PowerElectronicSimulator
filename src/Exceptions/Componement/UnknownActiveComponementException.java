/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.Componement;

/**
 * Exeption throw whent the active componement in entry does not exist in the XML file caractristic
 * @author Administrateur
 */
public class UnknownActiveComponementException extends Exception{

    public UnknownActiveComponementException() {
        super();
    }

    public UnknownActiveComponementException(String string) {
        super(string);
    }
    
}

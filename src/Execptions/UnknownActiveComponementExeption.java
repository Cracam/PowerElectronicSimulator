/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Execptions;

/**
 * Exeption throw whent the active componement in entry does not exist in the XML file caractristic
 * @author Administrateur
 */
public class UnknownActiveComponementExeption extends Exception{

    public UnknownActiveComponementExeption() {
        super();
    }

    public UnknownActiveComponementExeption(String string) {
        super(string);
    }
    
}

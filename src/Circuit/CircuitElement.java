/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Circuit;

import Componement.Componement;

/**
 *
 * @author Administrateur
 */
public class CircuitElement {
    private final String name;
    private final int compCount;
    
    
    
    private Componement[] componements;
    
    /**
     * This constructor create a new mesh with a name, compCount representing the number of componement in a mesh.
     * @param type_name /the type of the Circuit element (node,mesh,cell)
     * @param count 
     */
    public CircuitElement(String type_name,int count) {
        this.name = type_name+count;
        this.compCount = count;
        this.componements=new Componement[count];
    }

    public String getName() {
        return name;
    }

    public int getCompcount() {
        return compCount;
    }

    public Componement[] getComponements() {
        return componements;
    }

    public void setComponements(Componement[] componements) {
        this.componements = componements;
    }

    public void setOneComponements(Componement componements,int index) {
        this.componements[index] = componements;
    }
}

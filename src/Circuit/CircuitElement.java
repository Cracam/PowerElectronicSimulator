/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Circuit;

import Componement.Componement;

/**
 *This abstract class is a group of componement and is practical for the definition of one Mesh,Cell,Node.
 * @author  LECOURT Camille
 */
public abstract class CircuitElement {
    private final String name;
    private final int compCount;
    
    
    
    private Componement[] componements;
    
    /**
     * This constructor create a new mesh with a name, compCount representing the number of componement in a mesh.
     * @param type_name /the type of the Circuit element (node,mesh,cell)
     * @param count 
     */
    public CircuitElement(String type_name,int count) {
        this.name = "Unspecified_"+count;
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
    
    /**
     * Set A componeement in the componement Array at an index
     * @param componements
     * @param index 
     */
    public void setOneComponements(Componement componements,int index) {
        this.componements[index] = componements;
    }
}

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
public class Mesh {
    private final String name;
    private final int compCount;
    private static int meshsCount;
    
    
    private Componement[] componements;
    
    /**
     * This constructor create a new mesh with a name, compCount representing the number of componement in a mesh.
     * @param count 
     */
    public Mesh( int count) {
        this.name = "mesh_"+count;
        this.compCount = count;
        Mesh.meshsCount++;
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

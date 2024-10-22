/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Circuit;


/**
 * This class represent a mesh in a electrical circuit
 * @author LECOURT Camille
 */
public class Mesh extends CircuitElement{
    private static int meshsCount;
    

    /**
     * This constructor create a new mesh with a name, compCount representing the number of componement in a mesh.
     * @param count 
     */
    public Mesh( int count) {
        super("mesh_",count);
        Mesh.meshsCount++;
    }

    public static int getMeshsCount() {
        return Mesh.meshsCount;
    }

}

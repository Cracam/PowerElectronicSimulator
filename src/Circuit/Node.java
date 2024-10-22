/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Circuit;



/**
 *This class reprensent a Node in an electrical circuit.
 * @author LECOURT Camille
 */
public class Node extends CircuitElement{
    private static int nodesCount;
    

    /**
     * This constructor create a new mesh with a name, compCount representing the number of componement in a mesh.
     * @param count 
     */
    public Node( int count) {
        super("mesh_",count);
        Node.nodesCount++;
    }

    public static int getNodesCount() {
        return Node.nodesCount;
    }

    
    
    
    
}
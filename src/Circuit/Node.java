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
public class Node {
    private final String name;
    private final int compCount;
    
    private Componement[] componements;
    private static int nodesCount;
    
    public Node(int compCount) {
        this.compCount = compCount;
        Node.nodesCount++;
        this.name="node_"+Node.nodesCount;
        this.componements=new Componement[compCount];
    }

    public String getName() {
        return name;
    }

    public int getCompCount() {
        return compCount;
    }

    public static int getNodesCount() {
        return nodesCount;
    }
    
    
    public void setComponements(Componement[] componements) {
        this.componements = componements;
    }

    public void setOneComponements(Componement componements,int index) {
        this.componements[index] = componements;
    }
    
}

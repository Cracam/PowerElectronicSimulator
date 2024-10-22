/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.cades.model.execute.data;

import java.util.ArrayList;

/**
 *
 * @author gerbaudl
 * february 27th, 2013
 * This class should be welcome to deal with nodale solving of circuit
 * including coupled inductor.
 * the idea is to set and compute locally the coupled inductors, using local impedances matrixes
 * and to inject the local addmitance matrixes obtained from the impendance Inverses in the global
 * solving by managing local and global indexes
 */
public class LinkedMutualSet {
    protected ArrayList<PhysicalComponent> localInductorVector;
    protected ArrayList<Mutual> localMutualVector;
    protected int[][] transformationFromALinkedMutualSet;
    protected int[][] transformationFromLinkedMutualSetToA;
    
    /**
     * basic constructor
     */
    public LinkedMutualSet(){
        localInductorVector=new ArrayList<PhysicalComponent>();
        localMutualVector=new ArrayList<Mutual>();
    }
    
    public ArrayList<PhysicalComponent> getLocalInductorVector(){
        return this.localInductorVector;
    }
   
    public void setLocalInductorVector(ArrayList<PhysicalComponent> localInductorVector){
        this.localInductorVector=localInductorVector;
    }
   
    protected ArrayList<Mutual> getLocalMutualVector(){
        return this.localMutualVector;
    }
    
    protected void setLocalMutualVector(ArrayList<Mutual> localMutualVector){
        this.localMutualVector =localMutualVector;
    }
    
    /** 
     * System.out.println of the linked mutual set
     */
    public void printLinkedMutualSet(){
        System.out.println("\nset of linked mutual :\n");
        for (int j=0;j<localInductorVector.size();j++){
            System.out.println("  "+localInductorVector.get(j).getName());
        }
        for (int j=0;j<localMutualVector.size() ;j++){
            System.out.println("  "+localMutualVector.get(j).getName());
        }
        
    }
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////
///////////////////// To do ///////////////////////////////    
////////////////////////////    // this method uses the position obtained from XXXXXEquiConv.computePhysicalComponentPosition() that give
////////////////////////////    // the position of the inductor in A and nowing if a 
////////////////////////////    public void buildTransformationFromLinkedMutualSetToA(){
////////////////////////////        transformationFromLinkedMutualSetToA=new int[][]
////////////////////////////    }

}// end of the class

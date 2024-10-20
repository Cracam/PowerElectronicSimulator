/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestComponement;

import Componement.ComponementActive;
import org.junit.Test;

public class TestActiveComponement{
    

    @Test
    public void TestXmlRead(){
        ComponementActive comp;
        comp = new ComponementActive("T45", 3, 56);
        System.out.println(comp.toString());
        comp.physicalDescription(0.02f,20000f , 1.2f, -1.2f);
        System.out.println(comp.toString());
    }



}
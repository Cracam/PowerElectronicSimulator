/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componement;

import Exceptions.UndefinedPhysicalCaracteristicException;
import Exceptions.UnknownActiveComponementException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrateur
 */
public class ComponementActive extends Componement {
    //The 6 variables describing the active componement behavior
    private boolean V_p;
    private boolean V_n;
    private boolean P_OffOn;
    private boolean P_OnOff;
    private boolean N_OffOn;
    private boolean N_OnOff; // je voudrais les mettre final mais ça bloque
    
    private static final String[] caracList={"Vp","Vn","P_OnOff","P_OffOn","N_OnOff","N_OffOn"};
    
    
    //cell linked to our componement (null if not linked in a cell)
    //private Cell cell;
    private boolean slaved;

    
    
    //Pysical caracteristic of the compinement :
    private float R_On; //resitance when closed (very low)
    private float R_Off;//resistance when opened (very high)
    private float V_Th_P;// threshold for the V+ closing case 
    private float V_Th_N;// threshold for V- closing case 
    
    
    
    
    
    //Count of active componement
    public static int count;
    
    
    public ComponementActive(String name, int imputNode, int outputNode) {
            super(name, imputNode, outputNode);
            System.out.println("Componement.ComponementActive.<init>() type="+this.getType());
            ComponementActive.count=ComponementActive.count+1;   System.out.println("Componement.ComponementActive.<init>() type="+this.getType());
            this.R_On=-1;   //Set a impossible value to check that the user implement a value 
            this.R_Off=-1;  //Set a impossible value to check that the user implement a value 
            this.V_Th_P=-1; //Set a impossible value to check that the user implement a value 
            this.V_Th_N=-1; //Set a impossible value to check that the user implement a value 
            this.slaved=false;
        try{
            //set carac with XML comparator
            boolean[] carac =getCarateristics();
            if( carac==null){ //à améliorer ???
                throw new UnknownActiveComponementException("The componement : "+getType()+" doesn't exist in XML datafiles");
            }
            else{
            this.V_p=carac[0];
            this.V_n=carac[1];
            this.P_OnOff=carac[2];
            this.P_OffOn=carac[3];
            this.N_OnOff=carac[4];
            this.N_OffOn=carac[5];
            }
            
        }catch(UnknownActiveComponementException e){
            System.out.println(e.getMessage());
            System.out.println("\n\n ****************** Program Stopping ****************** \n\n");
            System.exit(1);
        }
    }
    
    /**
     * This program use the type of the Componement in order to get it's 6 comutation caracteristic
     * It can get exeption if the file where componement data is stored is unfoundable
     * Or if the componement type doesn't exist in this file
     * @return a size 6 boolean array containing "Vp","Vn","P_OnOff","P_OffOn","N_OnOff","N_OffOn"
     */
    private boolean[] getCarateristics(){
        //C:\BACKUP\Sauv\ENSE3\3A\PROJET_RECHERCHE\PowerElectronicSimulator\XML_Data
        boolean[] carac=new boolean[6];
        try {
            File file = new File("C:\\BACKUP\\Sauv\\ENSE3\\3A\\PROJET_RECHERCHE\\PowerElectronicSimulator\\XML_Data\\ActiveComponementCaracs.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeListOfActiveComponements = doc.getElementsByTagName("ActiveComponement");
            
            Node ActiveNodeComponement;
            for(int i=0;i<nodeListOfActiveComponements.getLength();i++){
                ActiveNodeComponement = nodeListOfActiveComponements.item(i);

                if (ActiveNodeComponement.getNodeType() == Node.ELEMENT_NODE) {
                    Element ActiveComponement =(Element) ActiveNodeComponement;
                    //System.out.println(ActiveComponement.getAttribute("prefix")); //Indicator of all the elements checked
                    if (ActiveComponement.getAttribute("prefix").equals(this.getType())){
                        for (int j=0;j<caracList.length;j++){
                            carac[j]=Boolean.parseBoolean(ActiveComponement.getAttribute(caracList[j]));
                        }
                        return carac;
                    }
                }
            }
            //if we get there it's mean that we dont get the active comonement caracteristic in the XML file
            throw new UnknownActiveComponementException("The Active componment "+this.getName() + " does not not have a caracteristic in the XML_Data\\ActiveComponementCaracs.xml file");
            
            
            
        } catch (UnknownActiveComponementException | IOException | ParserConfigurationException | SAXException e) {
            
            System.out.println(e.getMessage());
            return null;
        }
         
        
    }

    
    
    /**
     * This function is used to link physical caracteristic to the componement
     * like a setter but for 4 parameter at the same time
     * @param R_On
     * @param R_Off 
     * The thresholds variables 'V_Th_P' and 'V_Th_N' will be set to 0 by default
     */
    public void physicalDescription(float R_On, float R_Off) {
        physicalDescription(R_On, R_Off, 0f, 0f);
    }
    
    /**
     * This function is used to link physical caracteristic to the componement
     * like a setter but for 4 parameter at the same time
     * The sign doesn't matter
     * 
     * @param R_On resitance when closed (very low)
     * @param R_Off resistance when opened (very high)
     * @param V_Th_P threshold for the V+ closing case 
     * @param V_Th_N threshold for V- closing case 
     */
    public void physicalDescription(float R_On,float R_Off,float V_Th_P,float V_Th_N){
        this.R_On = Math.abs(R_On);
        this.R_Off = Math.abs(R_Off);
        this.V_Th_P = Math.abs(V_Th_P);
        this.V_Th_N = Math.abs(V_Th_N);
    }
            
    //Booleans caracteristic of the componement      
    public boolean isV_p() {
        return V_p;
    }

    public boolean isV_n() {
        return V_n;
    }

    public boolean isP_OffOn() {
        return P_OffOn;
    }

    public boolean isP_OnOff() {
        return P_OnOff;
    }

    public boolean isN_OffOn() {
        return N_OffOn;
    }

    public boolean isN_OnOff() {
        return N_OnOff;
    }
    
    // get for physical caracteristics
    public float getR_On() {
        return R_On;
    }

    public float getR_Off() {
        return R_Off;
    }

    public float getV_Th_P() {
        return V_Th_P;
    }

    public float getV_Th_N() {
        return V_Th_N;
    }

    /**
     * Return the number off active componement created
     * @return Number off active componement created
     */
    public static int getCount() {
        return count;
    }
    
    
    
    
    
    
    
    @Override
    public String toString(){
        return "Active compnement named : "+this.getName()+
                "\n Of type : "+this.getType()+ " number : "+this.getNumber()+
                "\n Imput Node : "+this.getImputNode()+ " Output Node : "+ getOutputNode()+
                
                "\n\n 6 boolean to Caracterise : "+
                "\n Vp="+isV_p()+"  Vn="+isV_n()+
                "\n P_OnOff="+isP_OnOff()+" P_OffOn="+isP_OffOn()+
                "\n N_OnOff="+isN_OnOff()+" N_OffOn="+isN_OffOn()+
                
                "\n\n Physicals Caracteristics : \n"+
                " R_Off="+getR_Off()+"  R_On="+getR_On()+
                "\n V_Th_P="+getV_Th_P()+" T_Th_N="+getV_Th_N();
    }

  
    @Override
    public String[] genCode(){
        try{
        if (R_On==-1 | R_Off==-1 | V_Th_P==-1 | V_Th_N==-1){
            throw new UndefinedPhysicalCaracteristicException("The physical Caracteristic of the componement "+this.getName()+" are not Initialised ");
        }
         
//        String returnProgram_Initialisation;
//        return Program_Initialisation="boolean State_"+this.name";\n"+ "";
//         //the initialisetion of the variables linked to our transistor
//        String returnProgram_Looper="";         //the main body of the program
        
     
         }catch(UndefinedPhysicalCaracteristicException e){
            System.out.println(e.getMessage());
         }
         
         
         
         
         
         
         
         
         
         
         
         
        
    }
    
    
}

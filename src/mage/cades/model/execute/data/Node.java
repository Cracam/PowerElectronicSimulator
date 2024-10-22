/*
 * Node.java
 *
 * Created on 29 avril 2002, 10:27
 */

package mage.cades.model.execute.data;

/** Representation d'un noeud du circuit électrique.
 * @author Franck VERDIERE
 */
public class Node {

    private int nodeNumber;
    private PhysicalComponent[] listIn;
    private PhysicalComponent[] listOut;

    /** create a new instance of Node.
     * @param nodeNumber Numéro du noeud.
     * @param listIn Tableau des composants entrants du noeud.
     * @param listOut Tableau des composants sortants du noeud.
     */
    public Node(int nodeNumber, PhysicalComponent[] listIn, PhysicalComponent[] listOut) {
        this.nodeNumber = nodeNumber;
        this.listIn = listIn;
        this.listOut = listOut;
    }

    /** Getter for property listIn.
     * @return Value of property listIn.
     */
    public PhysicalComponent[] getListIn() {
        return this.listIn;
    }

    /** Getter for property listOut.
     * @return Value of property listOut.
     */
    public PhysicalComponent[] getListOut() {
        return this.listOut;
    }

    /** Getter for property nodeNumber.
     * @return Value of property nodeNumber.
     */
    public int getNodeNumber() {
        return nodeNumber;
    }

    /**
     * 
     * @return a string for the XML representation of the node
     */
    public String toXmlString() {
        String xml = new String("");

        xml += "<NODE number=\""+nodeNumber+"\">\r\n";			

        xml += "\t<INPUTS>\r\n";
        for (int j=0 ; j<listIn.length ; j++) 
                xml += "\t\t"+listIn[j].toXmlString();
        xml += "\t</INPUTS>\r\n";

        xml += "\t<OUTPUTS>\r\n";
        for (int j=0 ; j<listOut.length ; j++) 
                xml += "\t\t"+listOut[j].toXmlString();
        xml += "\t</OUTPUTS>\r\n";

        xml += "</NODE>\r\n";

        return xml;
    }

}// end of the class

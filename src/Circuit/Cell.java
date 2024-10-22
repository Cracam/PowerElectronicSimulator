
package Circuit;

/**
 * This class represent a commutation cell
 * 
 * @author LECOURT Camille
 */
public class Cell extends CircuitElement{
    private static int cellsCount;
    
    public Cell(int count){
        super("cell_",count);
        Cell.cellsCount++;
    }
    
     public static int getCellsCount() {
        return Cell.cellsCount;
    }
}

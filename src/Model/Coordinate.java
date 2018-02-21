package Model;

/**
 * Maintains a location on a grid to be used by other classes
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Coordinate {
    private static final int MAX_COORDINATE = 9;
    private int rowIndex;
    private int colIndex;

    public Coordinate(int rowIndex, int colIndex){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public boolean equals(Coordinate other){
        return rowIndex == other.rowIndex && colIndex == other.colIndex;
    }

    public void increment(){
        colIndex++;
        if(colIndex > MAX_COORDINATE){
            colIndex = 0;
            rowIndex++;
            if(rowIndex > MAX_COORDINATE){
                rowIndex = 0;
            }
        }
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}
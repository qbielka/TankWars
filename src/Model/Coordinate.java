package Model;

/**
 * maintains a location on a grid to be used by other classes
 */
public class Coordinate {
    private static final int MAX_COORDINATE = 9;
    private int rowIndex;
    private int colIndex;

    public Coordinate(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public void increment(){
        rowIndex++;
        if(rowIndex > MAX_COORDINATE){
            rowIndex = 0;
            colIndex++;
            if(colIndex > MAX_COORDINATE){
                colIndex = 0;
            }
        }
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}

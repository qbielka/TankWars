public class coordinate {
    public static final int MAX_COORDINATE = 9;
    private int rowIndex;
    private int colIndex;

    public coordinate(int rowIndex, int colIndex) {
        this.rowIndex=rowIndex;
        this.colIndex=colIndex;
    }

    public void increment(){
        rowIndex++;
        if(rowIndex > MAX_COORDINATE){
            rowIndex=0;
            colIndex++;
            if(colIndex > MAX_COORDINATE){
                colIndex=0;
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

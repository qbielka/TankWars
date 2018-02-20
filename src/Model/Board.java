package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Board maintains a state of the game
 */
public class Board {
    private static final short BOARD_SIZE=10;
    private static final int UPPER_TANK_THRESHOLD = 25;
    private static final int MIDDLE_TANK_THRESHOLD = 20;
    private static final int TETROID_SIZE = 4;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int MAX_TRIES_BEFORE_GIVE_UP = 20000;

    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private List<Tank> tanks;


    // constructors and factory
    private Board(){}
    public static Board makeDisplayBoard(){
        Board toRet= new Board();
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = Tile.getTileFog();
            }
        }
        return toRet;
    }

    public static Board makeSecretBoard(int numTanks) throws Exception {
        Board toRet = makeBlankBoard(Tile.getTileBlank());
        boundsCheck(numTanks);

        List<Tank> myTanks = new ArrayList<>();// needs something extra

        for(int tankNumber = 0; tankNumber < numTanks; tankNumber++) {
            List<Coordinate> aTank = new ArrayList<>();
            Int pStartCol = new Int(randomCoordinate());
            Int pStartRow = new Int(randomCoordinate());

            findGoodSpot(toRet, pStartCol, pStartRow);

            Tank tankToAdd = new Tank(aTank);
            Int tetroidSize = new Int(0);
            Int currRow;
            Int currCol;

            makePointTank(toRet, tankNumber, aTank, pStartCol.wrapperInt, pStartRow.wrapperInt);
            tetroidSize.wrapperInt++;
            makeTankFromSeed(toRet, tankNumber, aTank, tetroidSize);
            myTanks.add(tankToAdd);
        }
        toRet.tanks = myTanks;
        return toRet;
    }

    private static void findGoodSpot(Board toRet, Int pStartCol, Int pStartRow) {
        int numberOfTrys = 0;

        // while board position is a tank tile or if there's not enough space for a tank, then retry
        while (isGoodLocation(toRet, pStartCol.wrapperInt, pStartRow.wrapperInt)) {
            pStartCol.wrapperInt = randomCoordinate();
            pStartRow.wrapperInt = randomCoordinate();
            numberOfTrys++;

            // If random check doesn't find empty spot, look through all spots
            if( numberOfTrys >= MAX_TRIES_BEFORE_GIVE_UP) {
                if (isNoPositions(toRet)) {
                    throw new IllegalArgumentException("Cannot place all tanks");
                }
            }
        }
    }

    private static void makeTankFromSeed(Board toRet, int tankNumber, List<Coordinate> aTank, Int tetroidSize) {
        Int currCol;
        Int currRow;
        while (tetroidSize.wrapperInt < TETROID_SIZE) {
            Int randomDirection = new Int((int) (Math.random() * 4));
            Int randomPiece = new Int((int) (Math.random() * aTank.size()));
            currCol = new Int ( aTank.get(randomPiece.wrapperInt).getColIndex());
            currRow = new Int (aTank.get(randomPiece.wrapperInt).getRowIndex());

            if (randomDirection.wrapperInt == UP &&
                    ((currCol.wrapperInt+1) < BOARD_SIZE && toRet.board[currRow.wrapperInt][currCol.wrapperInt+1] == Tile.getTileBlank())) {
                currCol.wrapperInt++;
                makePointTank(toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt);
                tetroidSize.wrapperInt++;

            }
            else if (randomDirection.wrapperInt == RIGHT &&
                    ((currRow.wrapperInt+1) < BOARD_SIZE && toRet.board[currRow.wrapperInt+1][currCol.wrapperInt] == Tile.getTileBlank())) {
                currRow.wrapperInt++;
                makePointTank(toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt);
                tetroidSize.wrapperInt++;

            }
            else if (randomDirection.wrapperInt == DOWN &&
                    ((currCol.wrapperInt-1) > 0 && toRet.board[currRow.wrapperInt][currCol.wrapperInt-1] == Tile.getTileBlank())) {
                currCol.wrapperInt--;
                makePointTank(toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt);
                tetroidSize.wrapperInt++;

            }
            else if (randomDirection.wrapperInt == LEFT &&
                    ((currRow.wrapperInt-1) > 0 && toRet.board[currRow.wrapperInt-1][currCol.wrapperInt] == Tile.getTileBlank())) {
                currRow.wrapperInt--;
                makePointTank(toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt);
                tetroidSize.wrapperInt++;
            }
        }
    }

    private static boolean isGoodLocation(Board toRet, int pStartCol, int pStartRow) {
        return toRet.board[pStartRow][pStartCol] == Tile.getTileTank() || !floodFillCheck(toRet.board, pStartRow, pStartCol);
    }

    private static int randomCoordinate() {
        return (int) (Math.random() * 10);
    }

    private static void boundsCheck(int numTanks) {
        if(numTanks > UPPER_TANK_THRESHOLD){
            throw new IllegalArgumentException("Too many Tanks");
        }else if(numTanks <= 0){
            throw new IllegalArgumentException("Too few Tanks");
        }
    }

    private static Board makeBlankBoard(char tileBlank) {
        Board toRet = new Board();
        // initialize to blank
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                toRet.board[row][column] = tileBlank;
            }
        }
        return toRet;
    }

    private static boolean isNoPositions(Board toRet) {
        boolean spaceAvailable = false;

        for( int row = 0; row < BOARD_SIZE; row++ ){
            for( int column = 0; column < BOARD_SIZE; column++ ){
                // If space available, set flag to true
                if( floodFillCheck(toRet.board, row, column) ) {
                    spaceAvailable = true;
                }
            }
        }


        return !spaceAvailable;
    }

    private static void makePointTank(Board toRet, final int tankNumber, List<Coordinate> aTank, final int col, final int row) {
        toRet.board[row][col] = (char) (Tile.getTileTank() + tankNumber);
        aTank.add(new Coordinate(row, col));
    }

    //helper for BoardLinker
    public char getTile(Coordinate point){
        return board[point.getRowIndex()][point.getColIndex()];
    }

    public void makeTileHit(Coordinate point) {
        if (board[point.getRowIndex()][point.getColIndex()] == Tile.getTileFog()){
            board[point.getRowIndex()][point.getColIndex()] = Tile.getTileHit();
        }
    }

    public List<Tank> getTanks(){
        return tanks;
    }

    // checks for factory methods
    private static void floodFill(char[][] board, int floodRow, int floodCol, Int numInFill){

        if(board[floodRow][floodCol]==Tile.getTileBlank()){
            numInFill.wrapperInt++;
            board[floodRow][floodCol]=Tile.getTileHit();
        }
        else {
            return;
        }
        if(numInFill.wrapperInt >= TETROID_SIZE){
            return;
        }
        if((floodRow+1) < BOARD_SIZE){
            floodFill(board, floodRow+1, floodCol, numInFill);
        }
        if((floodRow-1) > 0){
            floodFill(board, floodRow-1, floodCol, numInFill);
        }
        if((floodCol+1) < BOARD_SIZE){
            floodFill(board, floodRow, floodCol+1, numInFill);
        }
        if((floodCol-1) > 0){
            floodFill(board, floodRow, floodCol-1, numInFill);
        }
    }

    private static boolean floodFillCheck(char[][] board, int floodRow, int floodCol){
        char[][] thisBoard = new char[BOARD_SIZE][BOARD_SIZE];
        if(board[floodRow][floodCol] != Tile.getTileBlank()){
            return false;
        }
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                thisBoard[row][column] = board[row][column];
            }
        }
        Int sizeOfEmptySpace = new Int(0);
        floodFill(thisBoard, floodRow, floodCol, sizeOfEmptySpace);
        return sizeOfEmptySpace.wrapperInt >= TETROID_SIZE;
    }

}

class Int {
    public int wrapperInt = 0;

    Int(int item){
        wrapperInt = item;
    }
}
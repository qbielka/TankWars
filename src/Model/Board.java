package Model;

import Model.Tank;
import Model.Tile;
import Model.coordinate;

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

    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private List<Tank> tanks;


    // constuctors and factory
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
        Board toRet= new Board();
        // initialize to blank
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = Tile.getTileBlank();
            }
        }
        if(numTanks > UPPER_TANK_THRESHOLD){
            throw new IllegalArgumentException("Too many Tanks");
        }else if(numTanks <= 0){
            throw new IllegalArgumentException("Too few Tanks");
        }
        // make tanks drive onto field
        List<Tank> mytanks = new ArrayList<>();// needs something extra
        for(int tankNumber = 0; tankNumber < numTanks; tankNumber++) {
            List<coordinate> aTank = new ArrayList<>();
            int pStartCol = (int) (Math.random() * 10);
            int pStartRow = (int) (Math.random() * 10);
            // yes this while loop may be infinite with >15 tanks
            while (toRet.board[pStartRow][pStartCol] == Tile.getTileTank() || !floodFillCheck(toRet.board, pStartRow, pStartCol)) {
                pStartCol = (int) (Math.random() * 10);
                pStartRow = (int) (Math.random() * 10);
            }
            Tank toAdd = new Tank(aTank);
            int tetroidSize=0;
            int currRow = pStartRow;
            int currCol = pStartCol;

            makePointTank(toRet, tankNumber, aTank, pStartCol, pStartRow);
            tetroidSize++;
            while (tetroidSize < TETROID_SIZE) {
                int randomDirection = (int) (Math.random() * 4);
                int randomPiece = (int) (Math.random() * aTank.size());
                currCol = aTank.get(randomPiece).getColIndex();
                currRow = aTank.get(randomPiece).getRowIndex();

                if (randomDirection == UP &&
                        ((currCol+1) < BOARD_SIZE && toRet.board[currRow][currCol+1] == Tile.getTileBlank())) {
                    currCol++;
                    makePointTank(toRet, tankNumber, aTank, currCol, currRow);

                    tetroidSize++;

                }
                else if (randomDirection == RIGHT &&
                        ((currRow+1) < BOARD_SIZE && toRet.board[currRow+1][currCol] == Tile.getTileBlank())) {
                    currRow++;
                    makePointTank(toRet, tankNumber, aTank, currCol, currRow);
                    tetroidSize++;

                }
                else if (randomDirection == DOWN &&
                        ((currCol-1) > 0 && toRet.board[currRow][currCol-1] == Tile.getTileBlank())) {
                    currCol--;
                    makePointTank(toRet, tankNumber, aTank, currCol, currRow);
                    tetroidSize++;

                }
                else if (randomDirection == LEFT &&
                        ((currRow-1) > 0 && toRet.board[currRow-1][currCol] == Tile.getTileBlank())) {
                    currRow--;
                    makePointTank(toRet, tankNumber, aTank, currCol, currRow);
                    tetroidSize++;
                }
            }
            mytanks.add(toAdd);
        }
        toRet.tanks =mytanks;
        return toRet;
    }

    private static void makePointTank(Board toRet, final int tankNumber, List<coordinate> aTank, final int col, final int row) {
        toRet.board[row][col] = (char) (Tile.getTileTank() + tankNumber);
        aTank.add(new coordinate(row, col));
    }

    //helper for BoardLinker
    public char getTile(coordinate point){
        return board[point.getRowIndex()][point.getColIndex()];
    }

    public void makeTileHit(coordinate point) {
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
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                thisBoard[row][column] = board[row][column];
            }
        }
        Int sizeOfEmptySpace = new Int();
        floodFill(thisBoard, floodRow, floodCol, sizeOfEmptySpace);
        return sizeOfEmptySpace.wrapperInt >= TETROID_SIZE;
    }

}

class Int {
    public int wrapperInt = 0;
}
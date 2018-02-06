import java.util.ArrayList;
import java.util.List;

/**
 * Board maintains a state of the game
 */
public class Board {
    private static final short BOARD_SIZE=10;
    private static final int UPPER_TANK_THRESHOLD = 25;
    private static final int LOWER_TNK_THRESHOLD = 15;
    private static final int MIDDLE_TANK_THRESHOLD = 20;
    private static final char TILE_BLANK = ' ';
    private static final char TILE_FOG = '~';
    private static final char TILE_HIT = 'X';
    private static final char TILE_TANK = 'A';
    private static final int TETROID_SIZE = 4;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private char[][] board;

    // sets all tiles to unknown
    public static Board makeDisplayBoard(){
        Board toRet= new Board();
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = TILE_FOG;
            }
        }
        return toRet;
    }
    public static Board makeSecretBoard(int numTanks){
        Board toRet= new Board();
        // initialize to blank
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = TILE_BLANK;
            }
        }
        if(numTanks > UPPER_TANK_THRESHOLD){
            numTanks = UPPER_TANK_THRESHOLD;
        }
        // make tanks drive onto field
        List<Tank> mytanks = new ArrayList<>();// needs something extra
        for(int tankNumber = 0; tankNumber < numTanks; tankNumber++) {
            List<coordinate> aTank = new ArrayList<>();
            int pStartCol = (int) (Math.random() * 10);
            int pStartRow = (int) (Math.random() * 10);
            // yes this while loop may be infinite with >15 tanks
            while (toRet.board[pStartRow][pStartCol] == TILE_TANK || !floodFillCheck(toRet.board, pStartRow, pStartCol)) {
                pStartCol = (int) (Math.random() * 10);
                pStartRow = (int) (Math.random() * 10);
            }
            Tank toAdd = new Tank(aTank);
            toRet.board[pStartRow][pStartCol] = (char) (TILE_TANK + tankNumber);
            aTank.add(new coordinate(pStartRow,pStartCol));
            int currRow = pStartRow;
            int currCol = pStartCol;
            int tetroidSize=0;
            while (tetroidSize < TETROID_SIZE) {
                int randomDirection = (int) (Math.random() * 4);
                if (randomDirection == UP) {
                    if ((currCol + 1) < BOARD_SIZE && toRet.board[currRow][currCol + 1] == TILE_BLANK) {

                        tetroidSize++;
                    }
                } else if (randomDirection == RIGHT) {
                    if ((currRow + 1) < BOARD_SIZE && toRet.board[currRow + 1][currCol] == TILE_BLANK) {
                        tetroidSize++;
                    }
                } else if (randomDirection == DOWN) {
                    if ((currCol - 1) > 0 && toRet.board[currRow][currCol - 1] == TILE_BLANK) {
                        tetroidSize++;
                    }
                } else if (randomDirection == LEFT) {
                    if ((currRow - 1) > 0 && toRet.board[currRow - 1][currCol] == TILE_BLANK) {
                        tetroidSize++;
                    }
                }
            }
            mytanks.add(toAdd);
        }
        return toRet;
    }

    private static void floodFill(char[][] board, int floodRow, int floodCol, Int numInFill){

        if(board[floodRow][floodCol]==TILE_FOG){
            numInFill.wrapperInt++;
            board[floodRow][floodCol]=TILE_HIT;
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
/**
 * Board maintains a state of the game
 */
public class Board {
    private static final short BOARD_SIZE=10;
    private static final int UPPER_TANK_THRESHOLD = 25;
    private static final int LOWER_TNK_THRESHOLD = 15;
    private static final int MIDDLE_TANK_THRESHOLD = 20;
    Tile[][] board;
    // sets all tiles to unknown
    public static Board makeDisplayBoard(){
        Board toRet= new Board();
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = Tile.UNKNOWN;
            }
        }
        return toRet;
    }
    public static Board makeSecretBoard(int numTanks){
        Board toRet= new Board();
        for(int row = 0; row < BOARD_SIZE; row ++){
            for(int column = 0; column < BOARD_SIZE; column ++){
                toRet.board[row][column] = Tile.BLANK;
            }
        }
        if(numTanks > UPPER_TANK_THRESHOLD){
            numTanks = 25;
        }
        if(numTanks < LOWER_TNK_THRESHOLD){

        }
        else if (numTanks < MIDDLE_TANK_THRESHOLD){

        }
        else{

        }
        return toRet;
    }
}

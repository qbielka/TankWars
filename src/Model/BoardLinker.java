package Model;


import java.util.List;

/**
 * will link a secret board and a displayable board together for game logic
 */
public class BoardLinker {
    private Board secretBoard;
    private Board displayBoard;
    private List<Tank> tankList;

    public BoardLinker(int numTanks) throws Exception{
        secretBoard=Board.makeSecretBoard(numTanks);
        displayBoard=Board.makeDisplayBoard();
        tankList=secretBoard.getTanks();
    }

    public List<Tank> getTankList(){
        return tankList;
    }
    // violates Command Query Separation
    public boolean isPlayerGuessHit(coordinate guess){
        if ( getCheatTile(guess)!= Tile.getTileBlank()){
            if(getDisplayBoardTile(guess)==Tile.getTileFog()) {
                displayBoard.makeTileHit(guess);
                return true;
            }
        }
        return false;
    }

    public char getDisplayBoardTile(coordinate point){
        return displayBoard.getTile(point);
    }

    public char getCheatTile(coordinate point){
        return secretBoard.getTile(point);
    }
}

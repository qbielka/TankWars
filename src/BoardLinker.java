/**
 * will link a secret board and a displayable board together for game logic
 */
public class BoardLinker {
    private Board secretBoard;
    private Board displayBoard;

    BoardLinker(int numTanks) throws Exception{
        secretBoard=Board.makeSecretBoard(numTanks);
        displayBoard=Board.makeDisplayBoard();
    }

    public void playerGuess(coordinate guess){

    }

    public char getDisplayBoardTile(coordinate point){
        return displayBoard.getTile(point);
    }

    public char getCheatTile(coordinate point){
        return secretBoard.getTile(point);
    }
}

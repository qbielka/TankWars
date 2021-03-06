package Model;

import java.util.List;

/**
 * BoardLinker class links a secret board and a displayable board together for game logic
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class BoardLinker{
    private Board secretBoard;
    private Board displayBoard;
    private List<Tank> tankList;

    public BoardLinker( int numTanks ){
        secretBoard = Board.makeSecretBoard( numTanks );
        displayBoard = Board.makeDisplayBoard();
        tankList = secretBoard.getTanks();
    }

    public List<Tank> getTankList(){
        return tankList;
    }

    public boolean fireAtPoint( Coordinate guess ){
        if( getCheatTile( guess ) != Tile.getTileBlank() ){
            if( getDisplayBoardTile( guess ) == Tile.getTileFog() ){
                displayBoard.makeTileHit( guess );
                secretBoard.makeTileLowerCase( guess );
                return true;
            }
        }
        displayBoard.makeTileBlank( guess );
        return false;
    }

    public char getDisplayBoardTile( Coordinate point ){
        return displayBoard.getTile( point );
    }

    public char getCheatTile( Coordinate point ){
        return secretBoard.getTile( point );
    }
}
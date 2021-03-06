package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class which maintains a state of game.
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Board{
    private static final short BOARD_SIZE = 10;
    private static final int UPPER_TANK_THRESHOLD = 25;
    private static final int TANK_SIZE = 4;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int NUM_TRIES = 2000;

    private char[][] board = new char[ BOARD_SIZE ][ BOARD_SIZE ];
    private List<Tank> tanks;

    private Board(){
    }

    public static Board makeDisplayBoard(){
        Board toRet = new Board();
        for( int row = 0; row < BOARD_SIZE; row++ ){
            for( int column = 0; column < BOARD_SIZE; column++ ){
                toRet.board[ row ][ column ] = Tile.getTileFog();
            }
        }
        return toRet;
    }

    public static Board makeSecretBoard( int numTanks ){
        Board toRet = fillBoard( Tile.getTileBlank() );
        tankNumBoundsCheck( numTanks );

        List<Tank> myTanks = new ArrayList<>();

        for( int tankNumber = 0; tankNumber < numTanks; tankNumber++ ){
            List<Coordinate> aTank = new ArrayList<>();

            Int pStartCol = new Int( randomCoordinate() );
            Int pStartRow = new Int( randomCoordinate() );

            findLegalSpot( toRet, pStartCol, pStartRow );

            Tank tankToAdd = new Tank( aTank );
            Int tankSize = new Int( 0 );

            makePointTank( toRet, tankNumber, aTank, pStartCol.wrapperInt, pStartRow.wrapperInt );
            tankSize.wrapperInt++;
            growTankFromSeed( toRet, tankNumber, aTank, tankSize );
            myTanks.add( tankToAdd );
        }
        toRet.tanks = myTanks;
        return toRet;
    }

    private static void findLegalSpot( Board toRet, Int pStartCol, Int pStartRow ){
        int numTries = 0;

        while( isLegalLocation( toRet, pStartCol.wrapperInt, pStartRow.wrapperInt ) ){
            pStartCol.wrapperInt = randomCoordinate();
            pStartRow.wrapperInt = randomCoordinate();
            numTries++;

            if( numTries == NUM_TRIES ){
                if( isNoPositions( toRet ) ){
                    throw new IllegalArgumentException( "Cannot place all tanks" );
                }
            }
        }
    }

    private static void growTankFromSeed( Board toRet, int tankNumber, List<Coordinate> aTank, Int tetroidSize ){
        Int currCol;
        Int currRow;

        while( tetroidSize.wrapperInt < TANK_SIZE ){
            Int randomDirection = new Int( ( int ) ( Math.random() * 4 ) );
            Int randomPiece = new Int( ( int ) ( Math.random() * aTank.size() ) );
            currCol = new Int( aTank.get( randomPiece.wrapperInt ).getColIndex() );
            currRow = new Int( aTank.get( randomPiece.wrapperInt ).getRowIndex() );

            if( randomDirection.wrapperInt == UP &&
                    ( ( currCol.wrapperInt + 1 ) < BOARD_SIZE && toRet.board[ currRow.wrapperInt ][ currCol.wrapperInt + 1 ] == Tile.getTileBlank() ) ){
                currCol.wrapperInt++;
                makePointTank( toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt );
                tetroidSize.wrapperInt++;

            } else if( randomDirection.wrapperInt == RIGHT &&
                    ( ( currRow.wrapperInt + 1 ) < BOARD_SIZE && toRet.board[ currRow.wrapperInt + 1 ][ currCol.wrapperInt ] == Tile.getTileBlank() ) ){
                currRow.wrapperInt++;
                makePointTank( toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt );
                tetroidSize.wrapperInt++;

            } else if( randomDirection.wrapperInt == DOWN &&
                    ( ( currCol.wrapperInt - 1 ) > 0 && toRet.board[ currRow.wrapperInt ][ currCol.wrapperInt - 1 ] == Tile.getTileBlank() ) ){
                currCol.wrapperInt--;
                makePointTank( toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt );
                tetroidSize.wrapperInt++;

            } else if( randomDirection.wrapperInt == LEFT &&
                    ( ( currRow.wrapperInt - 1 ) > 0 && toRet.board[ currRow.wrapperInt - 1 ][ currCol.wrapperInt ] == Tile.getTileBlank() ) ){
                currRow.wrapperInt--;
                makePointTank( toRet, tankNumber, aTank, currCol.wrapperInt, currRow.wrapperInt );
                tetroidSize.wrapperInt++;
            }
        }
    }

    private static boolean isLegalLocation( Board toRet, int pStartCol, int pStartRow ){
        return toRet.board[ pStartRow ][ pStartCol ] == Tile.getTileTank()
                || !floodFillCheck( toRet.board, pStartRow, pStartCol );
    }

    private static int randomCoordinate(){
        return ( int ) ( Math.random() * 10 );
    }

    private static void tankNumBoundsCheck( int numTanks ){
        if( numTanks > UPPER_TANK_THRESHOLD ){
            throw new IllegalArgumentException( "Too many Tanks" );
        } else if( numTanks <= 0 ){
            throw new IllegalArgumentException( "Too few Tanks" );
        }
    }

    private static Board fillBoard( char tile ){
        Board toRet = new Board();

        for( int row = 0; row < BOARD_SIZE; row++ ){
            for( int column = 0; column < BOARD_SIZE; column++ ){
                toRet.board[ row ][ column ] = tile;
            }
        }
        return toRet;
    }

    private static boolean isNoPositions( Board toRet ){
        boolean spaceAvailable = false;

        for( int row = 0; row < BOARD_SIZE; row++ ){
            for( int column = 0; column < BOARD_SIZE; column++ ){
                if( floodFillCheck( toRet.board, row, column ) ){
                    spaceAvailable = true;
                }
            }
        }
        return !spaceAvailable;
    }

    private static void makePointTank( Board toRet, final int tankNumber, List<Coordinate> aTank, final int col, final int row ){
        toRet.board[ row ][ col ] = ( char ) ( Tile.getTileTank() + tankNumber );
        aTank.add( new Coordinate( row, col ) );
    }

    // Helper for BoardLinker
    public char getTile( Coordinate point ){
        return board[ point.getRowIndex() ][ point.getColIndex() ];
    }

    public void makeTileHit( Coordinate point ){
        if( board[ point.getRowIndex() ][ point.getColIndex() ] == Tile.getTileFog() ){
            board[ point.getRowIndex() ][ point.getColIndex() ] = Tile.getTileHit();
        }
    }

    public void makeTileBlank( Coordinate point ){
        if( board[ point.getRowIndex() ][ point.getColIndex() ] == Tile.getTileFog() ){
            board[ point.getRowIndex() ][ point.getColIndex() ] = Tile.getTileBlank();
        }
    }

    public void makeTileLowerCase( Coordinate point ){
        board[ point.getRowIndex() ][ point.getColIndex() ]
                = ( ( board[ point.getRowIndex() ][ point.getColIndex() ] + "" ).toLowerCase() ).charAt( 0 );
    }

    public List<Tank> getTanks(){
        return tanks;
    }

    private static void floodFill( char[][] board, int floodRow, int floodCol, Int numInFill ){
        if( board[ floodRow ][ floodCol ] == Tile.getTileBlank() ){
            numInFill.wrapperInt++;
            board[ floodRow ][ floodCol ] = Tile.getTileHit();
        } else{
            return;
        }

        if( numInFill.wrapperInt >= TANK_SIZE ){
            return;
        }

        if( ( floodRow + 1 ) < BOARD_SIZE ){
            floodFill( board, floodRow + 1, floodCol, numInFill );
        }

        if( ( floodRow - 1 ) > 0 ){
            floodFill( board, floodRow - 1, floodCol, numInFill );
        }

        if( ( floodCol + 1 ) < BOARD_SIZE ){
            floodFill( board, floodRow, floodCol + 1, numInFill );
        }

        if( ( floodCol - 1 ) > 0 ){
            floodFill( board, floodRow, floodCol - 1, numInFill );
        }
    }

    private static boolean floodFillCheck( char[][] board, int floodRow, int floodCol ){
        char[][] thisBoard = new char[ BOARD_SIZE ][ BOARD_SIZE ];
        if( board[ floodRow ][ floodCol ] != Tile.getTileBlank() ){
            return false;
        }

        for( int row = 0; row < BOARD_SIZE; row++ ){
            for( int column = 0; column < BOARD_SIZE; column++ ){
                thisBoard[ row ][ column ] = board[ row ][ column ];
            }
        }
        Int sizeOfEmptySpace = new Int( 0 );
        floodFill( thisBoard, floodRow, floodCol, sizeOfEmptySpace );

        return sizeOfEmptySpace.wrapperInt >= TANK_SIZE;
    }
}

class Int{
    public int wrapperInt;

    Int( int item ){
        wrapperInt = item;
    }
}
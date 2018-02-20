package UI;

import Model.BoardLinker;
import Model.Coordinate;

/**
 * will display a given board to the UI
 */
public class BoardDisplay {

    private static final char COLUMN_TAG = 'a';
    private static final int BOARD_SIZE = 10;


    public static void displayBoard(BoardLinker boards){
        Coordinate printPoint = new Coordinate(0,0);
        printBoardHeader();
        for(int count = 0; count < BOARD_SIZE; count++){
            System.out.print((char)(COLUMN_TAG + count) + "  ");
            for (int tileCount = 0; tileCount < 10; tileCount++) {
                System.out.print(boards.getDisplayBoardTile(printPoint) + "  ");
                printPoint.increment();
            }
            System.out.println("");
        }
        System.out.println();
    }

    private static void printBoardHeader() {
        System.out.print("  ");
        for(int count = 0; count < BOARD_SIZE; count ++){
            if(count < BOARD_SIZE - 1){
                System.out.print(" ");
            }
            System.out.print((count + 1) + " ");
        }
        System.out.println();
    }

    public static void displaySecretBoard(BoardLinker boards){
        Coordinate printPoint = new Coordinate(0,0);
        printBoardHeader();
        for(int count = 0; count < BOARD_SIZE; count++){
            System.out.print((char)(COLUMN_TAG + count) + "  ");
            for (int tileCount = 0; tileCount < 10; tileCount++) {
                System.out.print(boards.getCheatTile(printPoint) + "  ");
                printPoint.increment();
            }
            System.out.println("");
        }
        System.out.println();
    }
}

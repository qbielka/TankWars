/**
 * will display a given board to the UI
 */
public class BoardDisplay {

    private static final char COLLUMN_TAG = 'a';
    private static final int BOARD_SIZE = 10;


    public static void displayBoard(BoardLinker boards){
        coordinate printPoint = new coordinate(0,0);
        printBoardHeader();
        for(int count = 0; count < BOARD_SIZE; count++){
            System.out.print((char)(COLLUMN_TAG +count)+" ");
            for (int tileCount = 0; tileCount < 10; tileCount++) {
                System.out.print(boards.getDisplayBoardTile(printPoint)+" ");
                printPoint.increment();
            }
            System.out.println("");
        }
    }

    private static void printBoardHeader() {
        System.out.print(" ");
        for(int count = 0; count < 10; count ++){
            System.out.print(" "+(count+1));
        }
        System.out.println(" ");
    }

    public static void displaySecretBoard(BoardLinker boards){
        coordinate printPoint = new coordinate(0,0);
        printBoardHeader();
        for(int count = 0; count < BOARD_SIZE; count++){
            System.out.print((char)(COLLUMN_TAG +count)+" ");
            for (int tileCount = 0; tileCount < 10; tileCount++) {
                System.out.print(boards.getCheatTile(printPoint)+" ");
                printPoint.increment();
            }
            System.out.println("");
        }
    }
}

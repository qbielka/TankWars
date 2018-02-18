package Model;

import UI.BoardDisplay;

import static java.lang.Integer.parseInt;

/**
 * Game runs and maintains turn order in a game
 */
public class Game {

    public static final int NO_TANKS = 0;
    public static final int TANKS = 1;
    public static final int DEFAULT_TANKS = 4;

    public static void main(String [] args)
    {
        int numArgs = args.length;
        int numTanks;
        boolean cheat = false;
        if(numArgs == NO_TANKS){
            numTanks = DEFAULT_TANKS;
        }else if (numArgs == TANKS){
            numTanks = parseInt(args[0]);
        }else {
            numTanks = parseInt(args[0]);
            cheat = true;
        }

        try {
            BoardLinker linked = new BoardLinker(numTanks);
            BoardDisplay.displaySecretBoard(linked);
            TankList tanks = new TankList(linked.getTankList());
            Fortress fort = Fortress.getInstance();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

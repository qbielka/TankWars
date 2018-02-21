import Model.BoardLinker;
import Model.Coordinate;
import Model.Fortress;
import Model.TankList;
import UI.BoardDisplay;
import UI.MasterDisplay;
import UI.TankDisplay;


import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Runs turn order in the game
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Game {
    private static final int NO_TANKS = 0;
    private static final int TANKS = 1;
    private static final int DEFAULT_TANKS = 4;

    public static void main(String [] args)
    {
        int numArgs = args.length;
        int numTanks;
        boolean cheat = false;

        if(numArgs == NO_TANKS){ // If no arg entered
            numTanks = DEFAULT_TANKS;
        }
        else if (numArgs == TANKS){ // If arg entered
            numTanks = parseInt(args[0]);
        }
        else { // Cheat code entered
            numTanks = parseInt(args[0]);
            cheat = true;
        }

        try {
            BoardLinker linked;
            if(cheat) {
                linked = cheat(numTanks);
            } else {
                linked = new BoardLinker(numTanks);
            }

            TankList tanks = new TankList(linked.getTankList());
            while (tanks.alive() && Fortress.getInstance().getFortressHP() != 0){
                turn(linked, tanks);
            }
            if(Fortress.getInstance().getFortressHP() == 0){
                MasterDisplay.loose(linked);
            }
            if(!tanks.alive()){
                MasterDisplay.win(linked);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static BoardLinker cheat(int numTanks) throws Exception {
        BoardLinker linked = new BoardLinker(numTanks);
        BoardDisplay.displaySecretBoard(linked);
        return linked;
    }

    private static void turn( BoardLinker linked, TankList tanks) throws Exception {
        Coordinate move = MasterDisplay.displayStateAndGetInput(linked);
        tanks.damageTank(move, linked);
        List<Integer> damages = tanks.dealDamage();
        MasterDisplay.displayPlayerTurn(damages);
    }
}

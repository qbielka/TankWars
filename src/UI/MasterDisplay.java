package UI;

import Model.Coordinate;
import Model.Fortress;
import Model.BoardLinker;
import Model.TankList;

import java.util.List;

import static UI.PlayerInput.getPlayerInput;

/**
 * is the master of the UI
 * controls other UI functions
 */
public class MasterDisplay {

    public static Coordinate displayStateAndGetInput(BoardLinker linked){

        MasterDisplay.displayFortressHealth();
        System.out.println();
        BoardDisplay.displayBoard(linked);
        System.out.println();
        return getPlayerInput();
    }
    public static void displayPlayerTurn(List<Integer> damages){
        System.out.println();
        TankDisplay.displayTankDamage(damages);
        System.out.println();
    }

    private static void displayFortressHealth(){
        System.out.println("Fortress Health: " + Fortress.getInstance().getFortressHP());
        System.out.println();
    }

    public static void win(BoardLinker linked){
        System.out.println("Congratulations! You won!");
        System.out.println();
        BoardDisplay.displaySecretBoard(linked);
    }
    public static void loose(BoardLinker linked){
        System.out.println("I'm sorry, your fortress has been smashed!");
        System.out.println();
        BoardDisplay.displaySecretBoard(linked);
    }
}

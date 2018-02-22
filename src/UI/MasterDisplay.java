package UI;

import Model.Coordinate;
import Model.Fortress;
import Model.BoardLinker;

import java.util.List;

import static UI.PlayerInput.getPlayerInput;

/**
 * MasterDisplay class controls other UI functions
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class MasterDisplay{
    public static Coordinate displayStateAndGetInput( BoardLinker linked ){
        MasterDisplay.displayFortressHealth();
        System.out.println();
        BoardDisplay.displayBoard( linked );
        System.out.println();

        return getPlayerInput();
    }

    public static void displayPlayerTurn( List<Integer> damages ){
        System.out.println();
        TankDisplay.displayTankDamage( damages );
        System.out.println();
    }

    private static void displayFortressHealth(){
        System.out.println( "Fortress Health: " + Fortress.getInstance().getFortressHP() );
    }

    public static void win( BoardLinker linked ){
        System.out.println( "Congratulations! You won!" );
        System.out.println();
        BoardDisplay.displaySecretBoard( linked );
    }

    public static void loose( BoardLinker linked ){
        System.out.println( "Your fortress has been destroyed!" );
        System.out.println();
        BoardDisplay.displaySecretBoard( linked );
    }
}
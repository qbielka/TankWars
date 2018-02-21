package UI;

import java.util.List;

/**
 * Displays damage done by a multitude of tanks to the Fortress
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class TankDisplay {
    public static void displayTankDamage(List<Integer> damages){
        for (int x = 0; x < damages.size(); x++) {
            if(!damages.get(x).equals(0)){
                // Alive tank #1 of 5 shot you for 20!
                System.out.println("Alive tank #" + (x + 1) +
                        " of " + damages.size() + " shot you for " + damages.get(x) + "!" );
            }else {
//                System.out.println("ERROR 0");
            }
        }
    }
}
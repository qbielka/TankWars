package UI;

import java.util.List;

/**
 * TankDisplay class displays damage to fortress done by each tank
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class TankDisplay{
    public static void displayTankDamage( List<Integer> damages ){
        for( int x = 0; x < damages.size(); x++ ){
            if( !damages.get( x ).equals( 0 ) ){
                System.out.println( "Alive tank #" + ( x + 1 ) +
                        " of " + damages.size() + " shot you for " + damages.get( x ) + "!" );
            }
        }
    }
}
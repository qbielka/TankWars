package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * TankList class manages the list of Tanks in the game, and allows for damage to be done to the fortress
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class TankList{
    private List<Tank> tankList;

    public TankList( List<Tank> tankList ){
        this.tankList = tankList;
    }

    public List<Integer> dealDamage(){
        Fortress fortress = Fortress.getInstance();
        List<Integer> listOfDamageByTank = new ArrayList<>();
        for( Tank aTank : tankList ){
            int damage = aTank.getDamage();

            fortress.damageFortress( damage );
            listOfDamageByTank.add( damage );
        }
        return listOfDamageByTank;
    }

    public boolean damageTank( Coordinate playerGuess, BoardLinker board ){
        if( board.fireAtPoint( playerGuess ) ){
            for( Tank aTank : tankList ){
                for( Coordinate coordinate : aTank.getSpaces() ){
                    if( playerGuess.equals( coordinate ) ){
                        aTank.decreaseHealth();
                        return true;
                    }
                }
            }
        } else{
            board.fireAtPoint( playerGuess );
        }
        return false;
    }

    public boolean alive(){
        for( Tank aTank : tankList ){
            if( aTank.getDamage() != 0 ){
                return true;
            }
        }
        return false;
    }
}
package Model;

import java.util.List;

/**
 * Tank class maintains a single Tank including HP and damage due
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Tank{
    private static final int MAX_HEALTH = 4;
    private static final int FOUR_HIT_DAMAGE = 20;
    private static final int THREE_HIT_DMG = 5;
    private static final int TWO_HIT_DMG = 2;
    private static final int ONE_HIT_DMG = 1;

    private List<Coordinate> index;
    private int health;

    Tank( List<Coordinate> index ){
        this.index = index;
        health = MAX_HEALTH;
    }

    public void decreaseHealth(){
        if( health != 0 ){
            health--;
        }
    }

    public List<Coordinate> getSpaces(){
        return index;
    }

    public int getDamage(){
        if( health == 0 ){
            return 0;
        } else if( health == 1 ){
            return ONE_HIT_DMG;
        } else if( health == 2 ){
            return TWO_HIT_DMG;
        } else if( health == 3 ){
            return THREE_HIT_DMG;
        } else if( health == 4 ){
            return FOUR_HIT_DAMAGE;
        }
        return 0;
    }
}
package Model;

import java.util.List;

/**
 * Maintains a single Tank including it hitpoints and damage
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Tank {
    public static final int MAX_HEALTH = 4;
    private static final int MAX_DMG = 20;
    private static final int THREE_HIT_DMG = 5;
    private static final int TWO_HIT_DMG = 2;
    private static final int ONE_HIT_DMG = 1;

    private List<Coordinate> index;
    private int health;

    Tank(List<Coordinate> index) {
       this.index = index;
       health = MAX_HEALTH;
    }

//    // Test
//    public void getIndex(){
//        for( int i = 0; i < 4; i++ ){
//            System.out.println( "Index " + i + ": " + ((char)('a' + index.get( i ).getRowIndex())) +", " + (index.get( i ).getColIndex()+1) );
//        }
//    }

    public void decreaseHealth(){
        if( health != 0 ){
            health--;
        }
    }

    public int getDamage(){
        if(health == 0){
            return 0;
        }
        else if(health == 1) {
            return ONE_HIT_DMG;
        }
        else if(health == 2) {
            return TWO_HIT_DMG;
        }
        else if(health == 3) {
            return THREE_HIT_DMG;
        }
        else if(health == 4) {
            return MAX_DMG;
        }
        return 0;
    }
}
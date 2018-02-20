package Model;

import java.util.List;

public class Tank {
    public static final int MAX_TANK_SIZE = 4;
    private static final int MAX_DMG = 20;
    private static final int THREE_HIT_DMG = 5;
    private static final int TWO_HIT_DMG = 2;
    private static final int ONE_HIT_DMG = 1;
    private List<Coordinate> index;
    private int health;

    Tank(List<Coordinate> index) {
       this.index=index;
       health = index.size();
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
        }else if(health == 4) {
            return MAX_DMG;
        }
        return 0;
    }
}

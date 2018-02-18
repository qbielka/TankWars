package Model;

import Model.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * Tanklist manages the list of Tanks in the game
 * It also allows for damage to be done to the fortress
 */
public class TankList {
    private List <Tank> tankList;
    TankList(List<Tank> tankList){
        this.tankList=tankList;
    }

    List<Integer> dealDamage(){
        Fortress fortress = Fortress.getInstance();
        List<Integer> listOfDamageByTank = new ArrayList<>();
        for (Tank aTank : tankList) {
            int damage = aTank.getDamage();
            fortress.damageFortress(damage);
            listOfDamageByTank.add(damage);
        }
        return listOfDamageByTank;
    }
}

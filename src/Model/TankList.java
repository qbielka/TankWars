package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tanklist manages the list of Tanks in the game
 * It also allows for damage to be done to the fortress
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class TankList {
    private List <Tank> tankList;

    public TankList(List<Tank> tankList){
        this.tankList = tankList;
    }

    public List<Integer> dealDamage(){
        Fortress fortress = Fortress.getInstance();
        List<Integer> listOfDamageByTank = new ArrayList<>();
        for (Tank aTank : tankList) {
            int damage = aTank.getDamage();
            fortress.damageFortress(damage);
            listOfDamageByTank.add(damage);
        }
        return listOfDamageByTank;
    }

    public int getSize(){
        return tankList.size();
    }

//    // Test
//    public void getTanks(){
//        for( int i = 0; i < tankList.size(); i++ ){
//            tankList.get( i ).getIndex();
//        }
//    }
}

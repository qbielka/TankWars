package Model;

/**
 * Fortress class maintains a fortress HP holding a single instance
 *
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class Fortress{
    private static Fortress thisFortress;
    private static final int MAX_HP = 1500;
    private int fortressHP;

    private Fortress(){
        fortressHP = MAX_HP;
    }

    public static Fortress getInstance(){
        if( thisFortress == null ){
            thisFortress = new Fortress();
        }
        return thisFortress;
    }

    public void damageFortress( int damage ){
        fortressHP = fortressHP - damage;

        if( fortressHP < 0 ){
            fortressHP = 0;
        }
    }

    public int getFortressHP(){
        return fortressHP;
    }
}
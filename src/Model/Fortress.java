package Model;

/**
 * maintains a fortress hp holding a single instance
 */
public class Fortress {
    private static Fortress thisFortress;
    private static final int MAX_FORTRESS_HP = 1500;
    private int fortressHP;

    private Fortress(){
        fortressHP = MAX_FORTRESS_HP;
    }

    public static Fortress getInstance(){
        if(thisFortress == null){
            thisFortress = new Fortress();
        }
        return thisFortress;
    }

    public void damageFortress(int damage){
        fortressHP = fortressHP - damage;
        if(fortressHP < 0){
            fortressHP = 0;
        }
    }

    public int getFortressHP(){
        return fortressHP;
    }
}

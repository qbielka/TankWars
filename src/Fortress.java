/**
 * maintains a fortress hp
 */
public class Fortress {
    private static final int MAX_FORTRESS_HP = 1500;
    private int fortressHP;

    public Fortress(){
        fortressHP = MAX_FORTRESS_HP;
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

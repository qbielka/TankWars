import java.util.List;

public class Tank {
    public static final int MAX_TANK_SIZE = 4;
    private List<coordinate> index;
    private int health;
    public Tank(List<coordinate> index) {
       this.index=index;
       health = index.size();
    }

    public int getDamage(){
        if(health==0){

        }
        // if statements for each health level
        return 0;
    }
}

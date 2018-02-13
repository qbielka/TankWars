/**
 * Game runs and maintains turn order in a game
 */
public class Game {

    public static void main(String [] args)
    {
        try {
            BoardLinker linked = new BoardLinker(1);
            BoardDisplay.displaySecretBoard(linked);
            TankList tanks = new TankList(linked.getTankList());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

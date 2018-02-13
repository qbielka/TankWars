public class Game {

    public static void main(String [] args)
    {
        try {
            BoardLinker linked = new BoardLinker(20);
            BoardDisplay.displaySecretBoard(linked);


        }catch (Exception e){
            System.out.println("oops");
        }
    }
}

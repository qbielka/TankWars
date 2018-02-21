package UI;

import Model.Coordinate;

import java.util.Scanner;

/**
 * Accepts player input and translates it to a coordinate for the Model Classes to use
 * @author Quince Bielka (qbielka), Emma Hughes (eha38)
 */

public class PlayerInput {


    private static final int STANDARD_LENGTH_RESPONSE = 2;
    private static final int BOARD_SIZE = 10;

    public static Coordinate getPlayerInput() {
        String playerMoveString = "";
        char[] playerMoveCharArray = playerMoveString.toCharArray();
        playerMoveString = getInput(playerMoveString, playerMoveCharArray);

        //return playerMoveString;
        return getPlayerCoordinate(playerMoveString);
    }

    private static String getInput(String playerMoveString, char[] playerMoveCharArray) {
        while ((playerMoveString.length() != STANDARD_LENGTH_RESPONSE && playerMoveString.length() != STANDARD_LENGTH_RESPONSE + 1) ||
                !isCharLetter(playerMoveCharArray[0]) || !isEndNum(playerMoveCharArray)) {
            System.out.println("Input your move in the form 'a2'");
            Scanner playerInput = new Scanner(System.in);
            playerMoveString = playerInput.nextLine();
            playerMoveCharArray = playerMoveString.toCharArray();
        }
        return playerMoveString;
    }


    private static boolean isEndNum( char [] playerMoveCharArray){
        if(playerMoveCharArray.length == STANDARD_LENGTH_RESPONSE){
            return isCharNum(playerMoveCharArray[1]);
        }else if (playerMoveCharArray.length == (STANDARD_LENGTH_RESPONSE+1)){
            return isTen(playerMoveCharArray);
        }
        return false;
    }

    private static boolean isTen(char[] playerMoveCharArray) {
        return playerMoveCharArray[1] == '1' && playerMoveCharArray[2] == '0';
    }

    private static boolean isCharNum(char number){
        return (number >= '0' && number < ('0' + BOARD_SIZE));
    }

    private static boolean isCharLetter(char letter) {
        return (letter >= 'a' && letter < ('a' + BOARD_SIZE)) || (letter >= 'A' && letter < ('A' + BOARD_SIZE));
    }

    private static Coordinate getPlayerCoordinate(String properInput) {
        char[] properInputCharArray = properInput.toCharArray();
        Coordinate playerInput;
        if(properInputCharArray.length == STANDARD_LENGTH_RESPONSE){
            char letter = properInputCharArray[0];
            if((letter >= 'a' && letter < ('a' + BOARD_SIZE))){
                playerInput = new Coordinate(properInputCharArray[0] - 'a', properInputCharArray[1] - '1');
            }else {
                playerInput = new Coordinate(properInputCharArray[0]-'A',properInputCharArray[1] - '1');
            }

        }else{
            char letter = properInputCharArray[0];
            if((letter >= 'a' && letter < ('a' + BOARD_SIZE))){
                playerInput = new Coordinate(properInputCharArray[0] - 'a', BOARD_SIZE - 1);
            }else {
                playerInput = new Coordinate(properInputCharArray[0]-'A',BOARD_SIZE - 1);
            }
        }
        return playerInput;
    }
}
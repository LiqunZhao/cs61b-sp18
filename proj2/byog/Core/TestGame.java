package byog.Core;


import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests helper methods defined in Game.java
 */
public class TestGame {

    @Test
    public void testInitialization() {
        Game game;

        game = new Game();
        String inputWithoutSeed = "NS";
        game.playWithInputString(inputWithoutSeed);

        game = new Game();
        String inputWithSeed = "N123S";
        game.playWithInputString(inputWithSeed);

        game = new Game();
        String inputWithMaxSeed = "N9223372036854775807S";
        game.playWithInputString(inputWithMaxSeed);

        game = new Game();
        String inputLowerCase = "n123s";
        game.playWithInputString(inputLowerCase);

        game = new Game();
        String inputInvalid1 = "S";
        try {
            game.playWithInputString(inputInvalid1);
        } catch (Game.InvalidInputException e) {
            System.out.println("Passed invalid input test 1");
        }

        game = new Game();
        String inputInvalid2 = "X";
        try {
            game.playWithInputString(inputInvalid2);
        } catch (Game.InvalidInputException e) {
            System.out.println("Passed invalid input test 2");
        }

        game = new Game();
        String inputInvalid3 = "N123XS";
        try {
            game.playWithInputString(inputInvalid3);
        } catch (Game.InvalidInputException e) {
            System.out.println("Passed invalid input test 3");
        }

    }

}

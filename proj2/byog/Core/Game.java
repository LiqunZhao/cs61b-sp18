package byog.Core;


import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;


public class Game {

    /// Static members
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;
    private static final int ENTRYX = 40;
    private static final int ENTRYY = 5;


    /// Instance members
    private boolean newWorldFlag = false; // flag to check whether new world should be generated
    private boolean quitFlag = false; // flag to check whether : is inputted before quit
    private String seedString = ""; // store input random seed numbers as String
    private TERenderer ter = new TERenderer();
    private TETile[][] world;


    /// Nested class

    /**
     * Class to throw exceptions for invalid inputs
     */
    class InvalidInputException extends RuntimeException {
        /**
         * Returns InvalidInputException object
         *
         * @param s Error string
         */
        InvalidInputException(String s) {
            super(s);
        }
    }


    /// Private methods
    /* Switches newWorldFlag */
    private void switchNewWorldFlag() {
        newWorldFlag = !newWorldFlag;
    }

    /* Switches quitFlag */
    private void switchQuitFlag() {
        quitFlag = !quitFlag;
    }

    /* Processes game recursively according to a given input Strings */
    private void processInput(String input) {

        if (input == null) {
            throw new InvalidInputException("No input given.");
        }

        String first = Character.toString(input.charAt(0));
        first = first.toLowerCase(); // normalize an input to lower case
        processInputString(first);

        if (input.length() > 1) {
            String rest = input.substring(1);
            processInput(rest); // recursive call until input ends
        }

    }

    /* Processes game according to a given single input String */
    private void processInputString(String first) {

        if (first.equals("n")) { // start and initializes new game
            switchNewWorldFlag();
        } else if (first.equals("s")) { // generate a randomized world
            generate();
//        } else if (first == "l") {
//            load();
//        } else if (first == ":") {
//            setQuitFlag();
//        } else if (first == "q") {
//            quit();
        } else {                // append next seed integer to seedString
            try {
                Long.parseLong(first);
                seedString += first;
            } catch (NumberFormatException e) { // throw error if invalid input given
                throw new InvalidInputException("Invalid input given: " + first);
            }
        }

    }

    /* Generates a randomized world */
    private void generate() {

        if (!newWorldFlag) {
            String error = "Input string " + "\"S\" given, but no game has been initialized.\n"
                    + "Please initialize game first by input string \"N\" and following random seed"
                    + "numbers";
            throw new InvalidInputException(error);
        }
        switchNewWorldFlag();

        WorldGenerator wg;
        if (seedString.equals("")) {
            wg = new WorldGenerator(WIDTH, HEIGHT, ENTRYX, ENTRYY);
        } else {
            long seed = Long.parseLong(seedString);
            wg = new WorldGenerator(WIDTH, HEIGHT, ENTRYX, ENTRYY, seed);
        }
        world = wg.generate();

    }

//    /* Loads a previously saved game. If no saved game found, returns null. */
//    private void load() {
//        return;
//    }
//
//    /* Quits game saving a current game */
//    private void quit() {
//        if (!quitFlag) {
//            String errorString = "";
//            throw new InvalidInputException(errorString);
//        }
//    }


    /// Public methods

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    void playWithKeyboard() {

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    TETile[][] playWithInputString(String input) {

        ter.initialize(WIDTH, HEIGHT);
        processInput(input);
        ter.renderFrame(world);
        return world;

    }


    /// Main method just to check this class works itself
    public static void main(String[] args) {

        Game game = new Game();
        String input = "N42S";
        game.playWithInputString(input);

    }

}

package byog.Core;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.awt.Font;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;


public class Game {

    /// Static members
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;
    private static final int ENTRYX = 40;
    private static final int ENTRYY = 5;
    private static final String NORTH = "w";
    private static final String EAST = "d";
    private static final String SOUTH = "s";
    private static final String WEST = "a";
    private static final String PATH = "saved.txt";
    private static final int WELCOMEWIDTH = 600;
    private static final int WELCOMEHEIGHT = 800;


    /// Instance members
    private boolean setupMode = true;     // flag to check whether setup has been done
    private boolean newGameMode = false; // flag to check whether a new game is gonna be generated
    private boolean quitMode = false; // flag to check whether a game is supposed to be quited
    private String seedString = ""; // store input random seed numbers as String
    private TERenderer ter = new TERenderer();
    private TETile[][] world;
    private int playerX;
    private int playerY;

    private void switchSetupMode() {
        setupMode = !setupMode;
    }

    private void switchNewGameMode() {
        newGameMode = !newGameMode;
    }

    private void switchQuitMode() {
        quitMode = !quitMode;
    }


    /// Private methods

    /* Processes game recursively according to a given input Strings */
    private void processInput(String input) {

        if (input == null) {
            System.out.println("No input given.");
            System.exit(0);
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

        if (setupMode) {      // when the setup hasn't been done
            switch (first) {
                case "n":       // new game gonna be generated
                    switchNewGameMode();
                    break;
                case "s":       // setup a new game
                    setupNewGame();
                    break;
                case "l":       // load the previously saved game
                    load();
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:        // append next seed integer to seedString
                    try {
                        Long.parseLong(first);
                        seedString += first;
                    } catch (NumberFormatException e) { // exit program if input is invalid
                        System.out.println("Invalid input given: " + first);
                        System.exit(0);
                    }
                    break;
            }
        } else {                // when the setup has been done
            switch (first) {
                // @Note: Add my keyboard preferences
                case NORTH:
                case "o":
                case EAST:
                case "l":
                case SOUTH:
                case "n":
                case WEST:
                case "k":
                    move(first);
                    break;
                case ":":
                    switchQuitMode();
                    break;
                case "q":
                    saveAndQuit();
                    System.exit(0);
                    break;
                default:
            }
        }

    }

    /* Generates a randomized world and put a player in it */
    private void setupNewGame() {
        // check validity of input
        if (!newGameMode) {
            String error = "Input string " + "\"S\" given, but no game has been initialized.\n"
                    + "Please initialize game first by input string \"N\" and following random seed"
                    + "numbers";
            System.out.println(error);
            System.exit(0);
        }
        switchNewGameMode();

        // setup a random seed and generate a world according to it
        WorldGenerator wg;
        if (seedString.equals("")) {
            wg = new WorldGenerator(WIDTH, HEIGHT, ENTRYX, ENTRYY);
        } else {
            long seed = Long.parseLong(seedString);
            wg = new WorldGenerator(WIDTH, HEIGHT, ENTRYX, ENTRYY, seed);
        }
        world = wg.generate();

        // setup a player
        world[ENTRYX][ENTRYY + 1] = Tileset.PLAYER;
        playerX = ENTRYX;
        playerY = ENTRYY + 1;

        // switch off setupMode
        switchSetupMode();
    }

    /* Loads a previously saved game. If no saved game found, returns null. */
    private void load() {
        File f = new File(PATH);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            world = (TETile[][]) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("No previously saved world found.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Class TETile[][] not found.");
            System.exit(1);
        }

        // switch off setupMode
        switchSetupMode();

        // rewrite playerX, playerY
        rewritePlayerLocation();
    }

    /* Helper method for load method: rewrite playerX, playerY */
    private void rewritePlayerLocation() {
        for (int w = 0; w < WIDTH; w += 1) {
            for (int h = 0; h < HEIGHT; h += 1) {
                if (world[w][h].equals(Tileset.PLAYER)) {
                    playerX = w;
                    playerY = h;
                }
            }
        }
    }

    /* Moves a player according to input string */
    /* @Note: Add my keyboard preferences */
    private void move(String input) {
        switch (input) {
            case NORTH:
            case "o":
                if (world[playerX][playerY + 1].equals(Tileset.FLOOR)) {
                    world[playerX][playerY + 1] = Tileset.PLAYER;
                    world[playerX][playerY] = Tileset.FLOOR;
                    playerY += 1;
                }
                return;
            case EAST:
            case "l":
                if (world[playerX + 1][playerY].equals(Tileset.FLOOR)) {
                    world[playerX + 1][playerY] = Tileset.PLAYER;
                    world[playerX][playerY] = Tileset.FLOOR;
                    playerX += 1;
                }
                return;
            case SOUTH:
            case "n":
                if (world[playerX][playerY - 1].equals(Tileset.FLOOR)) {
                    world[playerX][playerY - 1] = Tileset.PLAYER;
                    world[playerX][playerY] = Tileset.FLOOR;
                    playerY -= 1;
                }
                return;
            case WEST:
            case "k":
                if (world[playerX - 1][playerY].equals(Tileset.FLOOR)) {
                    world[playerX - 1][playerY] = Tileset.PLAYER;
                    world[playerX][playerY] = Tileset.FLOOR;
                    playerX -= 1;
                }
                return;
            default:
        }
    }

    /* Quits game saving a current game */
    private void saveAndQuit() {
        // ignore if quit flag : hasn't been inputted in advance
        if (!quitMode) {
            return;
        }
        switchQuitMode();

        File f = new File(PATH);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(world);
            oos.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /* Process keyboard inputs in setupMode */
    private void processWelcome() {
        // prepare welcome board window
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(WELCOMEWIDTH, WELCOMEHEIGHT);
        StdDraw.clear(StdDraw.BLACK);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                String typed = Character.toString(StdDraw.nextKeyTyped());
                processInput(typed);
            }

            renderWelcomeBoard();

            if (!setupMode) {   // break after setup has been done and enter game mode
                break;
            }
        }

        processGame();
    }

    /* Renders welcome board in setupMode */
    private void renderWelcomeBoard() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        // title
        StdDraw.setFont(new Font("Arial", Font.BOLD, 40));
        StdDraw.text(0.5, 0.8, "CS61B: BYoG");

        // menu
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 20));
        StdDraw.text(0.5, 0.5, "New Game: N");
        StdDraw.text(0.5, 0.475, "Load Game: L");
        StdDraw.text(0.5, 0.45, "Quit: Q");

        // seed
        if (newGameMode) {
            StdDraw.text(0.5, 0.25, "Seed: " + seedString);
            StdDraw.text(0.5, 0.225, "(Press S to start the game)");
        }

        StdDraw.show();
        StdDraw.pause(100);
    }

    /* Process keyboard inputs in game mode */
    private void processGame() {
        ter.initialize(WIDTH, HEIGHT);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                String typed = Character.toString(StdDraw.nextKeyTyped());
                processInput(typed);
            }

            renderGame();
        }
    }

    /* Renders the current state of the game */
    private void renderGame() {
        renderWorld();
        showTileOnHover();
        StdDraw.pause(10);
    }

    /* Renders world */
    private void renderWorld() {
        StdDraw.setFont();
        StdDraw.setPenColor();
        ter.renderFrame(world);
    }

    /* Draws text describing the Tile currently under the mouse pointer */
    private void showTileOnHover() {
        // turn the position of mouse pointer into xy-coordinate
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();
        TETile mouseTile = world[mouseX][mouseY];

        // draw as text
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 15));
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, HEIGHT - 1, mouseTile.description());
        StdDraw.show();
    }

    /// Public methods

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        processWelcome();
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
    public TETile[][] playWithInputString(String input) {
        processInput(input);
        return world;
    }


    /// Main method just to check this class works itself
    public static void main(String[] args) {
        Game game = new Game();
        game.playWithKeyboard();
    }

}

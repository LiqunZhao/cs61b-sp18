package byog.lab6;


import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.util.Random;


public class MemoryGame {
    private int round;
    private int width;
    private int height;
    private Random rand;
    private boolean gameOver = false;
    private boolean playerTurn;
    private static final Font SMALL = new Font("Arial", Font.PLAIN, 15);
    private static final Font BIG = new Font("Arial", Font.BOLD, 30);
    private static final Color NORMAL = StdDraw.MAGENTA;
    private static final Color GOOD = StdDraw.CYAN;
    private static final Color BAD = StdDraw.RED;
    private static final Color BACKGROUND = StdDraw.BLACK;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        MemoryGame game;
        if (args.length < 1) {
            game = new MemoryGame(40, 40);
        } else {
            int seed = Integer.parseInt(args[0]);
            game = new MemoryGame(40, 40, seed);
        }
        game.startGame();
    }

    private MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        this.rand = new Random();
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
//        StdDraw.setFont(BIG);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(BACKGROUND);
        StdDraw.enableDoubleBuffering();
    }

    private MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        this.rand = new Random(seed);
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
//        StdDraw.setFont(BIG);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(BACKGROUND);
        StdDraw.enableDoubleBuffering();
    }

    private String generateRandomString(int n) {
        String generated = "";
        while (n > 0) {
            char picked = CHARACTERS[rand.nextInt(CHARACTERS.length)];
            generated += Character.toString(picked);
            n -= 1;
        }
        return generated;
    }

    private void drawFrame(String s, Color penColor) {
        StdDraw.clear(BACKGROUND);

        // Draws helpful UI
        StdDraw.setFont(SMALL);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, height - 1, "Round: " + round);
        StdDraw.text((double) width / 2, height - 1, playerTurn ? "Type !" : "Watch !");
        StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);

        StdDraw.setFont(BIG);
        StdDraw.setPenColor(penColor);

        StdDraw.text((double) width / 2, (double) height / 2, s);

        StdDraw.show();
    }

    private void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i += 1) {
            char letter = letters.charAt(i);
            String s = Character.toString(letter);

            drawFrame(s, NORMAL);
            StdDraw.pause(1000);
            StdDraw.clear(BACKGROUND);
            StdDraw.pause(500);
        }
    }

    private String solicitNCharsInput(int n) {
        String typed = "";
        while (n > 0) {
            if (StdDraw.hasNextKeyTyped()) {
                char dequeued = StdDraw.nextKeyTyped();
                typed += Character.toString(dequeued);
                drawFrame(typed, NORMAL);
                n -= 1;
            }
        }
        return typed;
    }

    private void startGame() {
        round = 1;

        while (!gameOver) {
            drawFrame("Round: " + round, NORMAL);
            StdDraw.pause(1000);

            playerTurn = false;
            String expected = generateRandomString(round);
            flashSequence(expected);

            playerTurn = true;
            String actual = solicitNCharsInput(round);

            if (expected.equals(actual)) {
                round += 1;
                drawFrame("You passed round: " + round + "!", GOOD);
                StdDraw.pause(500);
            } else {
                gameOver = true;
            }
        }

        drawFrame("Game Over ! You made it to round: " + (round - 1), BAD);
    }

}

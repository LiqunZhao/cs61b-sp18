package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = 42;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Fills up a given world without anything ...
     */
    private static void fillNOTHING(TETile[][] world) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    // Helper methods for addHexagon
    private static int width(int nCall, int s) {
        return 2 * s + (s - 2) - 2 * nCall;
    }

    private static void recursiveFillLines(int nCall,
                                           int s,
                                           TETile[][] world,
                                           int xMiddleUpperLeft,
                                           int yMiddleUpperLeft,
                                           TETile tile) {
        if (nCall == s) {
            return;
        }

        int width = width(nCall, s);
        int xUpperLeft = xMiddleUpperLeft + nCall;
        int yUpperLeft1 = yMiddleUpperLeft + nCall;
        int yUpperLeft2 = yMiddleUpperLeft - nCall - 1;
        fillLine(width, world, xUpperLeft, yUpperLeft1, tile);
        fillLine(width, world, xUpperLeft, yUpperLeft2, tile);

        recursiveFillLines(nCall + 1, s, world, xMiddleUpperLeft, yMiddleUpperLeft, tile);
    }

    private static void fillLine(int width,
                                 TETile[][] world,
                                 int xUpperLeft,
                                 int yUpperLeft,
                                 TETile tile) {
        for (int i = 0; i < width; i += 1) {
            world[xUpperLeft + i][yUpperLeft] = tile;
        }
    }

    // Tests for helper methods above
    @Test
    public void testWidth() {
        assertEquals(4, width(0, 2));
        assertEquals(2, width(1, 2));
        assertEquals(13, width(0, 5));
        assertEquals(11, width(1, 5));
        assertEquals(9, width(2, 5));
        assertEquals(7, width(3, 5));
        assertEquals(5, width(4, 5));
    }

    /**
     * Adds a hexagon of side length s to a specified position in the world.
     *
     * @param s                Side length of the hexagon.
     * @param world            World to which the hexagon will be added
     * @param xMiddleUpperLeft X-coordinate of upper left of the hexagon's "middle"
     * @param yMiddleUpperLeft Y-coordinate of upper left of the hexagon's "middle"
     * @param tile             TETile used to constitute the hexagon
     */
    private static void addHexagon(int s,
                                   TETile[][] world,
                                   int xMiddleUpperLeft,
                                   int yMiddleUpperLeft,
                                   TETile tile) {
        recursiveFillLines(0, s, world, xMiddleUpperLeft, yMiddleUpperLeft, tile);
    }

    /**
     * Calls addHexagon with random tiles
     */
    private static void randomAddHexagon(int s,
                                         TETile[][] world,
                                         int xMiddleUpperLeft,
                                         int yMiddleUpperLeft) {
        int tileNum = RANDOM.nextInt(5);
        TETile tile;
        switch (tileNum) {
            case 0:
                tile = Tileset.WALL;
                break;
            case 1:
                tile = Tileset.GRASS;
                break;
            case 2:
                tile = Tileset.TREE;
                break;
            case 3:
                tile = Tileset.MOUNTAIN;
                break;
            case 4:
                tile = Tileset.PLAYER;
                break;
            default:
                tile = Tileset.NOTHING;
                break;
        }
        addHexagon(s, world, xMiddleUpperLeft, yMiddleUpperLeft, tile);
    }

    // Helper method for tessellate
    private static void expand(int nExpand,
                               TETile[][] world,
                               int xMiddleUpperLeft,
                               int yMiddleUpperLeft) {
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft + 6);
        randomAddHexagon(3, world, xMiddleUpperLeft + 5, yMiddleUpperLeft + 3);
        randomAddHexagon(3, world, xMiddleUpperLeft + 5, yMiddleUpperLeft - 3);
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft - 6);
        randomAddHexagon(3, world, xMiddleUpperLeft - 5, yMiddleUpperLeft - 3);
        randomAddHexagon(3, world, xMiddleUpperLeft - 5, yMiddleUpperLeft + 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft, yMiddleUpperLeft + 6);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft + 5, yMiddleUpperLeft + 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft + 5, yMiddleUpperLeft - 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft, yMiddleUpperLeft - 6);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft - 5, yMiddleUpperLeft - 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft - 5, yMiddleUpperLeft + 3);
    }

    private static void recursiveExpand(int nExpand,
                                        TETile[][] world,
                                        int xMiddleUpperLeft,
                                        int yMiddleUpperLeft) {
        if (nExpand == 0) {
            return;
        }
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft);
        expand(nExpand, world, xMiddleUpperLeft, yMiddleUpperLeft);
    }

    private static void tessellate(TETile[][] world) {
        recursiveExpand(2, world, 22, 25);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        fillNOTHING(hexWorld);
//        randomAddHexagon(2, hexWorld, 10, 10);
//        randomAddHexagon(4, hexWorld, 20, 20);
//        randomAddHexagon(6, hexWorld, 30, 30);
        tessellate(hexWorld);

        ter.renderFrame(hexWorld);
    }

}

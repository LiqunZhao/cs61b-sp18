package lab11.graphs;

import java.util.Random;

/**
 * @author Josh Hug, Shuhei KADOWAKI
 */
public class MazeCycles extends MazeExplorer {

    /* Inherits public fields:
    Maze maze;
    int[] distTo;
    int[] edgeTo;
    protected boolean[] marked;
    */

    private int[] cameFrom;
    private boolean foundCircle = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    private void dfs(int v) {
        for (int w : maze.adj(v)) {

            if (foundCircle) {
                return;
            }

            if (!marked[w]) {
                marked[w] = true;
                cameFrom[w] = v;
                dfs(w);
            } else if (w != cameFrom[v]) { // When `w` is not the parent of `v` (circle found)
                cameFrom[w] = v;

                int cur = v;    // Reconstruct circle
                edgeTo[cur] = cameFrom[cur];
                while (cur != w) {
                    cur = cameFrom[cur];
                    edgeTo[cur] = cameFrom[cur];
                }

                foundCircle = true;
                return;
            }
        }
    }

    @Override
    public void solve() {

        /* Serves like `edgeTo`, created because I don't want to use `edgeTo` until circle found */
        cameFrom = new int[maze.V()];

        /* Set point where circle search starts */
        Random rand = new Random();
        int startX = rand.nextInt(maze.N());
        int startY = rand.nextInt(maze.N());
        int s = maze.xyTo1D(startX, startY);
        marked[s] = true;
        cameFrom[s] = s;

        dfs(s);
        announce();             // Render the results of DFS
    }

}


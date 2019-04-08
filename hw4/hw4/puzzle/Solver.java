package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Solver {

    private class SearchNode {
        private WorldState state;
        private int moves;
        private Integer priority;
        private SearchNode prev;

        private SearchNode(WorldState state, SearchNode prev) {
            this.state = state;
            this.moves = prev == null ? 0 : prev.moves + 1;
            if (edCaches.containsKey(this.state)) {
                int ed = edCaches.get(this.state);
                this.priority = this.moves + ed;
            } else {
                int ed = this.state.estimatedDistanceToGoal();
                edCaches.put(this.state, ed);
                this.priority = this.moves + ed;
            }

            this.prev = prev;
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode left, SearchNode right) {
            return left.priority.compareTo(right.priority);
        }
    }

    private Map<WorldState, Integer> edCaches = new HashMap<>(); // Caches estimated distance
    private int searchedCnt = 0;        // Keep track of the number of `SearchNode`s are enqueued
    private Stack<WorldState> path = new Stack<>(); // Keep track of the searched path

    /**
     * Constructor which solves the puzzle, computing everything necessary for `moves` and
     * solution` not to have to solve the problem again.
     * Solves the puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        SearchNode currentNode = new SearchNode(initial, null);

        while (!currentNode.state.isGoal()) {
            for (WorldState nextState : currentNode.state.neighbors()) {
                if (currentNode.prev == null || !nextState.equals(currentNode.prev.state)) {
                    SearchNode nextNode = new SearchNode(nextState, currentNode);
                    pq.insert(nextNode);
                    searchedCnt += 1;
                }
            }
            currentNode = pq.delMin();
        }

        for (SearchNode node = currentNode; node != null; node = node.prev) {
            path.push(node.state);
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle
     * starting from the initial `WorldState`
     */
    public int moves() {
        return path.size() - 1;
    }

    /**
     * Returns a sequence of `WorldState`s from the initial `WorldState` to the solution.
     */
    public Iterable<WorldState> solution() {
        return path;
    }

    int searchedCount() {
        return searchedCnt;
    }

}

package hw2;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Set;
import java.util.HashSet;


/**
 * Models a percolation system.
 *
 * @note All method calls except the constructor should work in constant time.
 */
public class Percolation {

    private int N;
    private boolean[] openGridIn1D;
    private int nOpen = 0;
    private WeightedQuickUnionUF connectedGrids;
    private Set<Integer> topFullIDs = new HashSet<>();
    private Set<Integer> bottomFullIDs = new HashSet<>();
    private boolean percolated = false;

    /* Returns 1D-array index corresponding to the given grid */
    private int xyToIndexIn1D(int row, int col) {
        return N * row + col;
    }

    /**
     * Creates N-by-N grid, with all sites initially blocked
     *
     * @throws IllegalArgumentException if {@code N <= 0}
     */
    public Percolation(int N) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater than 0 but given N = " + N + "."
            );
        }
        this.N = N;
        openGridIn1D = new boolean[N * N];
        for (int i = 0; i < N * N; i += 1) {
            openGridIn1D[i] = false;
        }
        connectedGrids = new WeightedQuickUnionUF(N * N);
    }

    private boolean isInvalidIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return false;
    }

    /**
     * Opens the site at (row, col) if it is not open already
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public void open(int row, int col) throws IndexOutOfBoundsException {
        if (isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + "."
            );
        }
        int indexIn1D = xyToIndexIn1D(row, col);

        // Update the grid's condition if it's not already open
        if (openGridIn1D[indexIn1D]) {
            return;
        }
        openGridIn1D[indexIn1D] = true;
        nOpen += 1;
        int id = connectedGrids.find(indexIn1D);
        if (indexIn1D < N) {    // Add grid's Id to topFullIDs if it's in the top layer
            topFullIDs.add(id);
        }
        if (N * N - indexIn1D <= N) { // Add grid's Id to bottomFullIDs if it's in the bottom layer
            bottomFullIDs.add(id);
        }

        // Update connection and full conditions both from top and bottom with neighbors around
        updateConnection(row, col, row - 1, col); // On the north
        updateConnection(row, col, row, col + 1); // On the east
        updateConnection(row, col, row + 1, col); // On the south
        updateConnection(row, col, row, col - 1); // On the west

        // Update percolation condition
        int newID = connectedGrids.find(indexIn1D);
        if (topFullIDs.contains(newID) && bottomFullIDs.contains(newID)) {
            percolated = true;
        }
    }

    /**
     * Updates connection of a grid at (row, col) and (neighborRow, neighborCol),
     * and updates full conditions of them
     */
    private void updateConnection(int row, int col, int neighborRow, int neighborCol) {

        int indexIn1D = xyToIndexIn1D(row, col);
        if (!isInvalidIndex(neighborRow, neighborCol) && isOpen(neighborRow, neighborCol)) {

            // Union grid and neighbor's set
            int id = connectedGrids.find(indexIn1D);
            int neighborIndexIn1D = xyToIndexIn1D(neighborRow, neighborCol);
            int oldId = connectedGrids.find(neighborIndexIn1D);
            connectedGrids.union(indexIn1D, neighborIndexIn1D);
            int newId = connectedGrids.find(neighborIndexIn1D);

            // Update full from top conditions
            if (topFullIDs.contains(id)) {
                topFullIDs.remove(id);
                topFullIDs.add(newId);
            }
            if (topFullIDs.contains(oldId)) {
                topFullIDs.remove(oldId);
                topFullIDs.add(newId);
            }

            // Update full from bottom conditions
            if (bottomFullIDs.contains(id)) {
                bottomFullIDs.remove(id);
                bottomFullIDs.add(newId);
            }
            if (bottomFullIDs.contains(oldId)) {
                bottomFullIDs.remove(oldId);
                bottomFullIDs.add(newId);
            }

        }

    }

    /**
     * Checks if the site at (row, col) is open
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        if (isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + "."
            );
        }
        int indexIn1D = xyToIndexIn1D(row, col);
        return openGridIn1D[indexIn1D];
    }

    /**
     * Checks if the site at (row, col) is full
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        if (isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + "."
            );
        }
        int indexIn1D = xyToIndexIn1D(row, col);
        return topFullIDs.contains(connectedGrids.find(indexIn1D));
    }

    /**
     * Returns number of open sites
     */
    public int numberOfOpenSites() {
        return nOpen;
    }

    /**
     * Checks if the system percolates
     */
    public boolean percolates() {
        return percolated;
    }

    public static void main(String[] args) {
        // Unit tests are written in TestPercolation.java
    }

}

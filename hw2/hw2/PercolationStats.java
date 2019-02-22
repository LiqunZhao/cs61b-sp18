package hw2;


import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


/**
 * Estimates a percolation threshold
 */
public class PercolationStats {

    private int N;
    private int T;
    private double[] thresholds;
    private PercolationFactory pf;

    /**
     * Performs T independent experiments on an N-by-N grid.
     *
     * @throws IllegalArgumentException if {@code N <= 0} or {@code T <= 0}
     */
    public PercolationStats(int N, int T, PercolationFactory pf) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater than 0 but given N = " + N + "."
            );
        }
        if (T <= 0) {
            throw new IllegalArgumentException(
                    "T should be greater than 0 but given T = " + T + "."
            );
        }

        this.N = N;
        this.T = T;
        thresholds = new double[T];
        this.pf = pf;

        simulate();
    }

    /* Runs T simulations and record the results */
    private void simulate() {
        for (int t = 0; t < T; t += 1) {
            Percolation system = pf.make(N);
            while (!system.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!system.isOpen(row, col)) {
                    system.open(row, col);
                }
            }
            double threshold = (double) system.numberOfOpenSites() / (N * N);
            thresholds[t] = threshold;
        }
    }

    /**
     * Return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Returns sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Returns low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * Returns high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

}

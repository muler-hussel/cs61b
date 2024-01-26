package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int N;
    private int T;
    private double[] x;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        x = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            while (!per.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                per.open(row, col);
            }
            x[i] = (double) per.numberOfOpenSites() / (N * N);
        }

    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(x);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                                // high endpoint of 95% confidence interval
}

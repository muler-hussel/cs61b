package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF WQU;
    private WeightedQuickUnionUF BW;
    private int OpenNum;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.OpenNum = 0;
        WQU = new WeightedQuickUnionUF(N * N + 2);
        BW = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N][N];
    }               // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        int next[][] = new int[][]{
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        OpenNum++;
        for (int i= 0; i < 3; i++) {
            int mx = next[i][0];
            int my = next[i][1];
            if (mx < 0 || my >= N) {
                continue;
            }
            if (mx == -1) {
                BW.union(xyTo1D(row, col), N * N);
            } else if (mx == N) {
                WQU.union(xyTo1D(row, col), N * N + 1);
                continue;
            }
            if (isOpen(mx, my) && !BW.connected(xyTo1D(row, col), xyTo1D(mx, my))) {
                WQU.union(xyTo1D(row, col), xyTo1D(mx, my));
                BW.union(xyTo1D(row, col), xyTo1D(mx, my));
            }
        }
    }      // open the site (row, col) if it is not open already
    private int xyTo1D(int mx, int my) {
        return mx * N + my;
    }
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return BW.connected(xyTo1D(row, col), N * N);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return OpenNum;
    }          // number of open sites
    public boolean percolates() {
        return WQU.connected(N * N, N * N + 1);
    }             // does the system percolate?
    // use for unit testing (not required)
}

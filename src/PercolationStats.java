import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double experiments[];
    private int size;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        size = n;
        experiments = new double[trials];

        for (int i = 0; i < trials; i++) {
            experiments[i] = next();
        }
    }

    public double mean() {
        return StdStats.mean(experiments);
    }

    public double stddev() {
        return StdStats.stddev(experiments);
    }

//    public double confidenceLo() {
//    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println(stats.mean());
        StdOut.println(stats.stddev());
    }

    private double next() {
        int[] ids = new int[size * size];

        for (int i = 0; i < size * size; i++) {
            ids[i] = i;
        }

        Percolation percolation = new Percolation(size);

        StdRandom.shuffle(ids);

        int tries = 0;

        while (!percolation.percolates()) {
            int row = (int)Math.floor(ids[tries] * 1.0 / size) + 1;
            int col = ids[tries] % size + 1;

            percolation.open(row, col);
            tries++;
        }

        return tries * 1.0 / (size * size);
    }
}

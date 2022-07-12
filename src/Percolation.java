import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int[] id;
    private int[] sz;
    private boolean[] opened;
    private final int size;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        size = n;

        id = new int[n * n];
        sz = new int[n * n];
        opened = new boolean[n * n];

        for (int i = 0; i < n * n; i++) {
            id[i] = i;
            sz[i] = 1;
            opened[i] = false;
        }
    }

    public static void main(String[] args) {

    }

    public void open(int row, int col) {
        validateIndexes(row, col);

        int i = row - 1;
        int j = col - 1;

        int targetIndex = mapIndexesToSingleDimension(i, j);
        opened[targetIndex] = true;

        if (i >= 1 && opened[mapIndexesToSingleDimension(i - 1, j)]) {
            union(targetIndex, mapIndexesToSingleDimension(i - 1, j));
        }

        if (j >= 1 && opened[mapIndexesToSingleDimension(i, j - 1)]) {
            union(targetIndex, mapIndexesToSingleDimension(i, j - 1));
        }

        if (i < size - 1 && opened[mapIndexesToSingleDimension(i + 1, j)]) {
            union(targetIndex, mapIndexesToSingleDimension(i + 1, j));
        }

        if (j < size - 1 && opened[mapIndexesToSingleDimension(i, j + 1)]) {
            union(targetIndex, mapIndexesToSingleDimension(i, j + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndexes(row, col);

        return opened[mapIndexesToSingleDimension(row - 1, col - 1)];
    }

    public boolean isFull(int row, int col) {
        boolean reachTop = false;
        boolean reachBottom = false;

        for (int i = 0; i < size; i++) {
            if (id[i] == id[mapIndexesToSingleDimension(row - 1, col - 1)]) {
                reachTop = true;
            }

            if (id[mapIndexesToSingleDimension(row - 1, col - 1)] == id[mapIndexesToSingleDimension(size - 1, i)]) {
                reachBottom = true;
            }
        }

        return reachTop && reachBottom;
    }

    public boolean percolates() {
        for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               if (id[i] == id[mapIndexesToSingleDimension(size - 1, j)]) {
                   return true;
               }
           }
        }

        return false;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                    StdOut.print(String.valueOf(id[mapIndexesToSingleDimension(i, j)]) + ' ');
            }
            StdOut.println();
        }
    }

    public int numberOfOpenSites() {
        int openSites = 0;

        for (boolean b : opened) {
            if (b) {
                openSites++;
            }
        }

        return openSites;
    }

    private int mapIndexesToSingleDimension(int i, int j) {
        return i * size + j;
    }

    private int root(int index) {
        while (index != id[index]) {
            id[index] = id[id[index]];
            index = id[index];
        }

        return index;
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = id[j];
            sz[j] += sz[i];
        } else {
            id[j] = id[i];
            sz[i] += sz[j];
        }
    }

    private void validateIndexes(int row, int col) {
        if (row <= 0 || col <= 0 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
    }
}
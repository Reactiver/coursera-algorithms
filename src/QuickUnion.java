
public class QuickUnion {
    private final int[] id;
    private final int[] sz;
    private final int[] largest;

    private final int size;
    public QuickUnion(int N){
        size = N;
        id = new int[N];
        sz = new int[N];
        largest = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        int largestNumber = Math.max(p, q);

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            largest[j] = Math.max(largestNumber, largest[j]);
        } else {
            id[j] = i;
            sz[i] += sz[j];
            largest[i] = Math.max(largestNumber, largest[i]);;
        }
    }

    public int find(int i) {
        return largest[root(i)];
    }

    public boolean isFullyConnected() {
        return sz[0] == size;
    }
}

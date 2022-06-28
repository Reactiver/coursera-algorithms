import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF  {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnion uf = new QuickUnion(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            } else {
                StdOut.println("Already connected");
            }

            StdOut.println("Fully connected:" + uf.isFullyConnected());
        }
    }
}
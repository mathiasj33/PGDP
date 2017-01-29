package pgdp.blatt12;

public class Map {

    public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
        if (f == null || a == null || b == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        } else if (n <= 0 || n > a.length) {
            throw new IllegalArgumentException("The number of threads must be in [1, a.length]");
        } else if (b.length != a.length) {
            throw new IllegalArgumentException("Array a does not have the same length as array b");
        }

        Thread[] threads = new Thread[n];

        int numLarger = a.length % n;
        int largerSize = (int) (a.length / n) + 1;
        int smallerSize = (int) a.length / n;

        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            int size = i < numLarger ? largerSize : smallerSize;
            end = start + size - 1;
            Thread t = new Thread(new FillArray<>(f, a, b, start, end));
            t.start();
            threads[i] = t;
            start += size;
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) {
        try {
            String[] stringArray = new String[20];
            Map.map(new IntToString(), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, stringArray, 7);
            for (String s : stringArray) {
                System.out.println(s);
            }
        } catch (InterruptedException ex) {
        }
    }
}

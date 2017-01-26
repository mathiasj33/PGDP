package pgdp.blatt12;

public class Map {

    public static Thread[] threads;

    public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
        if (f == null || a == null || b == null || n < 0) {
            throw new IllegalArgumentException("Parameter must not be null");
        } else if (b.length != a.length) {
            throw new IllegalArgumentException("Array a does not have the same length as array b");
        }

        int numLargerArray = a.length % n;
        int numSmallerArray = (int) (a.length / n) - numLargerArray;

        threads = new Thread[numLargerArray + numSmallerArray];

        int start = 0;
        int end = 0;
        int index = 0;

        int largerSize = a.length / n;
        int smallerSize = a.length / n - 1;

        boolean inLarger = true;

        for (int i = 0; i < numLargerArray + numSmallerArray; i++) {
            if (i >= numLargerArray) {
                inLarger = false;
            }
            int currentSize = inLarger ? largerSize : smallerSize;
            end = start + currentSize;
            Thread t = new Thread(new FillArray<>(f, a, b, start, end));
            t.start();
            threads[index] = t;
            start += currentSize + 1;
            index++;
        }
    }

    public static void main(String[] args) {
        try {
            String[] stringArray = new String[10];
            Map.map(new IntToString(), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, stringArray, 3);
            for (Thread t : Map.threads) {
                t.join();
            }
            for (String s : stringArray) {
                System.out.println(s);
            }
        } catch (InterruptedException ex) {
        }
    }
}

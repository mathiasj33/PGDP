
package pgdp.blatt12;

public class Map {
    
    public static Thread[] threads;
    
    public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
        if(f == null || a == null || b == null || n < 0) throw new IllegalArgumentException("Parameter must not be null");
        else if(b.length != a.length) throw new IllegalArgumentException("Array a does not have the same length as array b");
        
        int numLargerArray = a.length % n;
        int numSmallerArray = (int) (a.length / n) - numLargerArray;
        
        threads = new Thread[numLargerArray + numSmallerArray];
        
        int start = 0;
        int end = 0;
        int index = 0;
        
        int largerSize = floor(a.length / n);
        for(int i = 0; i < numLargerArray; i++) {
            end = start + largerSize;
            Thread t = new Thread(new FillArray<>(a, b, f, start, end));
            t.start();
            threads[index] = t;
            start += largerSize + 1;
            index++;
        }
        int smallerSize = floor(a.length / n) - 1;
        for(int i = 0; i < numSmallerArray; i++) {
            end = start + smallerSize;
            Thread t = new Thread(new FillArray<>(a, b, f, start, end));
            t.start();
            threads[index] = t;
            start += smallerSize + 1;
            index++;
        }
    }
    
    private static int floor(float f) {
        return (int) f;
    }
    
    public static void main(String[] args) {
        try {
            String[] stringArray = new String[10];
            Map.map(new IntToString(), new Integer[]{1,2,3,4,5,6,7,8,9,10}, stringArray, 3);
            for(Thread t : Map.threads) t.join();
            for(String s : stringArray) System.out.println(s);
        } catch (InterruptedException ex) {
        }
    }
}

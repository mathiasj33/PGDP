
package pgdp.blatt12;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {
    public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
//        if(f == null || a == null || b == null || n < 0) throw new IllegalArgumentException("Parameter must not be null");
        int numThreads = floor(b.length / n);
        int numLargerArray = b.length % n;
        int numSmallerArray = numThreads - numLargerArray;
        System.out.println(numSmallerArray);
        System.out.println(numLargerArray);
    }
    
    private static int floor(float f) {
        return (int) f;
    }
    
    public static void main(String[] args) {
        try {
            Map.<String, Integer>map(null, null, new Integer[]{1,2,3,4,5,6,7,8,9,10}, 3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

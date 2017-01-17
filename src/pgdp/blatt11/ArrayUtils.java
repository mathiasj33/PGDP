
package pgdp.blatt11;

public class ArrayUtils {
    public static <T> void copy(T[] oldArray, T[] newArray) {
        for(int i = 0; i < oldArray.length; i++) {
            if(i >= newArray.length) return;
            newArray[i] = oldArray[i];
        }
    }
    
    public static <T> void shiftLeftFrom(T[] array, int index) {
        for(int i = index; i < array.length; i++) {
            T nextElement = i == array.length - 1 ? null : array[i+1];
            array[i] = nextElement;
        }
    }
}

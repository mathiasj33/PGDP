
package pgdp.blatt09.game;

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
    
    public static <T> T[] copyAndDelete(T[] oldArray, T[] newArray, T e) {
        int oldArrayIndex = 0;
        for(int i = 0; i < newArray.length; i++) {
            if(oldArray[oldArrayIndex].equals(e)) oldArrayIndex++;
            newArray[i] = oldArray[oldArrayIndex];
            oldArrayIndex++;
        }
        return newArray;
    } 
}

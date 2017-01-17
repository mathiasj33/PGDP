
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
        int index = indexOf(oldArray, e);
        shiftLeftFrom(oldArray, index);
        copy(oldArray, newArray);
        return newArray;
    }
    
    private static <T> int indexOf(T[] array, T e) {
        for(int i = 0; i < array.length; i++) {
            T t = array[i];
            if(t.equals(e)) return i;
        }
        return -1;
    }
}

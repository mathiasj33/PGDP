
package pgdp.blatt08;

public class Queue {
    private int first, last = -1;
    private int[] arr;
    
    public Queue() {
        arr = new int[2];
    }
    
    public boolean isEmpty() {
        return first == -1 && last == -1;
    }
    
    public void enqueue(int x) {
        if(isEmpty()) {
            first = last = 0;
            arr[0] = x;
            return;
        }
        if(isFull()) {
            increase();
            enqueue(x);
            return;
        }
        last++;
        if(last >= arr.length) {
            last = 0;
        }
        arr[last] = x;
    }
    
    private boolean isFull() {
        return last == first - 1 || last >= arr.length - 1 && first == 0;
    }
    
    private void increase() {
        int[] newArr = new int[arr.length * 2];
        for(int i = 0; i < arr.length; i++) {
            newArr[i] = dequeue();
            last = i;
        }
        arr = newArr;
        first = 0;
    }
    
    public int dequeue() {
        int value = arr[first];
        first++;
    }
    
    private void grow() {
        
    }
    
    public static void main(String[] args) {
        
    }
    
    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            if (first <= last && (i < first || i > last))
                out += " *";
            if (first <= last && i > first && i < last)
                out += " " + arr[i];
            if (first > last && i > last && i < first)
                out += " *";
            if (first > last && (i > first || i < last))
                out += " " + arr[i];
            if (i == first)
                out = out + " (" + arr[i];
            if (i == last)
                out += (first == last ? "" : " " + arr[i]) + ")";
        }
        return out;
    }
}

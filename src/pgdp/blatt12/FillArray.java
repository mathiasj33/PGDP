package pgdp.blatt12;

import java.util.Arrays;

public class FillArray<T, R> implements Runnable {

    private T[] input;
    private R[] output;
    private Fun<T, R> function;
    private int start, end;

    public FillArray(T[] input, R[] output, Fun<T, R> function, int start, int end) {
        this.input = input;
        this.output = output;
        this.function = function;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void run() {
        System.out.println("Compute from " + start + " to " + end);
        for(int i = start; i <= end; i++) {
            output[i] = function.apply(input[i]);
        }
    }
}

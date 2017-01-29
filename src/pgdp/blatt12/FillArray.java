package pgdp.blatt12;

public class FillArray<T, R> implements Runnable {

    private T[] input;
    private R[] output;
    private Fun<T, R> function;
    private int start, end;

    public FillArray(Fun<T, R> function, T[] input, R[] output, int start, int end) {
        this.input = input;
        this.output = output;
        this.function = function;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void run() {
        for(int i = start; i <= end; i++) {
            output[i] = function.apply(input[i]);
        }
    }
}

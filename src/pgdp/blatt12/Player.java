package pgdp.blatt12;

import java.util.Random;

public class Player implements Runnable {

    private int value;
    private boolean valueSet;
    private final Object valueLock = new Object();
    private final Random random = new Random();

    @Override
    public void run() {
        while (true) {
            synchronized (valueLock) {
                value = calculateValue();
                valueSet = true;
                valueLock.notify();
                try {
                    while(valueSet) valueLock.wait();
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    public int getChoice() throws InterruptedException {
        synchronized (valueLock) {
            while (!valueSet)
                valueLock.wait();
            valueSet = false;
            valueLock.notify();
            return value;
        }
    }

    private int calculateValue() {
        return random.nextInt(3);
    }
}

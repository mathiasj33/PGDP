package pgdp.blatt12;

import java.util.Random;

public class Player implements Runnable {

    private int value;
    private final Object valueLock = new Object();
    private final Random random = new Random();

    @Override
    public void run() {
        while (true) {
            value = calculateValue();
            synchronized (valueLock) {
                try {
                    valueLock.wait();
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    public int getChoice() throws InterruptedException {
        synchronized (valueLock) {
            valueLock.notify();
            return value;
        }
    }

    private int calculateValue() {
        return random.nextInt(3);
    }
}

package pgdp.blatt03;

import pgdp.global.Spielfeld;

public class Schlangenspiel extends Spielfeld {

    private SchlangenPlayer p1 = new SchlangenPlayer(1);
    private SchlangenPlayer p2 = new SchlangenPlayer(2);
    private SchlangenPlayer current = p1;

    public static void main(String[] args) {
        new Schlangenspiel().start();
    }

    public void start() {
        startGameLoop();
    }

    private void startGameLoop() {
        while (true) {
            paintField(p1.getField(), p2.getField());
            movePlayer();
            paintField(p1.getField(), p2.getField());
            if (current.getField() >= 35) {
                write(current + " hat gewonnen.");
                return;
            }
            while (specialField(current.getField())) {
                if (ladderField(current.getField())) {
                    write("Leiter!");
                    current.move(3);
                } else if (snakeField(current.getField())) {
                    write("Schlangen!");
                    current.move(-4);
                }
                paintField(p1.getField(), p2.getField());
            }
            current = current.equals(p1) ? p2 : p1;
        }
    }

    private void movePlayer() {
        int value = dice();
        write(current + " hat eine " + value + " gew�rfelt.");
        current.move(value);
    }

    private boolean specialField(int field) {
        return ladderField(field) || snakeField(field);
    }

    private boolean ladderField(int field) {
        return field % 5 == 0;
    }

    private boolean snakeField(int field) {
        return field % 7 == 0;
    }
}

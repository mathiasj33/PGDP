package pgdp.blatt09.game;

/**
 * Die Klasse Main enthaelt das Hauptprogramm.
 *
 * Im Hauptprogramm wird zuerst der Benutzer gefragt,
 * wer das Spiel beginnen soll.
 *
 * Dann wird das Spiel gestartet.
 *
 */
public class Main {

    public static void main(String args[]) {
        Game game = new Game();
        String input = "W";
        do {
           input = IO.readString("Soll M oder W beginnen? (M/W)\n");
        } while(!input.equals("M") && !input.equals("W"));
        game.startGame(input.equals("W"));
    }

}

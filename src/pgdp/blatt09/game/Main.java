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
        //TODO: fragen nach Anfangsspieler
        Game game = new Game();
        game.startGame(true);
    }

}

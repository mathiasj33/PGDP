package pgdp.blatt07;

import pgdp.global.MiniJava;

public class DameSpiel extends MiniJava {

	private int nrRows, nrColumns; // Board dimensions
	private boolean[][] board; // true = queen, false = empty
	private boolean whiteToMove; // Whose turn it is
	private String white, black; // Players' names

	/**
	 * Der Konstruktor registriert Spielernamen fuer Weiss und Schwarz.
	 *
	 * @param white
	 *            Name des als 'Weiss' bezeichneten Spielers
	 * @param black
	 *            Name des als 'Schwarz' bezeichneten Spielers
	 */
	public DameSpiel(String white, String black) {
		this.white = white;
		this.black = black;
	}

	/**
	 * Gibt das Spielbrett aus.
	 */
	private void printBoard() {
		for (int j = board[0].length - 1; j >= 0; j--) {
			System.out.print("\n " + (1 + j));
			for (int i = 0; i < board.length; i++) {
				System.out.print(board[i][j] ? " X" : " -");
			}
		}
		System.out.print("\n  ");
		for (int i = 1; i <= board.length; i++) {
			System.out.print(" " + i);
		}
		System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
	}

	/**
	 * Initialisiert das Spielbrett ueberall mit false. Dazu wird (ggf. neuer)
	 * Speicher allokiert.
	 */
	private void initBoard() {
		board = new boolean[nrColumns][nrRows];
	}

	/**
	 * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln. Das
	 * Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns
	 * abgelegt.
	 */
	private void determineBoardSize() {
		do {
			nrColumns = readInt("Weiß wählt die Breite (5 - 8):");
			if (nrColumns < 5 || nrColumns > 8)
				write("Die Zahl muss in {5, 6, 7, 8} sein.");
		} while (nrColumns < 5 || nrColumns > 8);

		String set = "{" + (nrColumns - 1) + ", " + nrColumns + ", " + (nrColumns + 1) + "}";
		do {
			nrRows = readInt("Schwarz wählt die Länge (Höhe) aus " + set + ":");
			if (nrRows < nrColumns - 1 || nrRows > nrColumns + 1)
				write("Die Zahl muss in " + set + " sein.");
		} while (nrRows < nrColumns - 1 || nrRows > nrColumns + 1);

	}

	/**
	 * Ermittelt, wer anfaengt zu ziehen. Das Ergebnis der Abfrage wird im
	 * Attribut whiteToMove abgelegt.
	 */
	private void determineFirstPlayer() {
		int input;
		do {
			input = readInt("Weiß entscheidet, ob weiß (0) oder schwarz (1) beginnt:");
			if (input != 0 && input != 1)
				write("Die Eingabe muss entweder 0 oder 1 sein.");
		} while (input != 0 && input != 1);

		whiteToMove = input == 0;
	}

	/**
	 * Fuehrt den Zug aus.
	 *
	 * @param move
	 *            der auszufuehrende Zug!
	 */
	private void applyMove(int move) {
		Position pos = intToPosition(move);
		board[pos.x][pos.y] = true;
	}

	/**
	 * Startet die Hauptschleife des Spiels mit der Abfrage nach Zuegen. Die
	 * Schleife wird durch Eingabe von -1 beendet.
	 */
	private void mainLoop() {
		while (freeFieldExists()) {
			Position pos = readFieldInput();
			if (pos == null)
				return;
			applyMove(positionToInt(pos)); // Um die vorgegebene Methode
											// applyMove zu benutzen muss die
											// Position umgewandelt werden
			whiteToMove = !whiteToMove;
			printBoard();
		}
	}

	private boolean freeFieldExists() {
		for (int i = 0; i < nrColumns; i++) {
			for (int j = 0; j < nrRows; j++) {
				if (isValid(new Position(i, j)))
					return true;
			}
		}
		return false;
	}

	private int positionToInt(Position pos) {
		return pos.x * 10 + pos.y;
	}

	private Position intToPosition(int field) {
		return new Position((field / 10), (field % 10));
	}

	private Position readFieldInput() {
		while (true) {
			int field = readInt("Geben Sie ein Feld ein.");
			if (field == -1)
				return null;
			Position pos = intToPosition(field);
			pos.x -= 1;
			pos.y -= 1;
			if (pos.y < 0 || pos.y > nrRows - 1 || pos.x < 0 || pos.x > nrColumns - 1) {
				write("Dieses Feld existiert nicht.");
				continue;
			} else if (!isValid(pos)) {
				write("Auf dieses Feld kann kein Stein gesetzt werden.");
				continue;
			}
			return pos;
		}
	}

	private boolean isValid(Position pos) {
		for (int i = 0; i < nrColumns; i++) {
			for (int j = 0; j < nrRows; j++) {
				if (!board[i][j])
					continue;
				if (i == pos.x || j == pos.y)
					return false;
				int xDiff = pos.x - i;
				int yDiff = pos.y - j;
				if (MathHelper.abs(xDiff) == MathHelper.abs(yDiff))
					return false;
			}
		}
		return true;
	}

	/**
	 * Informiert die Benutzerin ueber den Ausgang des Spiels. Speziell: Wer hat
	 * gewonnen (Weiss oder Schwarz)?
	 */
	private void reportWinner() {
		String winner = whiteToMove ? black : white;
		write(winner + " hat das Spiel gewonnen.");
	}

	/**
	 * Startet das Spiel.
	 */
	public void startGame() {
		determineBoardSize();
		initBoard();
		determineFirstPlayer();
		printBoard();
		mainLoop();
		reportWinner();
	}

	public static void main(String[] args) {
		DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
		ds.startGame();
	}

}

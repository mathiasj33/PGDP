package pgdp.blatt03;

import pgdp.global.MiniJava;

public class SuV extends MiniJava {

	private Player currentPlayer;
	private Player player1;
	private Player player2;

	public static void main(String[] args) {
		new SuV().startGame();
	}

	public void startGame() {
		player1 = new Player(1);
		player2 = new Player(2);
		currentPlayer = player1;

		if (player1.getSumOfValues() > 21 || player2.getSumOfValues() > 21) {
			endGame();
			return;
		}
		startGameLoop();
	}

	private void startGameLoop() {
		while (true) {
			int input = readInt(currentPlayer + " ist am Zug.\n\nBisherige Karten: " + currentPlayer.printCards()
					+ "\n\nWollen Sie noch eine Karte ziehen?");

			if (input != 0 && input != 1) {
				write("Die Eingabe muss entweder 1 oder 0 sein.");
				continue;
			}

			boolean continueDrawing = input == 1;

			if (continueDrawing) {
				currentPlayer.drawCard();

				if (currentPlayer.getSumOfValues() > 21) {
					write(currentPlayer + " hat Verloren.\n\nKarten: " + currentPlayer.printCards());
					return;
				}
			} else if (currentPlayer.equals(player1)){
				 currentPlayer = player2;
			} else {
				endGame();
				return;
			}
		}
	}

	private void endGame() {
		Player winner = determineWinner();
		Player loser = determineLoser();
		write(winner + " hat gewonnen.\n\nKarten von " + winner + ": " + winner.printCards() + "\nKarten von " + loser
				+ ": " + loser.printCards());
	}

	private Player determineWinner() {
		if (player1.getSumOfValues() >= player2.getSumOfValues()) {
			return player1;
		}
		return player2;
	}

	private Player determineLoser() {
		Player loser = determineWinner().equals(player1) ? player2 : player1;
		return loser;
	}
}

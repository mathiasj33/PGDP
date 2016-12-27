package pgdp.blatt02;

import java.util.Arrays;

import pgdp.global.MiniJava;

public class Meiern extends MiniJava {

	int player = 0;
	int lastScore;
	
	public static void main(String[] args) {
		new Meiern().startGame();
	}

	public void startGame() {
		while(true) {
			int[] values = rollDices();
			int score = getValue(values);
			
			String beginning = player == 0 ? "Sie haben " : "Der Computer hat ";
			String output = beginning + Arrays.toString(values) + " gew�rfelt.";
			
			String end = "\n\n";
			
			if(score > lastScore) {
				player = player == 0 ? 1 : 0;
				String middle = player == 0 ? "sind Sie" : "ist der Computer";
				end += "Jetzt " + middle + " am Zug.";
				
				lastScore = score;
				
				write(output + end);
			} else {
				String state = player == 0 ? "verloren." : "gewonnen!";
				end += "Sie haben " + state;
				write(output + end);
				return;
			}
		}
	}

	private int[] rollDices() {
		return new int[] { dice(), dice() };
	}

	private int getValue(int... values) {
		int value1 = values[0];
		int value2 = values[1];
		
		int biggerValue = value1 > value2 ? value1 : value2;
		int smallerValue = value1 > value2 ? value2 : value1;
		
		if(biggerValue == 2 && smallerValue == 1) {
			return Integer.MAX_VALUE;
		}
		else if(value1 == value2) {
			int score = 500;
			for(int i = 1; i < 7; i++) {
				if(value1 == i) return score;
				score += i;
			}
		}
		else {
			return biggerValue * biggerValue + smallerValue;
		}
		
		return 0;
	}
}

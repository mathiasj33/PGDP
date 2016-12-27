package pgdp.blatt02;

import pgdp.global.MiniJava;

public class RabbitsFirst extends MiniJava {
	public static void main(String[] args) {
		new RabbitsFirst().start();
	}
	
	public void start() {
		int month = readInt("Geben Sie den Monat ein: ");
		if(month <= 0) {
			write("Fehlerhafte Eingabe.");
			return;
		}
		write("Zu diesem Zeitpunkt gibt es " + calculateRabbitAmount(month) + " Kaninchen.");
	}
	
	private int calculateRabbitAmount(int month) {
		int firstGen = 1;
		int secondGen = 0, thirdGen = 0;
		for(int i = 0; i < month - 1; i++) {
			int newFirstGen = firstGen + secondGen + thirdGen;
			int newSecondGen = firstGen;
			int newThirdGen = secondGen;
			
			firstGen = newFirstGen;
			secondGen = newSecondGen;
			thirdGen = newThirdGen;
		}
		
		return firstGen + secondGen + thirdGen;
	}
}

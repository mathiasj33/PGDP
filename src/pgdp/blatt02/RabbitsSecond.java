package pgdp.blatt02;

import pgdp.global.MiniJava;

public class RabbitsSecond extends MiniJava {
	public static void main(String[] args) {
		new RabbitsSecond().start();
	}
	
	public void start() {
		int month = readInt("Geben Sie den Monat ein: ");
		if(month <= 0) {
			write("Fehlerhafte Eingabe.");
			return;
		}
		int[] population = calculateRabbitPopulation(month);
		write("Zu diesem Zeitpunkt gibt es\n\n" + printPopulation(population));
	}
	
	private int[] calculateRabbitPopulation(int month) {
		int firstGen = 1;
		int secondGen = 0, thirdGen = 0;
		for(int i = 0; i < month - 1; i++) {
			int newFirstGen = firstGen + 3*secondGen + thirdGen;
			int newSecondGen = firstGen;
			int newThirdGen = secondGen;
			
			firstGen = newFirstGen;
			secondGen = newSecondGen;
			thirdGen = newThirdGen;
		}
		
		return new int[]{firstGen,secondGen,thirdGen};
	}
	
	private String printPopulation(int[] population) {
		return population[0] + " Kaninchen der 1. Generation,\n" + population[1] + " Kaninchen der 2. Generation und\n"
				+ population[2] + " Kaninchen der 3. Generation.";
	}
}

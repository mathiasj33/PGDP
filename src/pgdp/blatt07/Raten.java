package pgdp.blatt07;

import java.util.Scanner; // Einlesen ueber die Konsole

import pgdp.global.MiniJava;

public class Raten extends MiniJava {

    private int nrPlayers;
    private int number;
    private int lastInput = 0;
    private int player = 0;

    public Raten(int nrPlayers){
        this.nrPlayers = nrPlayers;
    }

    public void startGame() {
    	number = generateNumber(10, 100);
    	
        while(true) {
        	//lastInput = readInput(player);
        	if(lastInput > number) {
        		//...
        	}
        }
    }


    public static int readIntRange(String msg, int lower, int upper){
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner (System.in)).nextInt();
        } while (result < lower || result > upper);
        return result;
    }

    public static int generateNumber(int lower, int upper){
        return lower + (new java.util.Random()).nextInt(upper-lower+1);
    }


    public static void main(String[] args) {
        Raten guess = new Raten(3);
        guess.startGame();
    }

}

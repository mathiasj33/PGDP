package pgdp.blatt02;

import pgdp.global.MiniJava;

public class LustigeSieben extends MiniJava {

	int money = 100;

	public static void main(String[] args) {
		new LustigeSieben().startGame();
	}

	public void startGame() {
		while (true) {
			if (money == 0) {
				write("Endstand: " + money);
				return;
			}
			
			int setMoney = getMoney();
			if (setMoney == 0) {
				write("Endstand: " + money);
				return;
			}
			if (setMoney > money) {
				write("Sie haben nicht genug Geld.");
				continue;
			}
			int field = getChosenField();
			if(field < 2 || field > 12) {
				write("Dieses Feld existiert nicht.");
				continue;
			}
			money -= setMoney;
			int value = dice() + dice();
			System.out.println(value);
			if (value == 7 && field == 7) {
				money += 3 * setMoney;
				write("x3!!!\nNeues Guthaben: " + money);
			} else if (value == field) {
				money += 2 * setMoney;
				write("x2!\nNeues Guthaben: " + money);
			} else if (field != 7 && leftSide(value) == leftSide(field)) {
				money += setMoney;
				write("Sie bekommen ihr gesetztes Geld zur�ck.\nNeues Guthaben: " + money);
			} else {
				write("Sie haben Ihr Geld verloren.\nNeues Guthaben: " + money);
			}
		}
	}

	private int getMoney() {
		return readInt("Wie viel Geld wollen Sie setzen?");
	}

	private int getChosenField() {
		return readInt("W�hlen Sie ein Feld: ");
	}

	private boolean leftSide(int value) {
		return value >= 2 && value <= 6;
	}
}

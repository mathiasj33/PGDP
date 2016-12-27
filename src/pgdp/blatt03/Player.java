package pgdp.blatt03;

import java.util.ArrayList;
import java.util.List;

import pgdp.global.MiniJava;

public class Player {
	private int id;
	private List<Integer> cardValues = new ArrayList<>();
	
	public Player(int id) {
		this.id = id;
		initHand();
	}
	
	private void initHand() {
		drawCard();
		drawCard();
	}

	public void drawCard() {
		cardValues.add(MiniJava.drawCard());
	}
	
	public int getSumOfValues() {
		int sum = 0;
		for(int v : cardValues) sum += v;
		return sum;
	}
	
	public String printCards() {
		String s = "";
		for(int v : cardValues) s += v + ",";
		if(s.length() > 1) s = s.substring(0, s.length() - 1);
		return s;
	}
	
	@Override
	public String toString() {
		return "Spieler " + id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

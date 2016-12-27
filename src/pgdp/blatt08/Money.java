package pgdp.blatt08;

public class Money {
	private int cent;
	
	public Money(){}
	
	public Money(int cent) {
		this.cent = cent;
	}
	
	public int getCent() {
		return cent;
	}
	
	public Money addMoney(Money m) {
		return new Money(cent + m.cent);
	}
	
	public Money negativeValue() {
		return new Money(-cent);
	}
	
	@Override
	public String toString() {
		int euro = cent / 100;
		int rest = cent % 100;
		return euro + "," + rest + " Euro";
	}
}

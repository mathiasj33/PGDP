package pgdp.blatt03;

public class SchlangenPlayer {
	private int id;
	private int field;
	
	public SchlangenPlayer(int id) {
		this.id = id;
	}
	
	public void move(int offset) {
		field += offset;
	}
	
	public int getField() {
		return field;
	}
	
	@Override
	public String toString() {
		return "Spieler " + id;
	}
}

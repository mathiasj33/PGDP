package pgdp.blatt06;

public class Aufgabe6dot2 {
	
	public static void main(String[] args) {
		new Aufgabe6dot2().start();
	}
	
	public void start() {
		print(5);
	}
	
	private void print(int n) {
		print(1, n);
	}
	
	private void print(int i, int n) {
		System.out.println("davor");
		System.out.println(i);
		if(i == n) return;
		print(i+1, n);
		System.out.println("danach");
	}
	
}

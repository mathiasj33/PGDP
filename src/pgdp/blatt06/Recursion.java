package pgdp.blatt06;

public class Recursion {
	
	public static void main(String[] args) {
		new Recursion().printRoot();
	}
	
	public void printPi() {
		System.out.println(calculatePi(10000));
	}
	
	private float calculatePi(int n) {
		if(n == 0) return 4;
		float numerator = n % 2 == 0 ? 4f : -4f;
		return  (numerator / (2*n + 1)) + calculatePi(n - 1);
	}
	
	public void printRoot() {
		System.out.println(calculateRoot(7, 5, .01f));
	}
	
	private double calculateRoot(int a, double n, double eta) {
		double newN = 0.5 * (n + a/n);
		if(abs(newN * newN, a) < eta) return newN;
		return calculateRoot(a, newN, eta);
	}
	
	private double abs(double a, double b) {
		return a > b ? a - b : b - a;
	}
}

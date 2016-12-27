package pgdp.blatt07;

public class Fak {
	public static void main(String[] args) {
		System.out.println(facRec(5));
		System.out.println(facTailRec(5));
		System.out.println(facIt(5, 1));
		System.out.println(facNormal(5));
	}
	
	public static int facRec(int n) {
		if(n == 0) return 1;
		return n * facRec(n-1);
	}
	
	public static int facTailRec(int n) {
		return facTailRecHelper(n, 1);
	}
	
	private static int facTailRecHelper(int n, int k) {
		if(n == 0) return k;
		return facTailRecHelper(n - 1, k * n);
	}
	
	public static int facIt(int n, int k) {
		while(true) {
			if(n == 0) return k;
			k *= n;
			n -= 1;
		}
	}
	
	public static int facNormal(int n) {
		int result = n;
		for(int i = n-1; i >= 1; i--) {
			result *= i;
		}
		return result;
	}
}

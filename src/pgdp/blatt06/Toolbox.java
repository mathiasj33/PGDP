package pgdp.blatt06;

public class Toolbox {

	public static int evenSum(int n) {
		if (n > 0)
			return evenSumPositive(n);
		else
			return -evenSumPositive(-n);
	}

	private static int evenSumPositive(int n) {
		if (n == 2)
			return 2;
		if (isEven(n) && n > 2) {
			return n + evenSum(n - 2);
		}
		return evenSum(n - 1);
	}
	
	private static boolean isEven(int n) {
		if(n == 1) return false;
		else if(n == 2) return true;
		return isEven(n-2);
	}

	public static int multiplication(int x, int y) {
		if (y > 0)
			return multiplicationPositive(x, y);
		else
			return -multiplicationPositive(x, -y);
	}

	private static int multiplicationPositive(int x, int y) {
		if (y == 0)
			return 0;
		return x + multiplicationPositive(x, y - 1);
	}

	public static void reverse(int[] m) {
		reverse(m, 0, m.length - 1);
	}

	private static void reverse(int[] m, int start, int end) {
		if (start >= end)
			return;
		int tmp = m[start];
		m[start] = m[end];
		m[end] = tmp;
		reverse(m, start + 1, end - 1);
	}
	
	public static int numberOfOddIntegers(int[] m) {
		return numberOfOddIntegers(m, m.length - 1); 
	}
	
	private static int numberOfOddIntegers(int[] m, int index) {
		if(index == 0) return isOdd(m[index]) ? 1 : 0;
		return (isOdd(m[index]) ? 1 : 0) + numberOfOddIntegers(m, index - 1);
	}
	
	private static boolean isOdd(int i) {
		return i % 2 == 1 || i % 2 == -1;
	}
	
	public static int[] filterOdd(int[] m) {
		int[] result = new int[numberOfOddIntegers(m)];
		filterOdd(result, m, 0, 0);
		return result;
	}
	
	private static void filterOdd(int[] result, int[] m, int index, int resultIndex) {
		if(index >= m.length) return;
		if(isOdd(m[index])) {
			result[resultIndex] = m[index];
			resultIndex++;
		}
		index++;
		filterOdd(result, m, index, resultIndex);
	}
}

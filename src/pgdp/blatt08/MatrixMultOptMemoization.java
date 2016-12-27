package pgdp.blatt08;

import java.util.HashMap;
import java.util.Map;

public class MatrixMultOptMemoization {
	
	private static Map<Vector2, Integer> memoMap = new HashMap<>();
	
	public static int f(int[][] mm) {
		return f(mm, 0, mm.length - 1);
	}

	public static int f(int[][] mm, int i, int j) {
		if (i == j)
			return 0;
		else if (i < j) {
			Vector2 vector = new Vector2(i,j);
			if(memoMap.containsKey(vector)) {
				return memoMap.get(vector);
			}
			System.out.println("calculate for " + i + " and " + j);
			int min = Integer.MAX_VALUE;
			for (int x = i; x < j; x++) {
				int result = f(mm, i, x) + f(mm, x + 1, j) + mm[i][0] * mm[j][1] * mm[x][1];
				if (result < min)
					min = result;
			}
			memoMap.put(vector, min);
			return min;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[][] arr = new int[][]{{10, 30}, {30, 5}, {5, 60}, {60, 4}};
		System.out.println(f(arr));
	}
}

package pgdp.blatt05;

import java.util.Arrays;

public class Matrix {
	public static void main(String[] args) {
		matmatmul(new int[][]{{6,-2},{7,3},{4,5}}, new int[][] { { 2,6,8,-2 }, { 7,-1,9,-4 } });
	}

	public static int vecvecmul(int[] a, int[] b) {
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		return result;
	}

	public static int[] matvecmul(int[][] a, int[] b) {
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				result[i] += a[i][j] * b[j];
			}
		}
		System.out.println(Arrays.toString(result));
		return result;
	}

	public static int[][] transpose(int[][] a) {
		int[][] transpose = new int[a[0].length][a.length];
		for (int i = 0; i < a[0].length; i++) {
			for (int j = 0; j < a.length; j++) {
				transpose[i][j] = a[j][i];
			}
		}
		for (int i = 0; i < transpose.length; i++) {
			System.out.println(Arrays.toString(transpose[i]));
		}
		return transpose;
	}

	public static int[][] matmatmul(int[][] a, int[][] b) {
		int[][] result = new int[a.length][b[0].length];
		for (int h = 0; h < a.length; h++) {
			for (int i = 0; i < b[0].length; i++) {
				int[] bVector = new int[b.length];
				for (int j = 0; j < b.length; j++) {
					bVector[j] = b[j][i];
				}
				int[] aVector = a[h];
				result[h][i] = vecvecmul(aVector, bVector);
			}
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
		return result;
	}
}

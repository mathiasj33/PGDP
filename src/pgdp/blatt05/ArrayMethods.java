package pgdp.blatt05;

import java.util.Arrays;

public class ArrayMethods {
	public static void main(String[] args) {
		linearisieren(new int[][]{{1,3},{25},{7,4,6,9}});
	}
	
	private static void minUndMax(int[] feld) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i : feld) {
			if(i > max) max = i;
			else if (i < min) min = i;
		}
		System.out.println("Max " + max + "; Min " + min);
	}
	
	private static void invert(int[] feld) {
		int[] invertedField = new int[feld.length];
		for(int i = 0; i < feld.length;  i++) {
			invertedField[feld.length-1-i] = feld[i];
		}
		System.out.println(Arrays.toString(invertedField));
	}
	
	private static void linearisieren(int[][] array) {
		int length = 0;
		for(int[] subArray : array) {
			length += subArray.length;
		}
		int count = 0;
		int[] linearized = new int[length];
		for(int i = 0; i < array.length; i++) {
			int[] subArray = array[i];
			for(int j = 0; j < subArray.length; j++) {
				linearized[count] = subArray[j];
				count++;
			}
		}
		System.out.println(Arrays.toString(linearized));
	}
}

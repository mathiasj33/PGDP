package pgdp.blatt05;

import java.util.function.Predicate;

public class SimpleIntList {
	private int[] array;
	private int index = 0;
	
	public SimpleIntList() {
		array = new int[0];
	}
	
	public void add(int e) {
		int[] copy = new int[array.length + 1];
		copyTo(copy, array);
		array = copy;
		array[index] = e;
		index++;
	}
	
	public int get(int index) {
		return array[index];
	}
	
	public int size() {
		return array.length;
	}
	
	public SimpleIntList getAll(Predicate<Integer> p) {
		SimpleIntList newList = new SimpleIntList();
		for(int i : array) {
			if(p.test(i)) {
				newList.add(i);
			}
		}
		return newList;
	}
	
	public boolean contains(int e) {
		return getAll(i -> i == e).size() > 0;
	}
	
	private void copyTo(int[] copy, int[] array) {
		for(int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}
	}
}

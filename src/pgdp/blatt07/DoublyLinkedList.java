package pgdp.blatt07;

public class DoublyLinkedList {

	Entry head;
	int size;

	/**
	 * constructor empty DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return number of elements in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Appends a new element with value info to the end of this list
	 * 
	 * @param info
	 *            value of the new element
	 */
	public void add(int info) {
		if (head == null) {
			head = new Entry(null, null, info);
			size++;
			return;
		}
		add(info, head);
		size++;
	}

	private void add(int info, Entry currElem) {
		if (isLastElement(currElem)) {
			currElem.next = new Entry(currElem, null, info);
			return;
		}
		add(info, currElem.next);
	}

	private boolean isLastElement(Entry elem) {
		return elem.next == null;
	}

	/**
	 * Inserts a new element with value info at the specified position in this
	 * list.
	 * 
	 * @param index
	 *            position where the element is inserted, from 0 ...
	 *            list.size()-1
	 * @param info
	 *            value of the new element
	 */
	public void add(int index, int info) {
		Entry currElem = head;
		for (int i = 0; i < index; i++) {
			if(currElem.next == null) {
				Entry newEntry = new Entry(currElem, null, info);
				currElem.next = newEntry;
				return;
			}
			currElem = currElem.next;
		}
		Entry prev = currElem.prev;
		Entry newEntry = new Entry(prev, currElem, info);
		currElem.prev = newEntry;
		if(prev == null) {
			head = newEntry;
			return;
		}
		prev.next = newEntry;
		size++;
	}

	/**
	 * Removes and returns the element at position index from this list.
	 * 
	 * @param index
	 *            position of the element that is removed
	 * @return value of the removed element
	 */
	public int remove(int index) {
		if(index == 0) {
			int value = head.elem;
			head = head.next;
			head.prev = null;
			return value;
		}
		Entry currEntry = head;
		for(int i = 0; i < index; i++) {
			currEntry = currEntry.next;
		}
		if(index < 0 || index > size) {
			return Integer.MIN_VALUE;
		}
		int value = currEntry.elem;
		
		currEntry.prev.next = currEntry.next;
		if(currEntry.next != null)
		currEntry.next.prev = currEntry.prev;
		return value;
	}

	/**
	 * shifts the list the specified number of positions to the left example:
	 * [1,5,6,7] ---shift(2)---> [6,7,1,5]
	 * 
	 * @param index
	 *            number of position to shift, from 0 to size-1
	 */
	public void shiftLeft(int index) {
		
		if(index >= size() || index <= 0) return;
		
		if(index == 1) {
			Entry second = head.next;
			Entry last = head;
			for(int i = 0; i < size() - 1; i++) {
				last = last.next;
			}
			
			Entry prevHead = head;
			second.prev = null;
			head = second;
			
			prevHead.prev = last;
			last.next = prevHead;
			prevHead.next = null;
			return;
		}
		for(int i = 0; i < index; i++) {
			shiftLeft(1);
		}
	}

	@Override
	public String toString() {
		String out = "[";
		if (head != null) {
			out += head.elem;
			Entry tmp = head.next;
			while (tmp != null) {
				out = out + "," + tmp.elem;
				tmp = tmp.next;
			}
		}
		out += "]";
		return out;
	}

	public static void main(String[] args) {
		DoublyLinkedList list = new DoublyLinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list);
		list.shiftLeft(0);
		System.out.println(list);
	}

	class Entry {

		Entry prev;
		Entry next;
		int elem;

		public Entry(Entry prev, Entry next, int elem) {
			this.prev = prev;
			this.next = next;
			this.elem = elem;
		}

	}
}

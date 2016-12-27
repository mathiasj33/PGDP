package pgdp.blatt07;

public class HeadList {

	Entry head;

	/**
	 * constructor empty HeadList
	 */
	public HeadList() {
		head = null;
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
			head.first = head;
			return;
		}
		Entry current = head;
		while (current.next != null)
			current = current.next;
		Entry newEntry = new Entry(head, null, info);
		current.next = newEntry;
	}

	/**
	 * Removes and returns the element at position index from this list.
	 * 
	 * @param index
	 *            position of the element that is removed
	 * @return value of the removed element
	 */
	public int remove(int index) {
		if (index < 0)
			return Integer.MIN_VALUE;
		else if (index == 0) {
			int value = head.elem;
			removeHead();
			return value;
		}

		int i = 0;
		Entry current = head;
		Entry prev;
		while (current.next != null) {
			i++;
			prev = current;
			current = current.next;
			if (index == i) {
				prev.next = current.next;
				return current.elem;
			}
		}

		return Integer.MIN_VALUE;
	}

	private void removeHead() {
		head = head.next;
		setHead(head);
	}

	/**
	 * sets the head of each list element to newHead
	 * 
	 * @param newHead
	 *            reference to the new head
	 */
	private void setHead(Entry newHead) {
		Entry current = head;
		while (current.next != null) {
			current = current.next;
			current.first = newHead;
		}

		head.first = newHead;
		newHead.first = newHead;
	}

	/**
	 * reverse the list example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] -->
	 * [1]
	 */
	public void reverse() {
		Entry current = head;
		Entry prev = null;
		Entry next = null;
		while (true) {
			next = current.next;
			current.next = prev;
			prev = current;
			if(next == null) break;
			current = next;
		}
		
		head = current;
		setHead(head);
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
	}

	class Entry {

		Entry first;
		Entry next;
		int elem;

		public Entry(Entry first, Entry next, int elem) {
			this.first = first;
			this.next = next;
			this.elem = elem;
		}

	}

}

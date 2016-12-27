package pgdp.blatt08;


public class SymmetricStack {

    private int[] data;
    private int first;
    private int last;

    public SymmetricStack() {
        data = new int[6];
        first = last = -1;
    }
    
    public SymmetricStack(int size) {
    	data = new int[size];
    	first = last = -1;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getNumberOfElements() {
    	if(isEmpty()) return 0;
    	if(first == last) return 1;
        if(first < last) return last - first + 1;
        int numFree = (first - last + 1) - 2;
        if(last < first) return data.length - numFree;
        return -1;
    } 

    public void increase() {
        if(!isFull()) return;
        int prevSize = data.length;
        int[] arr = new int[data.length * 2];
        int index = data.length / 2;
        
        for(int i = 0; i < data.length; i++) {
        	arr[i + index] = data[first];
        	removeFirst();
        }
        
        data = arr;
        first = index;
        last = first + prevSize - 1;
    }

    private boolean canDecrease() {
    	return getNumberOfElements() <= data.length / 4;
    }
    
    public void decrease() {
        if(!canDecrease()) return;
        int prevSize = getNumberOfElements();
        int[] arr = new int[data.length / 2];
        int index = data.length / 8;
        
        for(int i = 0; i <= getNumberOfElements(); i++) {
        	arr[i + index] = data[first];
        	removeFirst();
        }
        
        data = arr;
        first = index;
        last = first + prevSize - 1;
    }

    public boolean isEmpty() {
        return first == -1;
    }

    public boolean isFull() {
        return first == 0 && last == data.length - 1 ||
        		last == first -1 ||
        		first == last + 1;
    }

    public void prepend(int x) {
    	if(isEmpty()) {
        	first = last = data.length / 2;
        }
        else {
        	if(isFull()) increase();
        	first--;
        	if(first < 0) first = data.length - 1;
        }
        data[first] = x;
    }

    public void append(int x) {
        if(isEmpty()) {
        	first = last = data.length / 2;
        }
        else {
        	if(isFull()) increase();
        	last++;
        	if(last >= data.length) last = 0;
        }
        data[last] = x;
    }

    public void removeFirst() {
    	if(getNumberOfElements() == 1) {
    		first = last = -1;
    		return;
    	};
        first++;
        if(first >= data.length) first = 0;
        if(canDecrease()) decrease();
    }

    public void removeLast() {
    	if(getNumberOfElements() == 1) {
    		first = last = -1;
    		return;
    	};
        last--;
        if(last < 0) last = data.length - 1;
        if(canDecrease()) decrease();
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < data.length; i++) {
            if (first <= last && (i < first || i > last))
                out += "* ";
            if (first <= last && i > first && i < last)
                out += " " + data[i];
            if (first > last && i > last && i < first)
                out += "* ";
            if (first > last && (i > first || i < last))
                out += " " + data[i];
            if (i == first)
                out = out + "(" + data[i];
            if (i == last)
                out += (first == last ? "" : " " + data[i]) + ")";
        }
        return out;
    }
}


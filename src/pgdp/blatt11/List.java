package pgdp.blatt11;

import java.util.Iterator;

public class List<T> implements Iterable<T> {

    private final Object[] array;
    private final int size;
    private final int last;

    public List() {
        array = new Object[0];
        this.size = 0;
        this.last = -1;
    }

    private List(Object[] array, int size, int last) {
        this.array = array;
        this.size = size;
        this.last = last;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public List<T> add(T e) {
        int newLast = last + 1;
        Object[] newArray = new Object[array.length + 1];
        ArrayUtils.copy(array, newArray);
        newArray[newLast] = e;
        int newSize = size + 1;
        return new List<>(newArray, newSize, newLast);
    }
    
    public void addAll(T... elements) {
        for (T t : elements) {
            add(t);
        }
    }

    public List<T> remove(Object o) {
        if(indexOf(o) == -1) return this;
        int newLast = last - 1;
        Object[] copy = new Object[array.length];
        ArrayUtils.copy(array, copy);
        ArrayUtils.shiftLeftFrom(copy, indexOf(o));
        
        Object[] newArray = new Object[array.length - 1];
        ArrayUtils.copy(copy, newArray);
        int newSize = size - 1;
        
        return new List<>(newArray, newSize, newLast);
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            T t = (T) array[i];
            if (t.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            result += array[i];
            if (i != size - 1) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                T e = get(index);
                index++;
                return e;
            }
        };
    }
}

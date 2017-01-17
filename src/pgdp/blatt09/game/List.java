package pgdp.blatt09.game;

public class List<T> {

    private Object[] array;
    private int size;
    private int last = -1;

    public List() {
        this(10);
    }

    public List(int size) {
        array = new Object[size];
    }

    public static <T> List<T> fromArrray(T[] arr) {
        List<T> list = new List<>();
        list.addAll(arr);
        return list;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void add(T e) {
        last++;
        if (last >= array.length) {
            growArray();
        }
        array[last] = e;
        size++;
    }

    public void addAll(T... elements) {
        for (T t : elements) {
            add(t);
        }
    }

    public boolean remove(T e) {
        if(indexOf(e) == -1) return false;
        ArrayUtils.shiftLeftFrom(array, indexOf(e));
        last--;
        if (last < array.length / 3) {
            shrinkArray();
        }
        size--;
        return true;
    }

    private void growArray() {
        Object[] newArray = new Object[array.length * 2];
        assignArray(newArray);
    }

    private void shrinkArray() {
        Object[] newArray = new Object[array.length / 2];
        assignArray(newArray);
    }

    private void assignArray(Object[] array) {
        ArrayUtils.copy(this.array, array);
        this.array = array;
    }

    public boolean contains(T e) {
        return indexOf(e) != -1;
    }

    public int indexOf(T e) {
        for (int i = 0; i < size; i++) {
            T t = (T) array[i];
            if (t.equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public int getArrayLength() {
        return array.length;
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
    
    public T[] toArray(T[] array) {
        ArrayUtils.copy(this.array, array);
        return array;
    }
}

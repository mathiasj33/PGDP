package pgdp.blatt11;

import java.util.Iterator;

public class List<T> implements Iterable<T> {
    private final Entry<T> head;
    private final int size;
    
    public List() {
        head = null;
        size = 0;
    }
    
    private List(Entry<T> head, int size) {
        this.head = head;
        this.size = size;
    }
    
    public List<T> add(T e) {
        if(size == 0) {
            return new List(new Entry<>(null, e), 1);
        }
        Entry<T> newHead = createAddedToList(head, e);
        return new List<>(newHead, size + 1);
    }
    
    private Entry<T> createAddedToList(Entry<T> entry, T newElement) {
        if(entry.next == null) {
            Entry<T> addedEntry = new Entry<>(null, newElement);
            Entry<T> newEntry = new Entry<>(addedEntry, entry.data);
            return newEntry;
        }
        return new Entry<>(createAddedToList(entry.next, newElement), entry.data);
    }
    
    public List<T> remove(Object o) {
        if(head == null) return this;
        if(!contains(o)) return this;
        if(o.equals(head.data)) {
            return new List<>(head.next, size - 1);
        }
        Entry<T> newHead = createRemovedFromList(head, o);
        return new List<>(newHead, size - 1);
    }
    
    private Entry<T> createRemovedFromList(Entry<T> entry, Object toRemove) {
        if(entry.next.data.equals(toRemove)) {
            Entry<T> newEntry = new Entry<>(entry.next.next, entry.data);
            return newEntry;
        }
        return new Entry<>(createRemovedFromList(entry.next, toRemove), entry.data);
    }
    
    public T get(int index) {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException();
        int i = 0;
        Entry<T> current = head;
        while(current.next != null) {
            if(i == index) return current.data;
            i++;
            current = current.next;
        }
        if(i == index) return current.data;
        return null;
    }
    
    public boolean contains(Object o) {
        for(T e : this) {
            if(e.equals(o)) return true;
        }
        return false;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        if(head == null) return "[]";
        String s = "[";
        Entry<T> entry = head;
        while(entry.next != null) {
            s += entry.data + ",";
            entry = entry.next;
        }
        s += entry.data + "]";
        return s;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Entry<T> current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if(current == null) return null;
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    
    private class Entry<T> {
        private final Entry<T> next;
        private final T data;

        public Entry(Entry<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }
}

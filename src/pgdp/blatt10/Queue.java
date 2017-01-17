package pgdp.blatt10;

/**
 * My Queue implementation via an underlying (array-based) list
 */

import pgdp.blatt09.game.List;

public class Queue<T> {
    private List<T> list;
    
    public Queue() {
        list = new List<>();
    }
    
    public void enqueue(T e) {
        list.add(e);
    }
    
    public T dequeue() {
        T e = list.get(0);
        list.remove(e);
        return e;
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean remove(T e) {
        return list.remove(e);
    }
    
    public int indexOf(T e) {
        return list.indexOf(e);
    }
}

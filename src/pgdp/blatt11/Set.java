/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pgdp.blatt11;

import java.util.Iterator;

public class Set<T> implements Iterable<T> {
    private final List<T> list;
    
    public Set() {
        list = new List<>();
    }
    
    private Set(List<T> list) {
        this.list = list;
    }
    
    public Set<T> add(T e) {
        if(e == null) throw new NullPointerException();
        if(contains(e)) return this;
        return new Set(list.add(e));
    }
    
    public Set<T> remove(Object o) {
        if(o == null) throw new NullPointerException();
        if(!contains(o)) return this;
        return new Set(list.remove(o));
    }
    
    public boolean contains(Object o) {
        return list.contains(o);
    }
    
    public int size() {
        return list.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Set other = (Set) o;
        if(list == null) {
            if(other.list != null) return false;
        }
        for(T t : list) {
            if(!other.contains(t)) return false;
        }
        for(Object obj : other) {
            if(!contains(obj)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + ((list == null) ? 0 : list.hashCode());
        return hash;
    }
    
    @Override
    public String toString() {
        String s = "{";
        for(int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            s += t + (i == list.size() - 1 ? "" : ",");
        }
        s += "}";
        return s;
    }
    
    public static void main(String[] args) {
        Set<String> set = new Set<>();
        System.out.println(set);
        set = set.add("hallo");
        set = set.add("1");
        set = set.add("2");
        set = set.add("3");
        set = set.remove("2");
        
        Set<String> other = new Set<>();
        other = other.add("3");
        other = other.add("1");
        other = other.remove("3");
        other = other.add("3");
        other = other.add("hallo");
        
        System.out.println(set);
        System.out.println(other);
        
        System.out.println(set.equals(other));
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

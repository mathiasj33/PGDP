/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pgdp.blatt11;

import java.util.Iterator;
import java.util.Objects;

public class Set<T> implements Iterable<T> {
    private final ArrayList<T> list;
    
    public Set() {
        list = new ArrayList<>();
    }
    
    private Set(ArrayList<T> list) {
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
        set = set.add("Hey");
        set = set.add("Jo");
        set = set.add("fuck");
        
        set = set.remove("Jo");
        
        System.out.println(set);
//        list = list.remove("Jo");
//        for(String s : list) {
//            System.out.println(s);
//        }
//        System.out.println(list.contains("Jo"));
//        System.out.println(list.size());
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

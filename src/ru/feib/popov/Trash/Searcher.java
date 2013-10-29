/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.util.ArrayList;

/**
 *
 * @author Suobig
 */

public class Searcher {
    public static void main(String[] args) {
        SearchableList<MyClass> list = new SearchableList<>();
        list.add(new MyClass(1, "a", 10));
        list.add(new MyClass(2, "b", 20));
        list.add(new MyClass(3, "c", 20));
        list.add(new MyClass(4, "d", 10));
        
        MyConditions conditions = new MyConditions();
        
        conditions.setId(4);
        System.out.println("id = 4: " + list.find(conditions));
        
        conditions.clear();
        conditions.setSize(10);
        System.out.println("size = 10: " + list.find(conditions));
        
        conditions.clear();
        conditions.setSize(10);
        conditions.setId(1);
        System.out.println("id = 1 & size = 10: " + list.find(conditions));
        
        conditions.clear();
        conditions.setSize(10);
        conditions.setName("d");
        System.out.println("size = 10 & name = d: " + list.find(conditions));
        
        conditions.clear();
        conditions.setId(2);
        conditions.setSize(10);
        conditions.setName("d");
        System.out.println("id = 2 & size = 10 & name = d: " + 
                list.find(conditions));
    }
}

class SearchableList<T> extends ArrayList<T> {
    public SearchableList<T> find(SearchConditions<T> conditions) {
        SearchableList<T> list = new SearchableList<>();
        for (T o : this) {
            if (conditions.fits(o)) 
                list.add(o);
        }
        return list;
    }
}

interface SearchConditions<T> {
    boolean fits(T o);
}

class MyConditions implements SearchConditions<MyClass> {
    private boolean isIdSet;
    private boolean isSizeSet;
    private boolean isNameSet;
    private int id;
    private String name;
    private int size;
        
    public void setId(int id) {
        isIdSet = true;
        this.id = id;
    }
    
    public void setName(String name) {
        isNameSet = true;
        this.name = name;
    }
    
    public void setSize(int size) {
        isSizeSet = true;
        this.size = size;
    }
    
    public void clear() {
        isIdSet = false;
        isSizeSet = false;
        isNameSet = false;
    }
        
    @Override
    public boolean fits(MyClass  o ) {
        if (isIdSet && o.getId() != id) return false;
        if (isNameSet && !name.equals(o.getName())) return false;
        if (isSizeSet && o.getSize() != size) return false;
        return true;
    }
}


class MyClass {
    private int id;
    private String name;
    private int size;
    
    public MyClass(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }
        
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public String toString() {
        return "{" + id + ", " + name + ", " + size + "}";
    }
}


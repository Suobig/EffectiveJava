/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LispList;

/**
 *
 * @author popov
 */
public class EmptyList implements LispList {

    @Override
    public boolean isEmpty() {
        return true;
    }
    
    @Override
    public NonEmptyList cons(Object head) {
        return new NonEmptyList((LispList)this,head);
    };

    @Override
    public Object head() {
        throw new UnsupportedOperationException(
                "Empty list doesn't support this method."); 
    }

    @Override
    public LispList tail() {
        throw new UnsupportedOperationException(
                "Empty list doesn't support this method.");
    }
    
    @Override
    public int length() {
        return 0;
    }
    
    @Override
    public String toString() {return "";}
    
    @Override
    public LispList merge(LispList other) {
        return other;
    }
}

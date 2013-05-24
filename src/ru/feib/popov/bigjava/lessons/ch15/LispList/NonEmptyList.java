/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LispList;

/**
 *
 * @author popov
 */
public class NonEmptyList implements LispList {

    Object mHead;
    LispList mTail;
    int mLength;
    
    public NonEmptyList(LispList listTail, Object listHead) {
        mHead = listHead;
        mTail = listTail;
        mLength = listTail.length() + 1;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override    
    public Object head() {
        return mHead;
    }
    
    @Override
    public NonEmptyList cons(Object listHead) {
        return new NonEmptyList((LispList)this, listHead);
    }
    
    @Override
    public LispList tail() {
        return mTail;
    }
    
    @Override
    public int length() {
        return mLength;
    }
    
    @Override
    public String toString() {
        return head() + tail().toString();
    }
    
    @Override
    public LispList merge(LispList other) {
        LispList newList = new EmptyList();
        
        int thisLength = this.length();
        int otherLength = other.length();
        LispList thisList = this;
        LispList otherList = other;
        
        while (thisLength + otherLength > 0) {
            if (thisLength > otherLength) { 
                newList = newList.cons(thisList.head());
                thisLength--;
                thisList = thisList.tail();
            } else {
                newList = newList.cons(otherList.head());
                otherLength--;
                otherList = otherList.tail();
            } 
        }   
        return newList;
    }
}


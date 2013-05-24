/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LispList;

/**
 *
 * @author popov
 */
public class LispListTester {
    private final static LispList NIL = new EmptyList();
    
    public void run() {
//        LispList list = NIL.cons("C").cons("B.").cons("A.");
        LispList list1 = NIL.cons("1").cons("2").cons("3").cons("4");
        LispList list2 = NIL.cons("5").cons("6");
        LispList finalList = list1.merge(list2);
        
        System.out.println(finalList + " " + finalList.length());
    }
}
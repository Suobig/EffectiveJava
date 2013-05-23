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
        LispList list = NIL.cons("C").cons("B.").cons("A.");
        System.out.println(list + " " + list.length());
    }
}

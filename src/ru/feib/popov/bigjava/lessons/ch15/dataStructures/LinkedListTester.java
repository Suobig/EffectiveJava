/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.dataStructures;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author popov
 */
public class LinkedListTester {
    
    private LinkedList<String> staff;
    
    public void run() {
        newList();
        printList();
//        reverse();
        downsize();
        printList();
    }
    
    public void downsize() {
        ListIterator<String> iterator = staff.listIterator();
        
        for (int i = 0; i < staff.size(); i++) {
            staff.remove(i);
        }
    }
    
    private void newList() {
        staff = new LinkedList<>();
        
        staff.add("Andrey");
        staff.add("Sergey");
        staff.add("Anton");
        staff.add("Irina");
        staff.add("Dmitry");
        staff.add("Alexander");
        staff.add("Vladimir");
    }
    
    private void reverse() {        
        for (int i = 0; i < staff.size(); i++) {
            staff.add(i, staff.pollLast());
        }
    }
    
    public void printList() {
        System.out.println("Staff:\n");
        for (String member : staff) {
            System.out.println(member);
        }
    }
}

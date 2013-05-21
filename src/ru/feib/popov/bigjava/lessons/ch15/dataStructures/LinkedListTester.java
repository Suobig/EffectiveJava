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
        downsize();
        printList();
    }
    
    public void downsize() {
        ListIterator<String> iterator = staff.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            if (iterator.hasNext()) iterator.next();
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
    }
    
    public void printList() {
        System.out.println("Staff:\n");
        for (String member : staff) {
            System.out.println(member);
        }
    }
}

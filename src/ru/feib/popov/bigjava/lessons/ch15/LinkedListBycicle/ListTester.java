package ru.feib.popov.bigjava.lessons.ch15.LinkedListBycicle;

/**
   A program that tests the LinkedList class
*/
public class ListTester
{  
   public void run() {  
      LinkedList staff = new LinkedList();
      staff.addFirst("Tom");
      staff.addFirst("Romeo");
      staff.addFirst("Harry");
      staff.addFirst("Diana");
      staff.addLast("Susanna");
      
      ListIterator adder = staff.listIterator();
      
      while (adder.hasNext()) adder.next();
      
      adder.add("Wendy");
      adder.remove();
      staff.addLast("Zed");
      
      
      // | in the comments indicates the iterator position

      ListIterator iterator = staff.listIterator(); // |DHRT
      
//      iterator.next(); // D|HRT
//      iterator.next(); // DH|RT
//
//      // Add more elements after second element
//      
//      iterator.add("Juliet"); // DHJ|RT
//      iterator.add("Nina"); // DHJN|RT
//
//      iterator.next(); // DHJNR|T
//
//      // Remove last traversed element 
//
//      iterator.remove(); // DHJN|T
//     
//      // Print all elements
//
//      iterator = staff.listIterator();
//      staff.reverse();
      
      while (iterator.hasNext())
         System.out.print(iterator.next() + " ");
      System.out.println();
      System.out.println("Expected: Diana Harry Romeo Tom Susanna");
   }
}

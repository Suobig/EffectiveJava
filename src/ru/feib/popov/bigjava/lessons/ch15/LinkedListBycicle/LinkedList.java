package ru.feib.popov.bigjava.lessons.ch15.LinkedListBycicle;

import java.util.NoSuchElementException;

/**
   A linked list is a sequence of nodes with efficient
   element insertion and removal. This class 
   contains a subset of the methods of the standard
   java.util.LinkedList class.
*/
public class LinkedList
{  
   protected Node first;
   protected Node last;
   protected int currentSize;
   
   /** 
      Constructs an empty linked list.
   */
   public LinkedList() {  
      first = null; 
      last = null;
      currentSize = 0;
   }
   
   /**
      Returns the first element in the linked list.
      @return the first element in the linked list
   */
   public Object getFirst() {  
      if (first == null) 
         throw new NoSuchElementException();
      return first.getData();
   }

   /**
      Removes the first element in the linked list.
      @return the removed element
   */
   public Object removeFirst() {
      if (first == null) 
         throw new NoSuchElementException();
      //Check if removing first node affects last one
      //If it does - modify last node accordingly
      if (first == last) last = null;
      Object element = first.getData();
      first = first.getNext();
      currentSize -= 1;
      return element;
   }
   
   /**
      Adds an element to the front of the linked list.
      @param element the element to add
   */
   public void addFirst(Object element) {  
      Node newNode = new Node();
      newNode.setData(element);
      newNode.setNext(first);
      first = newNode;
      if (last == null) last = newNode;
      currentSize += 1;
   }
   
   public void addLast(Object element) {
       Node newNode = new Node();
       newNode.setData(element);
       newNode.setNext(null);
       if (last != null) last.setNext(newNode);
       last = newNode;
   }
   
   public void reverse() {
       Node currentNode = first;
       Node nextNode;
       Node previousNode = null;
       
       while(currentNode.getNext() != null) {
           nextNode = currentNode.getNext();
           currentNode.setNext(previousNode);
           previousNode = currentNode;
           currentNode = nextNode;
       }           
       currentNode.setNext(previousNode);
       first = currentNode;
   }
   
   public int size() {
       return currentSize;
   }
   
   
   /**
      Returns an iterator for iterating through this list.
      @return an iterator for iterating through this list
   */
   public ListIterator listIterator() {
       return new LinkedListIterator();
   }
}

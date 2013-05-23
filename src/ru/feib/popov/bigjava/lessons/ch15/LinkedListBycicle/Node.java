/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LinkedListBycicle;

/**
 *
 * @author popov
 */
 public  class Node
   {  
      private Object mData;
      private Node mNext;
      
      
      public Node getNext() {
          return mNext;
      }
      
      public void setNext(Node next) {
          mNext = next;
      }
      
      public Object getData() {
          return mData;
      }
      
      public void setData(Object data) {
          mData = data;
      }
   }

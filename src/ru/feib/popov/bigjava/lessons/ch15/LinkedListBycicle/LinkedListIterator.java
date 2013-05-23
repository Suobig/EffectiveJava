/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LinkedListBycicle;

import java.util.NoSuchElementException;

/**
 *
 * @author popov
 */
  class LinkedListIterator extends LinkedList implements ListIterator {  
      private Node mPosition;
      private Node mPrevious;
      
      /**
         Constructs an iterator that points to the front
         of the linked list.
      */
      public LinkedListIterator()
      {
         mPosition = null;
         mPrevious = null;
      }
      
      /**
         Moves the iterator past the next element.
         @return the traversed element
      */
      
      @Override
      public Object next()
      {  
         if (!hasNext())
            throw new NoSuchElementException();
         mPrevious = mPosition; // Remember for remove

         if (mPosition == null)
            mPosition = super.first;
         else
            mPosition = mPosition.getNext();

         return mPosition.getData();
      }
      
      /**
         Tests if there is an element after the iterator position.
         @return true if there is an element after the iterator position
      */
      @Override
      public boolean hasNext()
      {  
         if (mPosition == null)
            return super.first != null;
         else
            return mPosition.getNext() != null;
      }
      
      /**
         Adds an element before the iterator position
         and moves the iterator past the inserted element.
         @param element the element to add
      */
      @Override
      public void add(Object element)
      {  
         if (mPosition == null)
         {
            addFirst(element);
            mPosition = first;
         } else if (mPosition == last) {
            addLast(element);
            mPosition = last;
         } else {  
            Node newNode = new Node();
            newNode.setData(element);
            newNode.setNext(mPosition.getNext());
            mPosition.setNext(newNode);
            mPosition = newNode;
         }
         currentSize += 1;
         mPrevious = mPosition;
      }
      
      /**
         Removes the last traversed element. This method may
         only be called after a call to the next() method.
      */
      @Override
      public void remove()
      {  
         if (mPrevious == mPosition)
            throw new IllegalStateException();

         if (mPosition == first)
         {
            removeFirst();
         }
         else 
         {  
            mPrevious.setNext(mPosition.getNext());
         }
         if (mPosition == last) last = mPrevious;
         mPosition = mPrevious;
         currentSize -= 1;
      }

      /**
         Sets the last traversed element to a different value. 
         @param element the element to set
      */
      @Override
      public void set(Object element)
      {
         if (mPosition == null)
            throw new NoSuchElementException();
         mPosition.setData(element);
      }
   }
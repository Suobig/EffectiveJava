package ru.feib.popov.bigjava.lessons.ch09.p8;

public class SquareSequence implements Sequence
{
   private int n;

   /**
    * Get square value of next integer
    * @return square value
    */
   @Override
   public int next()
   {
      n++;
      return n * n; 
   }
   
   /**
    * That class always has next value
    * @return always true
    */
   @Override 
   public boolean hasNext() {return true;}
}

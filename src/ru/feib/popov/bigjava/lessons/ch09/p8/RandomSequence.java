package ru.feib.popov.bigjava.lessons.ch09.p8;

public class RandomSequence implements Sequence
{
    /**
     * Get next random integer value from 0 to Integer.MAX_VALUE
     * @return random integer
     */
   @Override
   public int next()
   {
      return (int) (Integer.MAX_VALUE * Math.random());
   }
   
   /**
    * That class always has next value
    * @return always true
    */
   @Override
   public boolean hasNext() {return true;}
}

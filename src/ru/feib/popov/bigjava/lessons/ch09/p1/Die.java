package ru.feib.popov.bigjava.lessons.ch09.p1;

import java.util.Random;

/**
   This class models a die that, when cast, lands on a random
   face.
*/
public class Die 
{
   private Random generator;
   private int sides;
   private int mLastFace;

   /**
      Constructs a die with a given number of sides.
      @param s the number of sides, e.g. 6 for a normal die
   */
   public Die(int s)
   {
      sides = s;
      generator = new Random();
      mLastFace = 1 + generator.nextInt(sides);
   }

   /**
      Simulates a throw of the die
      @return the face of the die 
   */
   public void throwIt()
   {
      mLastFace = 1 + generator.nextInt(sides);
   }
    /**
     * Show last face after throw
     * @return 
     */
   public int showFace() {
       return mLastFace;
   }
}

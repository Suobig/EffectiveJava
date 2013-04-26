package ru.feib.popov.bigjava.lessons.ch09.p8;

import java.util.Arrays;

/**
   This class analyzes the distribution of the last digit of values
   from a sequence.
*/
public class LastDigitDistribution
{
   private static final char FILL_CHAR = '*';
   private static final int MAX_HYSTOGRAM_LENGTH = 40;
   
   private int[] counters;
   private int totalCount = 0;

   /**
      Constructs a distribution whose counters are set to zero.
   */
   public LastDigitDistribution()
   {
      counters = new int[10];
   }

   /**
      Processes values from this sequence.
      @param seq the sequence from which to obtain the values
      @param valuesToProcess the number of values to process
   */
   public void process(Sequence seq, int valuesToProcess)
   {
      for (int i = 1; i <= valuesToProcess && seq.hasNext(); i++)
      {
         int value = seq.next();
         int lastDigit = value % 10;
         counters[lastDigit]++;
         totalCount++;
      }
   }

   /**
      Displays the counter values of this distribution.
   */
   public void display()
   {
      long hystLength;
      for (int i = 0; i < counters.length; i++)
      {
         hystLength = Math.round(counters[i] / 
                 (double)totalCount * MAX_HYSTOGRAM_LENGTH);
         if (hystLength == 0 && counters[i] > 0) hystLength = 1;
         System.out.println(i + ": " + nSymbols(FILL_CHAR, (int)hystLength));
      }
   }
   
   private String nSymbols(char symbol, int num) {
       char[] chars = new char[num];
       Arrays.fill(chars, symbol);
       return new String(chars);
   }
}

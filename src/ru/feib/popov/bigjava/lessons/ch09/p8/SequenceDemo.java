package ru.feib.popov.bigjava.lessons.ch09.p8;

public class SequenceDemo
{
   public void runDemo()
   {
      System.out.println("Square distribution");
      LastDigitDistribution dist1 = new LastDigitDistribution();
      dist1.process(new SquareSequence(), 1000);
      dist1.display();
      
      System.out.println("\n Random distribution");

      LastDigitDistribution dist2 = new LastDigitDistribution();
      dist2.process(new RandomSequence(), 1000);
      dist2.display();
      
      System.out.println("\n Prime distribution");
      
      LastDigitDistribution dist3 = new LastDigitDistribution();
      dist3.process(new PrimeSequence(), 1000);
      dist3.display();
      
      System.out.println("\n Population distribution");
      
      try {
          LastDigitDistribution dist4 = new LastDigitDistribution();
          dist4.process(new PopulationSequence(), 1000);
          dist4.display();
      } catch (Exception e) {
          System.out.println(e.toString() + "\nCoudn't open file.");
      }
   }
}

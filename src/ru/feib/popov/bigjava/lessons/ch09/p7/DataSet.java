
package ru.feib.popov.bigjava.lessons.ch09.p7;

/**
   Computes the average of a set of data values.
*/
public class DataSet
{
   private double sum;
   private Object maximum;
   private Object minimum;
   private int count;
   private Measurer measurer;

   /**
      Constructs an empty data set with a given measurer.
      @param aMeasurer the measurer that is used to measure data values
   */
   public DataSet(Measurer aMeasurer)   {
      sum = 0;
      count = 0;
      maximum = null;
      minimum = null;
      measurer = aMeasurer;
   }
   
   public DataSet() {
       sum = 0;
       count = 0 ;
       maximum = null;
       minimum = null;
   }

   /**
      Adds a data value to the data set.
      @param x a data value
   */
   public void add(Object x)
   {
      sum = sum + measurer.measure(x);
      if (count == 0 || measurer.measure(maximum) < measurer.measure(x))
         maximum = x;
      if (count == 0 || measurer.measure(minimum) > measurer.measure(x))
          minimum = x;
      count++;
      
   }
   
   public void add(Measurable x) {
       Measurable measMax = (Measurable)maximum;
       Measurable measMin = (Measurable)minimum;
       
       sum = sum + x.getMeasure();
       if (count == 0 || measMax.getMeasure() < x.getMeasure())
           maximum = x;
       if (count == 0 || measMin.getMeasure() > x.getMeasure()) 
           minimum = x;
       count++;
   }

   /**
      Gets the average of the added data.
      @return the average or 0 if no data has been added
   */
   public double getAverage()
   {
      if (count == 0) return 0;
      else return sum / count;
   }

   /**
      Gets the largest of the added data.
      @return the maximum or 0 if no data has been added
   */
   public Object getMaximum()
   {
      return maximum;
   }
   
   /**
    * Get the lowest of all added data
    * @return 
    */
   public Object getMinimum() {
       return minimum;
   }
}

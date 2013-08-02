/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author popov
 */
//Builder pattern
public class NutritionFactsBuilder {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        //Required parameters
        private final int servingSize;
        private final int servings;

        //Optional paramteters
        private int calories        = 0;
        private int fat             = 0;
        private int carbohydrate    = 0;
        private int sodium          = 0;
        
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        
        public Builder calories(int val) 
            { calories = val;       return this; }
        public Builder fat(int val) 
            { fat = val;            return this; }
        public Builder carbohydate(int val) 
            { carbohydrate = val;   return this; }
        public Builder sodium(int val) 
            { sodium = val;         return this; }
        
        public NutritionFactsBuilder build() {
            return new NutritionFactsBuilder(this);
        }
    }
        
    private NutritionFactsBuilder(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories; 
        fat = builder.fat; 
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
    
    //Example how to call class with builder
    private void caller() {
        NutritionFactsBuilder cocaCola = 
                new NutritionFactsBuilder.Builder(240, 8).
                calories(100).sodium(35).carbohydate(27).build();
    }
}

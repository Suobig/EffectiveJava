/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author popov
 */
public class NutritionFactsJavaBeans{
    //Parameters initialized to default values (if any)
    private int servingSize = -1; //Required, no default value
    private int servings    = -1; //Required; no default value
    private int calories    = 0;
    private int fat         = 0;
    private int sodium      = 0;
    private int carbohydrate = 0;
    
    public NutritionFactsJavaBeans() {}
    
    public void setServingSize(int val) {servingSize = val;}
    public void setServings(int val) {servings = val;}
    public void setCalories(int val) {calories = val;}
    public void setFat(int val) {fat = val;}
    public void setSodium(int val) {sodium = val;}
    public void carbohydrate(int val) {carbohydrate = val;}
}

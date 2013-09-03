package net.suobig.effectivejava.ch2_СreateAndDelete;

/**
 *
 * @author popov
 */

    /*  Используйте builder, если в конструкторе выходит сликом много параметров

    Например, у нас есть класс пищевая ценность, который содержит ряд не только 
набор обязательных параметров (например вес одной порции, количество порций), 
но и множество дополнительных. Конечно, можно для каждого набора параметров 
сделать свой конструктор:*/
class TelescopeConstructorTemplate {
    public class NutritionFacts {
        private final int servingSize;  // (mL)             required
        private final int servings;      // (per container)  required
        private final int calories;     //                  optional
        private final int fat;          // (g)              optional
        private final int sodium;       // (mg)             optional
        private final int carbohydrate; // (g)              optional

        public NutritionFacts(int servingSize, int servings) {
            this(servingSize, servings, 0);
        }

        public NutritionFacts(int servingSize, int servings, int calories) {
            this(servingSize, servings, calories, 0);
        }

        public NutritionFacts(int servingSize, int servings, 
                int calories, int fat) {
            this(servingSize, servings, calories, fat, 0);
        }

        public NutritionFacts(int servingSize, int servings, 
                int calories, int fat, int sodium) {
            this(servingSize, servings, calories, fat, sodium, 0);
        }

        public NutritionFacts(int servingSize, int servings, 
                int calories, int fat, int sodium, int carbohydrate) {
            this.servingSize = servingSize;
            this.servings = servings;
            this.calories = calories;
            this.fat = fat;
            this.sodium = sodium;
            this.carbohydrate = carbohydrate;
        }
    }
/*  Этот шаблон называется «телескопический конструктор» и он обладает рядом 
серьзеных недостатков:
    1)	его сложно поддерживать и читать
    2)	если нужно заполнить только servingSize, servings, carbohydrate, 
то придется вызвать такой конструктор: */
    NutritionFacts cocaCola = new NutritionFacts(240, 8, 0, 0, 0, 20); 
/*  Т.е. все неиспользуемые параметры все равно придется передать как 0.
    3)	пользователь может перепутать два параметра местами и программа 
будет работать с ошибкой.*/
}
/*  Второй способ – использовать шаблон «JavaBeans» с пустым конструктором и 
отдельным сеттером для каждого параметра:*/
class NutritionFactsJavaBean {
        //Parameters initialized to default values (if any)
        private int servingSize = -1; //Required, no default value
        private int servings    = -1; //Required; no default value
        private int calories    = 0;
        private int fat         = 0;
        private int sodium      = 0;
        private int carbohydrate = 0;

        public NutritionFactsJavaBean() {}

        public void setServingSize(int val) {servingSize = val;}
        public void setServings(int val) {servings = val;}
        public void setCalories(int val) {calories = val;}
        public void setFat(int val) {fat = val;}
        public void setSodium(int val) {sodium = val;}
        public void setCarbohydrate(int val) {carbohydrate = val;}
    }
/*    Данная реализация является достаточно простой, и инициализация класса 
происходит понятным образом:*/
class InitializeJavaBeans {
    public void init() {
        NutritionFactsJavaBean cocaCola = new NutritionFactsJavaBean();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}
/*    Но данный шаблон имеет серьзеный недостаток: так как создание объекта 
происходит в несколько операций, в условиях многопоточности может произойти 
так, что одна часть программы начнет использовать объект, который другая часть 
программы еще не успела до конца создать. Подобные ошибки очень трудно искать, 
так как они будут возникать не постоянно, а их причина может оказаться очень 
сильно удалена от места возникновения. Кроме того, класс, созданный по шаблону 
JavaBeans принципиально нельзя сделать Immutable.*/
   
/*  Существует, однако, шаблон проектирования, который совмещает в себе простоту 
JavaBeans шаблона и безопасность телескопического конструктора. Он 
называется «строитель» (Builder). Его идея заключается в том, что создается 
промежуточный объект-построитель, которому передаются все необходимые 
параметры, а затем с помощью метода build создается нужный объект. На 
практике это выглядит следующим образом:*/
class NutritionFactsBuilder {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    static class Builder {
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
        servingSize     = builder.servingSize;
        servings        = builder.servings;
        calories        = builder.calories; 
        fat             = builder.fat; 
        sodium          = builder.sodium;
        carbohydrate    = builder.carbohydrate;
    }
}
/*  При использовании шаблона Builder создание экземпляра класса легко читается
 и понимается однозначно:*/
class InitializeBuilder {
    NutritionFactsBuilder cocaCola = 
            new NutritionFactsBuilder.Builder(240, 8).
            calories(100).sodium(35).carbohydate(27).build();
}
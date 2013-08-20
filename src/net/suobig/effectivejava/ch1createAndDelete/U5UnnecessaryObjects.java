/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.ch1createAndDelete;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author popov
 */
public class U5UnnecessaryObjects {
/*  Там, где это возможно, используйте уже существующий объект вместо создания 
 нового*/
    String s = new String("stringette"); //Не делайте так!
/* Приведенный код при каждом выполнении будет создавать нвоый объект String 
 Вместо этого пользуйтесь */
    String s1 = "stringette";
/* Любой объект, принимающий значение "stringette" пользуется одним и тем же
 объектом, созданным только один раз - при первом использовании этой строки.
  
   Любой неизменяемый (immutable) объект может быть использован неоднократно.
Кроме того, зачастую целесообразно пользоваться несколько раз и изменяемыми 
объектами, если есть гарантия того, что они не будут изменены между вызовами

   Например, изменяемый объект Date можно исползовать несколько раз, если 
он не изменяется между вызовами:
 */
}

class PersonUnnecessary {
    private final Date birthDate = null;
    //Остальная часть кода опущена

    //Не делайте так!
    public boolean isBabyBoomer() {
        //Необязательный дорогостоящий вызов объекта:
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 &&
                birthDate.compareTo(boomEnd) < 0;
    }
}
/* Объекты gmtCal, boomStart и boomEnd создаются при _каждом_ вызове проверки,
 что отнимает очень много ресурсов и является совершенно излишним*/
    
class PersonReuse {
    private final Date birthDate = null;
    // Остальные поля и конструкторы опущены

    /*
     * Дата начала и окончания беби-бума
     */
    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();            
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
                birthDate.compareTo(BOOM_END) < 0;
    }
}
/* Инициализируя пременные BOOM_START, BOOM_END и gmtCal только при иницализации
класса мы получаем значительный выиигрыш в производительности.
 
   Остается проблема, что, если isBabyBoomer ни разу не будет вызван, указанные
объекты все равно будут инициализированы. Эту проблему можно решить с помощью
ленивой иницилазиции (lazy initializing). Однако смысла нет - делать трудно, 
а прибавка производительности незаметна.

   Еще одна возможность излишнего создания объектов появилась с внедрением в 
Java автобоксинга (autoboxing): */
class Autoboxing {
    private void method() {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
    }   
}
/* Программа с таким листингом работает крайне медленно, так как вынуждена 
создавать 2^31 экземпляров объектов типа Long (каждый раз, когда переменная 
long i присваивается переменной Long sum)

   С другой стороны, не следует в любой ситуации создавать пул объектов, чтобы
при каждой попытке создания искать объекты в нем. Это имеет смысл только для
очень "тяжелых" объектов, таких как подключения к базе данных. В остальных 
случаях куда проще и дешевле просто создать новый объект.
 */


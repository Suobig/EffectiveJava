
package net.suobig.effectivejava.ch3_AllObjectMethods;

import java.util.HashMap;
import java.util.Map;


/*  Вы _обязаны_ переопределить метод hashCode в каждом случае, когда 
 переопредляете equals. Иначе ваш объект будет некорректно работать в любых
 коллекциях, где вычисляется хэш.
    
    Контракт для метода Object.hashCode такой:
    1. Два вызова метода hashCode должны вернуть одно и тоже число, если между
этими вызовами работа приложения не останавливалась и не было изменено ни одно 
из полей, используемых в методе equals.
    2. Если два объекта определены методом equals как равные, метод hashCode 
должен вернуть одинаковое значение для них обоих.
    3. Обратное не обязано быть верным.
    
    Если вы не переопределите hashCode, при этом переопределив equals, то тем
самым нарушите 2-е условие - возможна ситуация, что одинаковые объекты будут
иметь разный хэш код.

    Пример того, как это может повлиять на работу программы:*/

final class PhoneNumberNoHash {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    
    public PhoneNumberNoHash(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max)
            throw new IllegalArgumentException(name +": " + arg);
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof PhoneNumberNoHash))
            return false;
        
        PhoneNumberNoHash pn = (PhoneNumberNoHash)o;
        return pn.lineNumber == lineNumber 
                && pn.prefix == prefix 
                && pn.areaCode == areaCode;
    }
    
    //Нет hashCode - будут проблемы в коллекциях!
}

class RunnerNoHash {
    public static void main(String args[]) {
        Map<PhoneNumberNoHash, String> m = new HashMap<>();
        m.put(new PhoneNumberNoHash(707, 867, 5309), "Jenny");
        System.out.println("Return: " + m.get(new PhoneNumberNoHash(707, 867, 5309)) + 
                ", expected 'Jenny'"); // return null
    }
}
/*  Существуют способы создать вполне корректную с точки зрения контракта, но 
 совершенно недопустимую в нормальном проекте хэш-функцию. Например: */
class BrokenCash {
    @Override
    public int hashCode() {
        return 42;
    }
}

/*Проблема в том, что при таком подходе _любой_ экземпляр будет иметь один и 
 тот же хэш. Хэш-таблица при таком подходе деградирует до обычного списка и 
 работает за время O(n^2) вместо O(n). Т.е., для большой таблицы, не работает 
 вообще. 
    Для того, чтобы создать хорошую хэш-фунукцию для почти любого класса, 
достаточно:
    1. int result = 17 (например; 17 взято от балды).
    2. Для каждого значиомого поля f (используемого в equals):
        a) вычислить хэш-код c. Для этого:
            i.      if boolean: (f ? 1 : 0)
            ii.     if byte||char||short||int: (int)f
            iii.    if long: (int)(f ^ (f >>> 32))
            iv.     if float: Float.flatToIntBits(f)
            v.      if double: Double.doubleToLongBits(f); п. 2.а.iii
            vi.     if object: f.hashCode();
            vii.    if null: 0
            viii.   if array: вычисляется хэш каждого элемента коллекции, 
                    потом суммируется наравне с другими полями класса
        б) сложение хэш-кодов и вычисление результат проиходит по формуле
            result = 31 * result + c;
    3. return result
    4. Проверить, что hastCode выдает одинаковые значения для равных объектов.
 Провести проверку юнит-тестами.
 
    Поля, являющиеся комбинацией других полей класса, можно не включать в 
расчет.
    
    Поля, которые не используются при вычислении equals, _обязаны_ быть 
исключены из расчета.

    Умножение на 31 используется, потому что после всех оптимизаций эта операция
получается очень быстро (31 * i = (i << 5) - i) 

    Покажем работу на примере PhoneNumber:*/

final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    
    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max)
            throw new IllegalArgumentException(name +": " + arg);
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof PhoneNumber))
            return false;
        
        PhoneNumber pn = (PhoneNumber)o;
        return pn.lineNumber == lineNumber 
                && pn.prefix == prefix 
                && pn.areaCode == areaCode;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        return result;
    }
}

/*  В некоторых случаях, когда объект неизменяемый, а вычисление хэша занимает
значительное время, бывает целесообразно хранить хэш объекта в одном из его 
полей. При этом есть выбор - либо вычислять хэш в момент создания объекта, либо
сделать т.н. ленивую инициализацию - вычислять хэш при первом обращении к нему*/

final class PhoneNumberLazyHash {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    private volatile int hashCode;
    
    public PhoneNumberLazyHash(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max)
            throw new IllegalArgumentException(name +": " + arg);
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof PhoneNumberLazyHash))
            return false;
        
        PhoneNumberLazyHash pn = (PhoneNumberLazyHash)o;
        return pn.lineNumber == lineNumber 
                && pn.prefix == prefix 
                && pn.areaCode == areaCode;
    }
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }
        return result;
    }
}
/*  При написании хэш-функции важно найти правильный баланс между частотой 
коллизий и скоростью вычисления. Нужно всегда помнить о том, какое огромное
влияние на скорость работы программы оказывает деградация коллекций до линейных 
списков в случае плохо написанной хэш-функции.*/

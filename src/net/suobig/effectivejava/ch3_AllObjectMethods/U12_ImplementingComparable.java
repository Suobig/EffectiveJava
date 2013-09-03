package net.suobig.effectivejava.ch3_AllObjectMethods;

import java.util.*;

/*  Метода compareTo не объявлен в Object. Это единственный метод интерфейса
Comparable. Реализация данного интерфейса позволит не только сравнить объекты
как equals, но и отранжировать их. Это подразумевает, что экземпляры класса
можно упорядочить. А значит, содержащие их коллеции просто сортировать
 * Arrays.sort(a);
а также просто искать, вчислять экстремумы, добавлять в автоматически сорируемые
коллекции и т.д. 

    Напримем, пользуясь тем, что String implements Comparable можно создать
такой класс:*/

class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);
    }
}

/*  Контракт compareTo в целом соответствует контракту equals:
    1) метод ранжурует данный объект в сравнении с другим объектом
    2) возвращает отрицательный int, 0, положительный int, если сравниваемый
объект, соответственно, меньше, равен или больше переданного как аргумент.
Бросает ClassCastException, если эти объекты нельзя сравнить.

    (В идущей ниже записи функция sgn является эквивалентом signum, возвращет
1, 0, -1 в зависимости от знака аргумента)
    3) для любых x, y: sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
(следствие: x.compareTo(y) выбрасывает исключение тогда и только тогда, когда
y.compareTo(x) выбрасывает исключение)
    4) для любых x, y, z: если (x.compareTo(y) > 0 && y.compareTo(z) > 0), то
x.comareTo(z) > 0.
    5) для любых x, y, z: если x.compareTo(y) == 0 то
sgn(x.compareTo(z)) == sgn(y.compareTo(z))
    6) строго рекомендуемо, хотя и не обязаельно: для любых x, y:
x.compareTo(y) == 0 тогда и только тогда, когда x.equals(y) Если данное условие
не соблюдается, нужно указать это в комментарии.

    Внутри одного класса любое разумное с точки зрение человеческой логики
ранжирование объектов почти навреняка будет удовлетворять всем этим условиям
А между классами compareTo не обязан работать и может просто выбрасывать 
ClassCastException. Во всех стандартных библиотеках методы compareTo именно так
и поступают.

    Для compareTo работает то же ограничение, что и для equals - нельзя 
создать наследника, добавить к нему значимое поле и затем корректно сравнить
наследника с родителем. 

    Последнее скорее предположение, чем условие, говорит о том, что результат
compareTo согласуется с результатом equals. Если это так, метод называется
совместимым с equals, иначе - несовместимым. Последнее допустима, хотя может
привести к некорректной работе некоторых коллекций.

    Например, если у нас есть 2 объекта: BigDecimal a = new BigDecimal(1.0) и 
BigDecimal b = new BigDecimal(1.00), то в коллекции HashSet это будет 2 записи 
(так как a.equals(b) == false), а в TreeSet - одна (так как a.compareTo(b) = 0)

    Пример реализации метода compareTo:*/

class CaseInsensitiveStringComparable 
        implements Comparable<CaseInsensitiveStringComparable> {
    String s;
    
    @Override
    public int compareTo(CaseInsensitiveStringComparable cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
    // Remainder omitted
}

/*  Обратите внимание, что класс реализует интерфейс 
Comparable<CaseInsensitiveStringComparable>. Это означает, что в качестве 
арумента можно передать только объект этого же класса. 

    Правила сравнения:
    1. Для int: операторы сравнения >, <
    2. для double и float: Double.compare, Float.compare
    3. для Array: сравнивайте поэлементно
    
    Если у класса несколько полей для сравнения - критически важен порядок. 
Сравнивайте от самых важных полей к менее важным. Например, для телефонного
номера:*/

final class PhoneNumberComparable 
        implements Comparable<PhoneNumberComparable>{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    private volatile int hashCode;
    
    public PhoneNumberComparable(int areaCode, int prefix, int lineNumber) {
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
    
    public int compareToLong (PhoneNumberComparable pn) {
        if (areaCode < pn.areaCode)
            return -1;
        if (areaCode > pn.areaCode)
            return 1;
        if (prefix < pn.prefix) 
            return -1;
        if (prefix > pn.prefix) 
            return 1;
        if (lineNumber < pn.lineNumber) 
            return -1;
        if (lineNumber > pn.lineNumber) 
            return 1;
        return 0;
    }
    /*  Этот метод правильно работает, но можно написать более лаконично, если
вспомнить, что требуется лишь знак возвращаемого значения, а его величина не 
важна:*/
    public int compareTo (PhoneNumberComparable pn) {
        int areaCodeDiff = areaCode - pn.areaCode; 
        if (areaCodeDiff != 0)
            return areaCodeDiff;
        
        int prefixDiff = prefix - pn.prefix;
        if (prefixDiff != 0) 
            return prefixDiff;
        
        return lineNumber - pn.lineNumber;
    }
    /*  Однако, данный трюк надо использовать с большой осторожностью: если
вдруг разность превысит Integer.MAX_VALUE, результат вычислений будет 
неправильным, а ошибку будет невероятно трудно обнаружить.*/
}
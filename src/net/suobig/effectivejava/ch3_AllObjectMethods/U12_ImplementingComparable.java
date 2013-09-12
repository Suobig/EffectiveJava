package net.suobig.effectivejava.ch3_AllObjectMethods;

import java.util.*;
import java.text.SimpleDateFormat;

/*  Метод compareTo не объявлен в Object, он является частью интерфейса
Comparable. Реализация данного интерфейса позволяет не только сравнить объекты
как это делает метод equals(), но и отранжировать, упорядочить их.  А значит, 
коллекции, которые содержат методы, можно сортировать. Например так:
* Arrays.sort(a);
Также для экземляров такого класса просто искать, вчислять экстремумы, 
добавлять в автоматически сорируемые коллекции и т.д. 

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
ранжирование объектов почти наверняка будет удовлетворять всем этим условиям
А между классами compareTo не обязан работать и может просто выбрасывать 
ClassCastException. Во всех стандартных библиотеках методы compareTo именно так
и поступают.

    Для compareTo работает то же ограничение, что и для equals - нельзя 
создать класс-наследник с дополнительным значащим полем, и затем корректно 
сравнить наследника с родителем. 

    Пункт (6), является скорее рекомендацией, чем условием. Если он выполнен, 
метод называется совместимым с equals, иначе - несовместимым. Последнее 
допустимо, хотя может привести к некорректной работе некоторых коллекций.

    Например, это условие не выполняется для класс BigDecimal. И, если у вас 
есть два объекта:
    * BigDecimal a = new BigDecimal(1.0);
    * BigDecimal b = new BigDecimal(1.00);
то в коллекции HashSet это будет 2 записи, так как a.equals(b) == false, 
* а в TreeSet - одна, так как a.compareTo(b) = 0

    Пример реализации метода compareTo:*/

class CaseInsensitiveStringComparable
        implements Comparable<CaseInsensitiveStringComparable> {
    String s;
    
    @Override
    public int compareTo(CaseInsensitiveStringComparable cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
    // Остальное опущено
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
    @Override
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


class BookPage {
    private String text;
    private BookPage next;
    
    public String getText() {
        return text;
    }
    
    public BookPage getNext() {
        return next;
    }
    
    public BookPage(String text) {
        this.text = text;
    }
    
    public void add(String text) {
        BookPage temp = this.next;
        this.next = new BookPage(text);
        this.next.next = temp;
    }
    
    public void addLast(String text) {
        BookPage currentPage = this;
        
        while(currentPage.next != null) 
            currentPage = currentPage.next;
        currentPage.next = new BookPage(text);
    }
}

class Main {
    public static void main(String[] args) {
        BookPage book = new BookPage("Page1");
        book.addLast("Page2");
        book.addLast("Page3");
        book.add("Page1.5");
        System.out.println(book.getText());
        System.out.println(book.getNext().getText());
        System.out.println(book.getNext().getNext().getText());
        System.out.println(book.getNext().getNext().getNext().getText());
    }
}


class MyClass implements Comparable<MyClass> { 
    private String name;
    private int price;
    private double discount;
    private Calendar date;
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public Calendar getDate() {
        return date;
    }
    
    public MyClass(String name, int price, double discount, Calendar date) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }
        
    @Override
    public int compareTo(MyClass that) {
        int dtCompare = this.date.compareTo(that.date);
        if (dtCompare != 0) 
            return dtCompare;
        
        int nameCompare = this.name.compareTo(that.name);
        if (nameCompare != 0) 
            return nameCompare;
        
        int priceCompare = Double.compare(this.price, that.price);
        if (priceCompare != 0) 
            return priceCompare;
        
        return Double.compare(this.discount, that.discount);
    }
    
    @Override
    public String toString() {
        return name + ", " + price + ", " + discount + ", " + 
               new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());
    }
}
 
class Tester {
    public static void main(String[] args) {
        Calendar dt1 = Calendar.getInstance();
        dt1.set(2013, Calendar.JUNE, 8);
 
        Calendar dt2 = Calendar.getInstance();
        dt2.set(2013, Calendar.JUNE, 1);
 
        MyClass a = new MyClass("a", 100, 0., dt1);
        MyClass b = new MyClass("b", 200, 0., dt2);   
        MyClass c = new MyClass("c", 50, 0., dt2);   
        MyClass[] arr = new MyClass[3];
        
               
        arr[0] = a;
        arr[1] = b;
        arr[2] = c;
        
        MyClassComparator comparator = new MyClassComparator();
        comparator.setCompareMethod(MyClassCompareMethods.BY_NAME);
        
        Arrays.sort(arr, comparator);
        System.out.println("Compare by Name:");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        
        comparator.setCompareMethod(MyClassCompareMethods.BY_DATE);
        
        Arrays.sort(arr, comparator);
        System.out.println("Compare by Date:");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        
        
        comparator.setCompareMethod(MyClassCompareMethods.BY_PRICE);
        
        Arrays.sort(arr, comparator);
        System.out.println("Compare by Price:");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
    }
}
 
enum MyClassCompareMethods {
    BY_NAME,
    BY_PRICE,
    BY_DISCOUNT,
    BY_DATE;
}
 
class MyClassComparator implements Comparator<MyClass> {
    private MyClassCompareMethods compareMethod;
    
    public void setCompareMethod(MyClassCompareMethods method) {
        compareMethod = method;
    }
    
    @Override
    public int compare(MyClass o1, MyClass o2) {
        switch (compareMethod) {
            case BY_NAME:
                int nameDiff = o1.getName().compareTo(o2.getName());
                if (nameDiff != 0) 
                    return nameDiff;
                break;
            case BY_PRICE: 
                int priceDiff = Double.compare(o1.getPrice(), o2.getPrice());
                if (priceDiff != 0) 
                    return priceDiff;
                break;
            case BY_DISCOUNT:
                int discountDiff = Double.compare(
                        o1.getDiscount(), o2.getDiscount());
                if (discountDiff != 0) 
                    return discountDiff;
            case BY_DATE:
                int dateDiff = o1.getDate().compareTo(o2.getDate());
        }
        //Если приоритетное сравнение вернуло 0 - сравниваем по умолчанию
        return o1.compareTo(o2);
    }    
}
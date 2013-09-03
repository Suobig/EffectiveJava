/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.ch3_AllObjectMethods;

import java.util.Arrays;
import java.util.EmptyStackException;

/*  Не смотря на то, что в java.util существует интерфейс Cloneable, он
лишь сообщает, что объект можно клонировать, однако он не предоставляет метода
clone для этого. Таким образом, не существует гарантии, что у объекта с 
Cloneable интерфейсом будет метод clone. 

Контракт метода clone предполагает следующее:
    1) x.clone() != x;
    2) x.clone().getClass() == x.getClass();
    3) x.clone().equals(x);
    Однако ни один из этих пунктов не является обязательным. При клонировании 
объекта создается новый экземпляр класса, также может потребоваться копирование 
всей структуры данных. Конструктор при этом не вызывается.
    
    В этом контракте есть целый ряд проблем:
    1. Требование "Конструктор не вызывается" является слишком сильным. В 
некоторых случаях вызов конструктора является наилучшим способом клонирования 
объекта (например, если класс объявляен как final)
    2. Рекомендация x.clone().getClass() == x.getClass() вообще является лишней:
если программист пишет в классе X super.clone(), то он ожидает, что ему вернется
экземпляр класса X, т.к. по цепочке вызовов super.clone() дойдет до Object и там
создаст копию нужного объекта класса X. Т.о. 
super.clone().getClass() != super.getClass() 

    Простейшей реализацией метода clone() является вызов super.clone(). 
Например:*/
    
final class PhoneNumberCloneable implements Cloneable {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumberCloneable(int areaCode, int prefix, int lineNumber) {
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
    public PhoneNumberCloneable clone() {
        try {
            return (PhoneNumberCloneable) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError(); //Невозможно
        }
    }
    
    public static void main(String args[]) {
       PhoneNumberCloneable number = new PhoneNumberCloneable(
               100, 200, 1245);
        System.out.println(number.clone().getClass());
    }
}

/*  Стоит обратить внимание, что метод clone возвращает правильный тип, а не 
 Object. Это называется ковариантность типов возвращаемых значений - при 
 переопределении метода тип возвращаемого значения может быть не только
 таким, как указано в переопределяемом методе, но и любым из его наследников 
 (при условии, что они удовлетворяют принципу подстановки Б.Лисков)
 
    Можно, конечно, возвращать Object, но тогда каждому клиенту, который будет
пользоваться методом clone() делать преобразование типа самостоятельно. 
Общее правило - никогда не заставляйте клиента делать то, что библиотека может
сделать за него сама.

    Если какие-то из полей объекта являются ссылками на изменяемые объекты
то, простой вызов super.clone() и возврат полученного объекта приведут к
крайте нежелательным результатам. Дело в том, что Object.clone() скопирует
ссылку и оба клона будут иметь ссылку на один и тот же объект. Например:*/

class StackBadCloning {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public StackBadCloning() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    // Ensure space for at least one more element.
    private void ensureCapacity() {
        if (elements.length == size)
        elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    
    @Override 
    public StackBadCloning clone() {
        try {
            StackBadCloning result = (StackBadCloning) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

/*  В результате метода clone оба клона будут ссылаться на один и тот же объект
elements. Это неизбежно приведет к ошибкам. Самый простой способ - рекурсивно
клонировать все поля, содержащие ссылки на изменяемые объекты:*/

class StackGoodCloning {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public StackGoodCloning() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    // Ensure space for at least one more element.
    private void ensureCapacity() {
        if (elements.length == size)
        elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    
    @Override 
    public StackGoodCloning clone() {
        try {
            StackGoodCloning result = (StackGoodCloning) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
/*  Обратите внимание: нет необходимости менять тип возвращаемого значения 
 у elements.clone() - при клонировании массива возвращается массив того же 
 типа
 
    Если вы хотите клонировать массив, содержащий изменяемые объекты, простого 
вызова метода clone() будет недостаточно - клонированный массив будет содержать
ссылки на те же самые объекты. Для того, чтобы правильно клонировать массив
необходимо создать новый массив и заполнить его клонами объектов исходного
массива. Отдельные проблемы может вызвать клонирование списка: если делать это 
методом Object.clone() - получится клонирование только клон первого элемента
списка, а уже второй элемент будет у обоих списков общий (склонируется ссылка
на него, но не он сам.*/

class HashTableBadClone implements Cloneable {
    private Entry[] buckets = new Entry[100];
    
    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
// Оставшееся опущено
    @Override 
    public HashTableBadClone clone() {
        try {
            HashTableBadClone result = (HashTableBadClone) super.clone();
            result.buckets = buckets.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

/*  Если мы реализуем clone подобным образом - клонирование массива Entry 
 даст только создание массива со ссылками на те же объекты. Правильно 
 реализованный метод clone:*/

class HashTableGoodCloneRecursive implements Cloneable {
    private Entry[] buckets = new Entry[100];
    
    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        //Рекурсивно создаем копии всех элементов списка
        Entry deepCopy() {
            return new Entry(key, value,
                    next == null ? null : next.deepCopy());
        }
    }
    // Оставшееся опущено
    
    @Override 
    public HashTableGoodCloneRecursive clone() {
        try {
            HashTableGoodCloneRecursive result = (HashTableGoodCloneRecursive) 
                    super.clone();
            result.buckets = new Entry[buckets.length];
            //Обходим массив, корректно копируя всех списки
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); //Невоможно
        }
    }
}

/*  Проблема данной реализации - в рекурсии. Она выглядит красиво и при 
 определенных условиях замечательно работает. Проблема, однако, в том, что 
 мы не контролируем длину списка, а значит - при достаточно большом списке - 
 можем получить переполнение стека вызвов. Наилучший вариант в данном случае -
 использовать итерации:*/

class HashTableGoodCloneIterative implements Cloneable {
    private Entry[] buckets = new Entry[100];
    
    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        //Итеративно создаем копии всех элементов списка
        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            
            for (Entry p = result; p.next != null; p = p.next) 
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            
            return result;
        }
    }
    // Оставшееся опущено
    
    @Override 
    public HashTableGoodCloneIterative clone() {
        try {
            HashTableGoodCloneIterative result = (HashTableGoodCloneIterative) super.clone();
            result.buckets = new Entry[buckets.length];
            //Обходим массив, корректно копируя всех списки
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); //Невоможно
        }
    }
}

/*TODO: добавить абзац про вызов нефинальных методов после прочтения главы 17.
Пока непонятно что это такое.*/

/*  Метод Object.clone() бросает CloneNotSupportedException, однако, при 
 переоределении метода повтор этого объявления необязателен. А для публичных 
 методов даже нежелателен - удобнее пользоваться методами, который не бросают
 исключений.*/
//TODO: дополнить после прочтения параграфа 59

/*  Если класс создан только для того, чтобы от него наследовать, его метод
 clone должен быть объявлен так же как и в Оbject: он должен быть объявлен как
 protected и бросать CloneNotSupportedException и при этом класс не дожен 
 реализовывать интерфейс Clonable. Это оставит наследникам свободу выбора и 
 будет выглядеть так, как будто они наследуют метод напрямую из Object. 
 
    Если вы хотите сделать потокобезопасный класс, вы должны объявить метод 
clone как synchronized. Надо помнить, что метод clone у Object не является 
синхронизированным. Т.о. даже если вас устраивает его поведение, все равно
придется переопределить и сделать синхронизированным.

    Суммируя. Если вы хотите реализовать интрефейс Cloneable:
    1) переопределите метод clone() предка
    2) первым делом объявите super.clone()
    3) замените ссылки на объекты ссылками на клоны, если объекты изменяемые
    4) клонируйте структуры данных в соответствии с логикой внутреннего 
устройства этих структур
    5) при клонировании структур предпочитайте итеративный подход рекурсивному
    6) меняйте поля, которые содержат уникальные характеристики экземпляра (
Id, время создания и т.д.)

    Как часто используется метод clone? Нечасто. Обычно предпочитают более 
простые подходы. Например коприующий конструктор (public Foo(Foo bar) {...};)
или копирующую фабрику (public static Foo newInstance(Foo bar) {...};)

    Большиство программистов стараются во всех возможных случаях не 
переопределять clone() и не реализовывать интерфейс Cloneable*/


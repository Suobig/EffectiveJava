/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.ch2_СreateAndDelete;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *
 * @author popov
 */
/*  Наличие в Java автоматического сборщика мусора может создать ошибочное 
 впечатление, что теперь вообще не нужно задумываться об управлении памятью.
 Например, такая реализация стека: */
class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
    
/*  Проблема заключена в методе pop() - уменьшая размер стека, он не удаляет
ссылки на объекты, которые там находились. Это утечка памяти, которая может 
привести к переполнению. 

    Для того, чтобы исправить проблему, достаточно удалить ссылки на объекты, 
которые мы больше не используем:*/ 
    public Object popFixed() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; //Удаляем ненужные ссылки, чтобы GC убрал объекты
        return result;
    }
/*  При этом не стоит увлекаться занулением каждой ссылки на объект, который
 больше не испльзуется. Зануление ссылок - это скорее исключение, чем правило.
 
    Все лишние ссылки сами занулятся в момент, когда закончится область 
видимости хранящей их переменной. Поэтому лучший способ борьбы с мусором - 
объявлять переменные с минимально возможной областью видимости.

    В чем проблема у нашей реализации стека? В том, что он сам управляет своей
памятью через переменную size. Для сборщика мусора все, что находится в массиве
elements одинаково валидно вне зависимости от переменной size. Поэтому общее 
* правило таково: если класс сам управляет своей памятью, то он должен сам
заботиться о ее своевременной очитке.
    
    Вторым распространенным источником утечек является кэш. Он должен 
периодически очищаться от объектов, которые больше не используются при помощи
отдельного фонового потока.

    Третий источник утечек - listner'ы и callback'и. Если пользователь 
регистрирует Listener в вашем API - у вас должен быть механизм проверки
продолжает ли этот Listener использоваться и не требуется ли его удалить.
Самым простым способом является использование WeakHashMap.*/
}

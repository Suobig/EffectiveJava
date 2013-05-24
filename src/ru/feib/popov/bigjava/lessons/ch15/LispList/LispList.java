/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch15.LispList;

/**
 *
 * @author popov
 */
public interface LispList {
    boolean isEmpty();
    Object head();
    LispList tail();
    int length();
    NonEmptyList cons(Object listHead);
    LispList merge(LispList other);
}


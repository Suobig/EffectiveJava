/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch20.greeting;

/**
 *
 * @author popov
 */
public class GreetingThreadRunner {
    public void go() {
        GreetingRunnable r1 = new GreetingRunnable("Hello, world!");
        GreetingRunnable r2 = new GreetingRunnable("Goodbye, world!");
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}

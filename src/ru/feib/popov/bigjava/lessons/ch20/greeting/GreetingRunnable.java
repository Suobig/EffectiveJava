/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch20.greeting;

import java.util.Date;

/**
 * A runnable that repeatedly prints a greeting
 * @author popov
 */
public class GreetingRunnable implements Runnable {
    private static final int REPETITIONS = 10;
    private static final int DELAY = 1000;
    
    private String mGreeting;
    
    public GreetingRunnable(String greeting) {
        mGreeting = greeting;
    }
    
    @Override
    public void run() {
        try { 
            for (int i = 1; i <= REPETITIONS; i++) {
                Date now = new Date();
                System.out.println(now + " " + mGreeting);
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException exception) {}
    }
}

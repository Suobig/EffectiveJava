/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

/**
 *
 * @author popov
 */
import java.util.ArrayList;
 
public class TestClass {
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> done = new ArrayList<>();
 
    public TestClass() {
        // заполняем
        for (int i = 0; i < 1000; i++) {
             double value = 100 * Math.random();
            array.add(Double.toString(value));
        }
        // обрабатываем
        QueryThread thread1 = new QueryThread();
        QueryThread thread2 = new QueryThread();
        QueryThread thread3 = new QueryThread();
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public class QueryThread extends Thread {
        public QueryThread() {
            super();
        }
        
        @Override
        public void run() {
            while (!array.isEmpty()) {
                String temp = array.get(array.size() - 1);
                array.remove(array.size() - 1);
                boolean exists = false;
                for (int i = 0; i < done.size(); i++) {
                    String temp2 = temp.substring(0, temp.indexOf("."));
                    if (done.get(i).startsWith(temp2)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    done.add(temp);
                }
            }
        }
    }
}

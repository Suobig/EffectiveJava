/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

/**
 *
 * @author popov
 */
public class JavaApplication
{
    public static int bar = 3;
    
    static class Car
    {
        protected int number;
        public Car(){};
        public void print()
        {
            System.out.println(number);
        };
        public void get_var(int var)
        {
            number = var;
        };
        
    };
    public static void foo()
    {
        Car vehicle = new Car();    
        vehicle.number = 3;
        vehicle.print();
        Car vehicle2 = new Car();
        vehicle2.number = 4;
        vehicle2.print();
    };
}
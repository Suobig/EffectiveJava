/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.databaseTester;

/**
 *
 * @author popov
 */
public class Department {
    public int id;
    public String name;
    
    public Department(int aId, String aName) {
        id = aId;
        name = aName;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

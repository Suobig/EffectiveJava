/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch09.p1;

/**
 *
 * @author popov
 */
public class DieSetBuilder {
    private static int DIE_SIDES = 6;
    
    public void build() {
        DieMeasurer measurer = new DieMeasurer();
        
        DataSet diesSet = new DataSet(measurer);

        for (int i = 1; i < 10; i++) {
            diesSet.add(new Die(DIE_SIDES));        
        }
    }
}

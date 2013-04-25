/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch09.p1;

/**
 *
 * @author popov
 */
public class DieMeasurer implements Measurer {
    /**
     * get die face
     * @param x die object
     * @return face 
     */
    @Override
    public int measure(Object x) {
        Die d = (Die)x;
        return d.showFace();
    }
}

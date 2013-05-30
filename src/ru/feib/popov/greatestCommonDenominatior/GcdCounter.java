/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.greatestCommonDenominatior;

import javax.swing.JFrame;
import javax.swing.JFrame.*;

/**
 *
 * @author popov
 */
public class GcdCounter {
    public static int gibbyGcd(int val1, int val2) {
        int a, b, r;
        int absV1 = Math.abs(val1);
        int absV2 = Math.abs(val2);
                
        if (absV1 > absV2) {
            a = absV1;
            b = absV2;
        } else {
            b = absV1;
            a = absV2;
        }
        
        if (b == 0) return 0;        
        
        do {
            r = a % b;
            a = b;
            b = r;
        } while (r != 0);
        
        return a;        
    }
    
    public static int animatorGcd(int fi, int si) {
        int min;
        int nod = 0;
        if (fi > si) {
            min = si;
        } else {
            min = fi;
        }
        for (int count = 1; count <= min; count++) {
            if (fi % count == 0 && si % count == 0) {
                if (count > nod) {
                    nod = count;
                }
            }
        }
        JFrame frame = new JFrame();
        return nod;
    }
}
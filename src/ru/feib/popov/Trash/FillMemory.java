/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.util.ArrayList;

/**
 *
 * @author popov
 */
public class FillMemory {
    public static void run() {
        final int ARRAY_SIZE = 568;
        final int ITER_COUNT = 100;
        ArrayList<long[][]> matrixList = new ArrayList<>();        
        
        for (int i = 0; i < ITER_COUNT; i++) {
            long[][] matrix = new long[ARRAY_SIZE][ARRAY_SIZE];
            fillMatrix(matrix);
            matrixList.add(matrix);
        }
    }
    
    public static void fillMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            for (long element: row ) {
                element = Long.MAX_VALUE;
            }
        }
    }
}

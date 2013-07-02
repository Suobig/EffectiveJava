/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private ArrayList<ArrayList<Integer>> rows;
    private int mRowCount, mColumnCount;
    private int mPrintSize = 0;
    
    /**
     * Initialize matrix with 0 values
     * Initialize mRowCount and mColumnCount variables
     * @param rowCount
     * @param columnCount 
     */
    public Matrix(int rowCount, int columnCount) {
        rows = new ArrayList<>();
        mRowCount = rowCount;
        mColumnCount = columnCount;
        
        for (int i = 0; i < rowCount; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                row.add(0);
            }
            rows.add(row);
        }
    }
    
    /**
     * Fill Matrix with random values 
     * @param from random values from
     * @param to random values to
     */
    public void fillRandom(int from, int to) {
        mPrintSize = (int)Math.log10(to) + 1;
        
        Random randomizer = new Random();
        randomizer.setSeed(System.nanoTime());
                
        for (int i = 0; i < mRowCount; i++) {
            ArrayList<Integer> row = rows.get(i);
            for (int j = 0; j < mColumnCount; j++) {
                //TODO: update null
                int randomInt = randomizer.nextInt(to - from) + from;
                row.set(j, randomInt);
            }
        }
    }  
    
    /**
     * Get matrix element
     * @param i row
     * @param j column
     * @return element value
     * @throws ArrayIndexOutOfBoundsException 
     */
    public int getValue(int i, int j) throws ArrayIndexOutOfBoundsException {
        if (i > mRowCount || j > mColumnCount || i < 0 || j < 0) 
            throw new ArrayIndexOutOfBoundsException();
        
        return rows.get(i).get(j);
    }
    
    public void swapElemenets(int i1, int j1, int i2, int j2) 
            throws ArrayIndexOutOfBoundsException {
        if (i1 > mRowCount || 
                i2 > mRowCount ||
                j1 > mColumnCount ||
                j2 > mColumnCount ||
                i1 < 0 ||
                i2 < 0 ||
                j1 < 0 ||
                j2 < 0) 
            throw new ArrayIndexOutOfBoundsException();
        
        int val1 = rows.get(i1).get(j1);
        int val2 = rows.get(i2).get(j2);
        rows.get(i1).set(i1, val2);
        rows.get(i2).set(i2, val1);
    }
    
    @Override
    public String toString() {
        String print = "";
        
        for (int i = 0; i < mRowCount; i++) {
            for (int val : rows.get(i)) {
                print += String.format("%-" + mPrintSize + "d", val);
            }
            print += "\n";
        }
        Boolean var = null;        
        return print;
    }
    
    
   
    
}

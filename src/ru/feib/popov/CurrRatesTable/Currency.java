/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

/**
 *
 * @author popov
 */
public final class Currency {
    private int mNumber;
    private String mCode;
    private int mMeasure;
    private String mName;
    private double mRate;
    
    public Currency(
            int number, 
            String code, 
            int measure, 
            String name, 
            double rate) {
        mNumber = number;
        mCode = code;
        mMeasure = measure;
        mName = name;
        mRate = rate;
    }
    
    public Currency(int number, double rate) {
        mNumber = number;
        mRate = rate;
    }
    
    @Override
    public String toString() {
        return mNumber + ": " + mRate;
    }
    
    public int getNumber() {
        return mNumber;
    }
    
    public double getRate() {
        return mRate;
    }
}

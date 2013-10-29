/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

import java.util.Date;

/**
 *
 * @author popov
 */
public final class Currency {
    private Date date;
    private int number;
    private String code;
    private int measure;
    private String name;
    private double rate;
    
    public Currency(
            Date aDate,
            int aNumber, 
            String aCode, 
            int aMeasure, 
            String aName, 
            double aRate) {
        date = aDate;
        number = aNumber;
        code = aCode;
        measure = aMeasure;
        name = aName;
        rate = aRate;
    }
    
    public Currency(Date aDate, int aNumber, double aRate) {
        date = aDate;
        number = aNumber;
        rate = aRate;
    }
    
    @Override
    public String toString() {
        final String DATE_PATTERN = "%1$td.%1$tm.%1$tY";
        return String.format(DATE_PATTERN, date) + ": " + number + ": " + rate;
    }
    
    public Date getDate()  {
        return date;
    }
    
    public int getNumber() {
        return number;
    }
    
    public double getRate() {
        return rate;
    }
}

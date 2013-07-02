/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

import java.util.ArrayList;

/**
 *
 * @author popov
 */
public class CurrRateList {
    private  ArrayList<Currency> list;
    
    public CurrRateList() {
        list = new ArrayList<>();
    }
    
    public void add(Currency item) {
        list.add(item);
    }
    
    public int getSize() {
        return list.size();
    }
    
    public Currency getItem(int i) {
        return list.get(i);
    }
    
    @Override
    public String toString() {
        String  print = "";
        
        for (int i = 0; i < list.size(); i++) 
            print += ((i == 0) ? "" : "\n") + 
                    list.get(i);
        return print;
    }
}

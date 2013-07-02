/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.util.HashMap;
import java.util.Map.Entry;

public class VowelSet {
    private HashMap<Character, Boolean> vowels;
    
    public VowelSet() {
        vowels = new HashMap<>();
        vowels.put('a', false);
        vowels.put('e', false);
        vowels.put('i', false);
        vowels.put('o', false);
        vowels.put('u', false);
        vowels.put('y', false);
    }
    
    public VowelSet(String toParse) {
        this();
        this.parseString(toParse);
    }    
    
    public void reset() {
        for (Entry e : vowels.entrySet()){
            e.setValue(false);
        }
    }
    
    public final void parseString(String toParse) {
        for (char c : toParse.toCharArray()) {
            if (vowels.containsKey(c)) {
                boolean isMet = vowels.get(c);
                if (!isMet)
                    vowels.put(c, true);
            }
        }
    }
    
    public String getMet() {
        String toPrint = "";
        for (Entry e : vowels.entrySet()) {
            boolean isMet = (boolean)e.getValue();
            if (isMet) 
                toPrint += ((toPrint.isEmpty()) ? "" : ", ") + e.getKey();
        }        
        return toPrint;
    }
    
    public String getNotMet() {
        String toPrint = "";
        for (Entry e : vowels.entrySet()) {
            boolean isMet = (boolean)e.getValue();
            if (!isMet) 
                toPrint += ((toPrint.isEmpty()) ? "" : ", ") + e.getKey();
        }        
        return toPrint;
    }
    
    public String getAll() {
        String toPrint = "";
        for (Entry e : vowels.entrySet()) {
            toPrint += ((toPrint.isEmpty()) ? "" : ", ") + e.getKey();
        }     
        return toPrint;
    }
}

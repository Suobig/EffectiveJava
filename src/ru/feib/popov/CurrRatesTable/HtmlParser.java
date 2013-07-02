/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author popov
 */
public class HtmlParser {
    private  CurrRateList list;
    
    public CurrRateList parceHTML(String path) {
        Document doc = null;
        
        final String ATTRIBUTE = "CBRTBL";
        final String TABLE_ROWS_TAG = "tr";
        final String TABLE_DATA_TAGs = "td";
        
        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Elements tableRows = doc.getElementsByClass(ATTRIBUTE)
                .select(TABLE_ROWS_TAG);
        
        parceTableHtml(tableRows);
        return list;
    }
    
    private void parceTableHtml(Elements table) {
        final String TABLE_DATA_TAG = "td";
        int code;
        double rate;
        
        list = new CurrRateList();
        //TODO: fill List
        Iterator<Element> iterator = table.iterator();
        
        //Skip heading
        if (iterator.hasNext()) iterator.next();
        
        while (iterator.hasNext()) {  
            Element tableRow = iterator.next();
            Elements row = tableRow.select(TABLE_DATA_TAG);
            
            code = Integer.parseInt(row.get(0).text());
            rate = Double.parseDouble(row.get(4).text().replace(",", "."));
            
            if (code != 0) {
                list.add(new Currency(code, rate));
            }
        }
    }   
}

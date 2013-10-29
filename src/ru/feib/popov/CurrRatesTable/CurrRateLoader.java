/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author popov
 */
public class CurrRateLoader {
    
    public static CurrRateList getCurrencyList(Date loadDate) 
            throws IOException {       
        final String URL_PATTERN = 
                "http://cbr.ru/currency_base/D_print.aspx?date_req=%1$td.%1$tm.%1$tY";
        final String ATTRIBUTE = "CBRTBL";
        final String TABLE_ROWS_TAG = "tr";
        final String TABLE_DATA_TAG = "td";
        
        int code;
        double rate;
        
        String path = String.format(URL_PATTERN, loadDate);
        
        Document doc = Jsoup.connect(path).get();
        
        Elements tableRows = doc.getElementsByClass(ATTRIBUTE)
                .select(TABLE_ROWS_TAG);
        
        CurrRateList list = new CurrRateList();
        Iterator<Element> iterator = tableRows.iterator();
        
        //Skip heading
        if (iterator.hasNext()) iterator.next();
        
        while (iterator.hasNext()) {  
            Element tableRow = iterator.next();
            Elements row = tableRow.select(TABLE_DATA_TAG);
            
            code = Integer.parseInt(row.get(0).text());
            rate = Double.parseDouble(row.get(4).text().replace(",", "."));
            
            if (code != 0) {
                list.add(new Currency(loadDate, code, rate));
            }
        }
        return list;
    }
    
    public static Currency getGoldRate(Date loadDate) throws IOException {       
        final String DATE_PATTERN = "%1$td.%1$tm.%1$tY";
        final String URL_PATTERN = 
                "http://cbr.ru/metall_base/New_dynamics.asp?" +
                "r1=0&date_req1=" + DATE_PATTERN + 
                "&date_req2=" + DATE_PATTERN + "&m1=1";
        final int GOLD_CODE = 998;
        String path = String.format(URL_PATTERN, loadDate);
        Document doc = Jsoup.connect(path).get();
        
        Elements dateElements = doc.getElementsMatchingText(
                String.format(DATE_PATTERN,loadDate));
        //We assume, that last date on this page is met in a table cell
        Element dateTableCell = dateElements.last();
        Element rateTableCell = dateTableCell.nextElementSibling();
        double goldRate = Double.parseDouble(rateTableCell.text()
                .replace(" ","")
                .replace(",", "."));
        Currency gold = new Currency(loadDate, GOLD_CODE, goldRate);        
        return gold;
    }
}

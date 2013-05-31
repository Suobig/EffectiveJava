/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author popov
 */
public class HtmlParser {
    public static void parceHTML(String path) {
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
        Iterator<Element> tableIterator = tableRows.iterator();
        
        while (tableIterator.hasNext()) {
            Element tableRow = tableIterator.next();
            tableRow.select(TABLE_DATA_TAGs);
        }
        
//        Elements elem = doc.getElementsByTag("tr");
//        for (Element item : elements) {
//            System.out.println(item.toString());
//        }
    }
        
}

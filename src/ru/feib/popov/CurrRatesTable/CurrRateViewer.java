/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

/**
 *
 * @author popov
 */
public final class CurrRateViewer {
    public static void run(String url) {
        HtmlParser parser = new HtmlParser();
        CurrRateList list = parser.parceHTML(url);
        CurrRateFrame tableViewer = new CurrRateFrame(list);
        tableViewer.view();
    }
}

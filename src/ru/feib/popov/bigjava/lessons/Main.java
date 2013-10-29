/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.feib.popov.CurrRatesTable.CurrRateList;
import ru.feib.popov.CurrRatesTable.CurrRateLoader;
import ru.feib.popov.Trash.MacBookAir;
import ru.feib.popov.Trash.Notebook;
import ru.feib.popov.Trash.*;

/**
 * Main class
 * @author popov
 */
public class Main {
    /**
     * main module
     * @param args 
     */
    public static void main(String args[]) {
        FillMemory.run();
//        try {
//            Connection oraConn = new DbEngine().dbConnection();
//        } catch (Exception e) {
//            'e.printStackTrace();
//        }
//        Test test = new Test();
            //test.runButton();
            //test.runInvestmentViewer1();
    //        test.runInvestmentViewer2();
    //        test.runRectangleMover();
    //        test.runRectangleMouseMover();
    //        test.runDieSet();
    //        test.runQuizGradesSet();
    //        test.runQuizGradesSet2();
    //        test.runSequenceDemo();
    //        test.runAccountTester();
    //        test.runQuestionDemo();
    //        test.testFileDataForm();
    //        test.testLinkedList();
    //        test.testLinkedListBycicle();
    //        test.testLispList();
    //        test.testInvestmentViewer3();]
    //        test.testFontFrame();
    //        test.testColorViewer();
    //        test.testGreetingThreads();
    //        test.testClicker();
    //        test.testComboBox();
    //        test.testGcdCounter();
    //        test.testHtml();
    //        test.testFullScreen();
    //        test.testMatrix();
    //        test.testVowelSet();
    //        test.testCurrRateViewer();
    //        JavaApplication.foo();
//        try {
//            Date dtLoad =  new Date(System.currentTimeMillis());
//            CurrRateList list = CurrRateLoader.getCurrencyList(dtLoad);
//            list.add(CurrRateLoader.getGoldRate(dtLoad));
//            System.out.println(list);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Notebook nout = new MacBookAir();
        
        ((MacBookAir) nout).printAir();
    }
}



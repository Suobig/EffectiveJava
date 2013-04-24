/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

import java.sql.Connection;

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
//        try {
//            Connection oraConn = new DbEngine().dbConnection();
//        } catch (Exception e) {
//            'e.printStackTrace();
//        }
        Test test = new Test();
        //test.runButton();
        //test.runInvestmentViewer1();
//        test.runInvestmentViewer2();
        test.runRectangleMover();
    }
}



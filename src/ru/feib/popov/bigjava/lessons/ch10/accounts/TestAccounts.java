/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch10.accounts;

/**
 *
 * @author popov
 */
public class TestAccounts {
    
    public void test() {
        final double INTEREST_RATE = 0.1;
        final double START_AMOUNT = 10000.0;
    
        SavingsAccount acc = new SavingsAccount(INTEREST_RATE, START_AMOUNT);
        acc.addInterest();
        acc.withdraw(5000.0);
        acc.addInterest();
        System.out.println("Current balance = " + acc.getBalance());
        System.out.println("Expected: 6600");
    }
}

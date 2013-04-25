/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch09.InvestmentViewer;

import ru.feib.popov.bigjava.lessons.*;

/**
 * Class 
 * @author popov
 */
public class BankAccount {
    private double mValue = 0;
    //Counstructor with initial value
    public BankAccount(double initialValue) {
        mValue = initialValue;
    }
    //Counstructor without initial value
    public BankAccount() {
        mValue = 0;
    }
    
    /**
     * Deposit value to an account
     * @param value amount being deposited
     */
    public void deposit(double value) { 
        mValue += value;
    }
    
    /**
     * Withdraw value from an account
     * @param value amount being withdrawn
     */
    public void withdraw(double value) {
        mValue -= value;
    }
    
    /**
     * Get current balance
     * @return 
     */
    public double getBalance() {
        return mValue;
    }
}

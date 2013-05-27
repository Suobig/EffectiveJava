package ru.feib.popov.bigjava.lessons.ch18.InvestmentViewer;

/**
 * Superclass for SavingAccount and CheckingAccount classes
 * realizes basic bank account
 * @author popov
 */
public class BankAccount  {
    private double mAccountValue;
    
    public BankAccount() {
        mAccountValue = 0;
    }
    
    public BankAccount(int value) {
        mAccountValue = value;
    }
    /**
     * Deposit money to an account
     * @param value amount of money deposited
     */
    public void deposit(double value) {
        mAccountValue += value;
    }
    
    /**
     * Withdraw money from account
     * @param value amount of money withdrawn
     */
    public void withdraw(double value) {
        mAccountValue -= value;
    }
    
    /** 
     * @return current balance
     */
    public double getBalance() {
        return mAccountValue;
    }
}

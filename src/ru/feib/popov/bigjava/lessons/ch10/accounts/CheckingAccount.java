
package ru.feib.popov.bigjava.lessons.ch10.accounts;

/**
 * Class implements account with no interest rate
 * and fees on transactions
 * @author popov
 */
public class CheckingAccount extends BankAccount {
    public static final int UNCHARGEABLE_AMOUNT = 10000;
    public static final double TRANSACTION_FEE = 0.002;
        
    private int transactionCount;
    private double totalAmount;
    
    /**
     * Deposit money to checking account
     * @param amount 
     */
    @Override
    public void deposit(double value) {
        transactionCount++;
        
       totalAmount += value;
        super.deposit(value);
    }
    
    /**
     * withdraw money from checking account
     * @param amount 
     */
    @Override
    public void withdraw(double value) {
        transactionCount++;
        
        totalAmount += value;
        super.withdraw(value);
    }
    
    /**
     * Take fees for chargeable amount 
     */
    public void deductFees() {
        if (totalAmount > UNCHARGEABLE_AMOUNT) {
            double fee = (totalAmount - UNCHARGEABLE_AMOUNT) * TRANSACTION_FEE;
            super.withdraw(fee);
        }
        totalAmount = 0;
    }
}

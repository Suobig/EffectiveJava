
package ru.feib.popov.bigjava.lessons.ch10.accounts;

/**
 *
 * @author popov
 */
public class SavingsAccount extends BankAccount {
    private double mInterestRate;
    
    /**
     * Initialize account with certain rate
     * @param rate 
     */
    public SavingsAccount(double rate) {
        mInterestRate = rate;
    }
    
    /**
     * Add interest, based on current rate
     */
    public void addInterest() {
        double interest = this.getBalance() * mInterestRate;
        
        this.deposit(interest);
    }
    
    /**
     * @return current interest rate
     */
    public double getInterestRate() {
        return mInterestRate;
    }
}

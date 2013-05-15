
package ru.feib.popov.bigjava.lessons.ch10.accounts;

/**
 *
 * @author popov
 */
public class SavingsAccount extends BankAccount {
    private double mInterestRate;
    private double minimumBalance;
    
    @Override
    public void withdraw (double amount) {
        super.withdraw(amount);
        
        double newBalance = getBalance();
        if (minimumBalance > newBalance) minimumBalance = newBalance;
    }
    
    /**
     * Initialize account with certain rate
     * @param rate 
     */
    public SavingsAccount(double rate, double amount) {
        mInterestRate = rate;
        minimumBalance = amount;
        super.deposit(amount);
    }
    
    /**
     * Add interest, based on current rate
     */
    public void addInterest() {
        double interest = minimumBalance * mInterestRate;
        
        this.deposit(interest);
    }
    
    /**
     * @return current interest rate
     */
    public double getInterestRate() {
        return mInterestRate;
    }
    
}

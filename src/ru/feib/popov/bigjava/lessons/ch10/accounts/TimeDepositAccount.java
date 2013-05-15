/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch10.accounts;

/**
 *
 * @author popov
 */
public class TimeDepositAccount extends SavingsAccount {
    private int mMonthsUntilWithdraw;
    public static final double WITHDRAWAL_PENALTY = 20;
    
    public TimeDepositAccount(double interestRate, 
            double amount, int monthsUntilWithdraw ) {
        super(interestRate, amount);
        mMonthsUntilWithdraw = monthsUntilWithdraw;
    }
    
    public int getMonthsUntilWithdraw() {
        return mMonthsUntilWithdraw;
    }
    
    @Override
    public void addInterest() {
        if (mMonthsUntilWithdraw > 0) mMonthsUntilWithdraw--;
        super.addInterest();
    }
    
    @Override
    public void withdraw(double amount) {
        if (mMonthsUntilWithdraw != 0) applyPenalty();
        super.withdraw(amount);
    }
    
    private void applyPenalty() {
        super.withdraw(WITHDRAWAL_PENALTY);
    }
}

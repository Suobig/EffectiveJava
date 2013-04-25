package ru.feib.popov.bigjava.lessons.ch09.InvestmentViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This program demonstrates how an action can access 
 * a variable from a surrounding block
 * @author popov
 */
public class InvestmentViewer2 {
    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 150;
    
    private static final double INTEREST_RATE = 10.;
    private static final double INITIAL_BALANCE = 1000.;
    
    public void createViewer() {
        //The application adds interest to this bank account
        final BankAccount account = new BankAccount(INITIAL_BALANCE);
        
        //Crate frame with a button and a label.
        //Clicking button you add percent to bank account 
        //And new account value is displayed on the label
        final JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        //Add button to trigger the calculation
        JButton buttonSetInterest = new JButton("Add Interest");
        JButton buttonClose = new JButton("Close");
        final JLabel label = new JLabel("Inital balance = " 
                + account.getBalance());
        
        panel.add(buttonSetInterest);
        panel.add(label);
        panel.add(buttonClose);
        frame.add(panel);
                
        ActionListener listenerSetInterest = new ActionListener()
        //Implement button click action in ActionListener event
        {
            @Override
            public void actionPerformed(ActionEvent event) {
                double interest = account.getBalance() * INTEREST_RATE / 100;
                account.deposit(interest);
                label.setText("Current balance = " + account.getBalance());
            }
        };
        buttonSetInterest.addActionListener(listenerSetInterest);
        
        ActionListener listenerClose = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
            }
        };
        buttonClose.addActionListener(listenerClose);
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

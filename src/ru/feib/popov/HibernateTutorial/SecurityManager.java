package ru.feib.popov.HibernateTutorial;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author popov
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class SecurityManager {
    public static String getPassword() throws BadAuthorizationException {
        JTextField loginField = new JTextField(10);
        loginField.setEditable(false);
        loginField.setText("Test");
        loginField.setFocusable(false);

        JPasswordField passwordField = new JPasswordField(10) {
            @Override
            public void addNotify() {
                super.addNotify();
                requestFocus();
            }
        };

        JPanel myPanel = new JPanel(new GridBagLayout());
        myPanel.add(new JLabel("Login:"), new GridBagConstraints(
                0, 0, 1, 1, 0, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(5, 5, 5, 0), 0, 0));
        
        myPanel.add(loginField, new GridBagConstraints(
                1, 0, 1, 1, 0, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(5, 5, 5, 5), 0, 0));
        
        myPanel.add(new JLabel("Password:"), new GridBagConstraints(
                0, 1, 1, 1, 0, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 5, 5, 0), 0, 0));
        
        myPanel.add(passwordField, new GridBagConstraints(
                1, 1, 1, 1, 0, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 5, 5, 5), 0, 0));
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Database security", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        String password = String.copyValueOf(passwordField.getPassword());
        if (result == JOptionPane.OK_OPTION && ! "".equals(password)) {
            return password;
        } 
        throw new BadAuthorizationException("No password entered.");
    }
}

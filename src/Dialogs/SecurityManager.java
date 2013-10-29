/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public final class SecurityManager {
    private static JTextField loginField = new JTextField(10);
    private static JPasswordField passwordField = new JPasswordField(10);
    
    public static String getPassword() {
        JOptionPane pane = new JOptionPane();
        SecurityDialog f = new SecurityDialog(loginField, passwordField);
        f.setContentPane(pane);
        f.pack();
        f.setVisible(true);
        pane.getValue();
        return String.copyValueOf(passwordField.getPassword());
    }       
}

class Test {
    public static void main(String[] args) {
        System.out.println(SecurityManager.getPassword());
    }
}
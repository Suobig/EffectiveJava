/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.HibernateTutorial;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author popov
 */
public class ConnectionDialog extends JDialog {
    private JButton buttonCancel;
    private JButton buttonOK;
    private JTextField userField;
    private JPasswordField passwordField;
    private JTextField databaseField;
    
    final int BIG_OFFSET = 10;
    final int SMALL_OFFSET = 5; 
    
    private String password ="";
    
    public ConnectionDialog() {
        super.dialogInit();
        initialize();
    }
    
    
    
    private void initialize() {
        //<editor-fold defaultstate="collapsed" desc="Initialization code">
        setSize(250, 150);
        setTitle("Connection security");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);        
        JPanel content = new JPanel(new GridBagLayout());
        JLabel databaseLabel = new JLabel("Database:");
        JLabel userLabel = new JLabel("UID:");
        JLabel passwordLabel = new JLabel("Password");
        
        content.add(new JLabel("Database:"), new GridBagConstraints(
                0, 0, 1, 1, 1, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(BIG_OFFSET, BIG_OFFSET, 0, 0), 0, 0));
        
        databaseField = new JTextField("Treasury", 20);
        databaseField.setEditable(false);
        databaseField.setFocusable(false);
        content.add(databaseField, new GridBagConstraints(
                1, 0, 2, 1, 1, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(BIG_OFFSET, SMALL_OFFSET, 0, BIG_OFFSET), 0, 0));
        
        content.add(new JLabel("UserName:"), new GridBagConstraints(
                0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.BOTH, 
                new Insets(SMALL_OFFSET, BIG_OFFSET, 0, 0), 0, 0));
        
        userField = new JTextField("Test", 20);
        userField.setFocusable(false);
        userField.setEditable(false);
        content.add(userField, new GridBagConstraints(
                1, 1, 2, 1, 0, 0, 
                GridBagConstraints.CENTER, 
                GridBagConstraints.BOTH, 
                new Insets(SMALL_OFFSET, SMALL_OFFSET, 0, BIG_OFFSET), 0, 0));
        
        content.add(new JLabel("Password:"), new GridBagConstraints(
                0, 2, 1, 1, 0, 0, 
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
                new Insets(SMALL_OFFSET, BIG_OFFSET, 0, 0), 0, 0));
        
        passwordField = new JPasswordField("", 20);
        passwordField.setEditable(true);
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonOK.doClick();
                }
            }
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
        content.add(passwordField, new GridBagConstraints(
                1, 2, 2, 1, 0, 0, 
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
                new Insets(SMALL_OFFSET, SMALL_OFFSET, 0, BIG_OFFSET), 0, 0));

        buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass = passwordField.getPassword();
                password = String.copyValueOf(pass);
                setVisible(false);
            }
        });
        content.add(buttonOK, new GridBagConstraints(
                1, 3, 1, 1 ,0 ,0, 
                GridBagConstraints.LINE_END, GridBagConstraints.NONE, 
                new Insets(SMALL_OFFSET, 0, SMALL_OFFSET, SMALL_OFFSET), 0, 0));
        
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                password = "";
                setVisible(false);
            }
        });
        content.add(buttonCancel, new GridBagConstraints(
                2, 3, 1, 1 ,0 ,0, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                new Insets(SMALL_OFFSET, 0, SMALL_OFFSET, SMALL_OFFSET), 0, 0));
        setContentPane(content);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                passwordField.requestFocus();
            }
        });
        //</editor-fold>   
    }
    
    public static void main(String[] args) {
        ConnectionDialog d = new ConnectionDialog();
        
    }
            
}

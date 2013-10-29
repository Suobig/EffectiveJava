package ru.feib.popov.HibernateTutorial;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author popov
 */
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;



public class PasswordBox
{
    public JOptionPane pane;
    
    @SuppressWarnings("unused")
    public String prompt()
    {
        pane = new JOptionPane();
        final JPasswordField pass = new JPasswordField(10);  
//        String[] options = {"OK", "Cancel"};
//        int action = JOptionPane.showOptionDialog(null, pass, "Enter a name", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        int action = JOptionPane.showConfirmDialog(null, pass,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
        return new String(pass.getPassword());
     }
    
    public static void main(String[] args) {
        PasswordBox b = new PasswordBox();
        b.prompt();
    }
}

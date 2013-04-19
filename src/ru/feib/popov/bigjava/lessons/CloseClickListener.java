/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author popov
 */
public class CloseClickListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        frame.dispose();
    }
    
}

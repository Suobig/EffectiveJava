/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener, that print as message
 * @author popov
 */
public class ClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.print("I was clicked!\n");
    }
}

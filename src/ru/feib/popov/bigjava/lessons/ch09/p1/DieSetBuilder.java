/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch09.p1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author popov
 */
public class DieSetBuilder {
    private static int DIE_SIDES = 6;
    private static int FRAME_WIDTH = 300;
    private static int FRAME_HEIGHT = 150;
    
    public void build() {                
        final JFrame frame = new JFrame("DieSet average");
        JPanel panel = new JPanel();
        final JLabel label = new JLabel("Click \"ThrowDies\" to count average");
        JButton throwButton = new JButton("Throw dies");
        JButton closeButton = new JButton("Close frame");
        
        //Listener for "Throw dies" button
        ActionListener throwListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DieMeasurer measurer = new DieMeasurer();
                DataSet diesSet = new DataSet(measurer);

                for (int i = 1; i < 10000; i++) {
                    diesSet.add(new Die(DIE_SIDES));
                }
               label.setText("Average of all dies = " + diesSet.getAverage());
            }
        };
        
        //Listener for "Close frame" button
        ActionListener closeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        
        //assemble frame
        throwButton.addActionListener(throwListener);
        closeButton.addActionListener(closeListener);
        panel.add(throwButton);
        panel.add(label);
        panel.add(closeButton);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
    }     
}

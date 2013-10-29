/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author popov
 */
public class ImageViewer {
    public static void main (String[] args) {
 
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
 
                JFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

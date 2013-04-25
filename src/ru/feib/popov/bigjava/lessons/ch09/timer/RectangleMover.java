package ru.feib.popov.bigjava.lessons.ch09.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This program moves the rectangle
 * @author popov
 */
public class RectangleMover {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    
    public void runMover() {
        JFrame frame = new JFrame();
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("An animated rectangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final RectangleComponent component = new RectangleComponent();
        frame.add(component);
        
        frame.setVisible(true);
        
        ActionListener listener = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) {
                component.moveBy(1, 1);
            }
        };
        
        final int DELAY = 30; //Delay in milliseconds
        Timer t = new Timer(DELAY, listener);
        t.start();
    }
}

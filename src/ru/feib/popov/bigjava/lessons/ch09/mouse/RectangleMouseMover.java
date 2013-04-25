
package ru.feib.popov.bigjava.lessons.ch09.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 * This program displays a RectangleComponent
 * @author popov
 */
public class RectangleMouseMover {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    
    public void drawRectangle() {
        final RectangleComponent component = new RectangleComponent();
        
        class ClickListener extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                component.moveTo(x, y);
            }
        }        
        
        ClickListener listener = new ClickListener();
        
        component.addMouseListener(listener);
        
        JFrame frame = new JFrame();
        frame.add(component);
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

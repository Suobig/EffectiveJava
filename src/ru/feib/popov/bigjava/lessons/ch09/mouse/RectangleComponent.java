package ru.feib.popov.bigjava.lessons.ch09.mouse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 *
 * @author popov
 */
public class RectangleComponent extends JComponent {
    private static final int BOX_X = 100;
    private static final int BOX_Y = 100;
    private static final int BOX_WIDTH = 20;
    private static final int BOX_HEIGHT = 30;
    
    private Rectangle box;
    
    public RectangleComponent() {
        //The rectangle that the paint method draws
        box = new Rectangle(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(box);
    }
    
    /**
     * Moves the rectangle by a given amount
     * @param dx the amount to move in the x-direction
     * @param dy the amount to move in the y-direction
     */
    public void moveBy(int dx, int dy) {
        box.translate(dx, dy);
        repaint();
    }
    
    public void moveTo(int x, int y) {
        box.setLocation(x, y);
        repaint();    
    }
}

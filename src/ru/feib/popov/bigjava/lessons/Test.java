/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

//import java.awt.event.ActionListener;
import ru.feib.popov.bigjava.lessons.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import ru.feib.popov.bigjava.lessons.timer.RectangleMover;

/**
 *
 * @author popov
 */
public class Test {
    public static final int FRAME_WIDTH = 100;
    public static final int FRAME_HEIGHT = 120;
    public static final int BUTTON_CLOSE_HEIGHT = 60;
    public static final int BUTTON_CLICK_HEIGHT = 60;
    public static final int BUTTON_CLOSE_X = 60;
    
    public void runButton() {
        JFrame frame = new JFrame();
        JButton buttonClick = new JButton("Click me!");
        frame.add(buttonClick);
        buttonClick.setSize(FRAME_WIDTH,BUTTON_CLICK_HEIGHT);
        
        buttonClick.addActionListener(new ClickListener());
        
        JButton buttonClose = new JButton("Close");
        frame.add(buttonClose);
        buttonClose.setSize(FRAME_WIDTH, BUTTON_CLICK_HEIGHT);
        buttonClose.setLocation(BUTTON_CLOSE_X,buttonClose.getX());

        buttonClose.addActionListener(new CloseClickListener());
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void runInvestmentViewer1() {
        InvestmentViewer1 viewer = new InvestmentViewer1();
        viewer.createViewer();
    }
    
    public void runInvestmentViewer2() {
        InvestmentViewer2 viewer = new InvestmentViewer2();
        viewer.createViewer();
    }
    
    public void runRectangleMover() {
        RectangleMover mover = new RectangleMover();
        
        mover.runMover();
    }
}

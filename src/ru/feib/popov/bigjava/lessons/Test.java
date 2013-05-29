/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons;

import ru.feib.popov.bigjava.lessons.ch09.InvestmentViewer.*;
import ru.feib.popov.bigjava.lessons.ch09.mouse.*;
import ru.feib.popov.bigjava.lessons.ch09.p1.*;
import ru.feib.popov.bigjava.lessons.ch09.p2.*;
import ru.feib.popov.bigjava.lessons.ch09.p7.*;
import ru.feib.popov.bigjava.lessons.ch09.p8.*;
//import ru.feib.popov.bigjava.lessons.ch09.timer.*;
//import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import ru.feib.popov.databaseTester.*;
import ru.feib.popov.bigjava.lessons.ch09.timer.RectangleMover;
import ru.feib.popov.bigjava.lessons.ch10.accounts.*;
import ru.feib.popov.bigjava.lessons.ch10.questions.*;
import ru.feib.popov.bigjava.lessons.ch11.ex1.*;
import ru.feib.popov.bigjava.lessons.ch15.dataStructures.*;
import ru.feib.popov.bigjava.lessons.ch15.LinkedListBycicle.*;
import ru.feib.popov.bigjava.lessons.ch15.LispList.*;
import ru.feib.popov.bigjava.lessons.ch18.InvestmentViewer.*;
import ru.feib.popov.bigjava.lessons.ch18.FontFrame.*;
import ru.feib.popov.bigjava.lessons.ch18.ColorViewer.*;
import ru.feib.popov.bigjava.lessons.ch20.greeting.*;



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
    
    public void runRectangleMouseMover() {
        RectangleMouseMover mover = new RectangleMouseMover();
        mover.drawRectangle();
    }
    
    public void runDieSet() {
        DieSetBuilder builder = new DieSetBuilder();
        builder.build();
    }
    
    public void runQuizGradesSet() {
        QuizGradesSet quizSet = new QuizGradesSet();
        quizSet.buildSet();
    }
    
    public void runQuizGradesSet2() {
        QuizGradesSet2 quizSet = new QuizGradesSet2();
        quizSet.buildSet();
    }
    
    public void runSequenceDemo() {
        SequenceDemo demo = new SequenceDemo();
        demo.runDemo();
    }
    
    public void runAccountTester() {
        TestAccounts test = new TestAccounts();
        test.test();
    }
    
    public void runQuestionDemo() {
        QuestionDemo demo = new QuestionDemo();
        
        demo.runDemo();
    }
    
    public void testFileDataForm() {
        FileDataForm form = new FileDataForm();
        form.display();
    }
    
    public void testLinkedList() {
        LinkedListTester tester = new LinkedListTester();
        tester.run();
    }
    
    public void testLinkedListBycicle() {
        ListTester tester = new ListTester();
        tester.run();
    }
    
    public void testLispList() {
        LispListTester tester = new LispListTester();
        tester.run();
    }
    
    public void testInvestmentViewer3() {
        InvestmentFrame frame = new InvestmentFrame();
        frame.display();
    }
    
    public void testFontFrame() {
        FontViewer frame = new FontViewer();
        frame.display();
    }
    
    public void testColorViewer() {
        ColorViewer viewer = new ColorViewer();
        viewer.display();
    }
    
    public void testGreetingThreads() { 
        GreetingThreadRunner runner = new GreetingThreadRunner();
        runner.go();
    }
    
    public void testComboBox() {
        NewJFrame frame = new NewJFrame();
        frame.display();
    }
}


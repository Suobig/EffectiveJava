package net.suobig.effectivejava.ch2allObjectMethods;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
 
class Buttons extends JButton {
    public final int aXPos;
    public final int aYPos;
 
    Buttons(int xPos, int yPos) {
        aXPos = xPos;
        aYPos = yPos;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evnt) {
                System.out.println(aXPos + "  " + aYPos);
            }
        });
    }
}
 
public class Test extends JFrame {
 
    private Buttons[][] playerField;
    private JPanel player;
    private JPanel button;
    private JButton enabler;
 
    Test() {
        initFrame();
    }
 
    void initFrame() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        button = new JPanel();
        enabler = new JButton("Enable buttons");
        button.add(enabler);
        enabler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for(int i = 0; i < 10; i++) {
                    for(int j = 0; j < 10; j++) {
                        playerField[i][j].setEnabled(true);                 
                    }
                }
                getCoord(playerField);
            }
        });
        add(button, BorderLayout.SOUTH);
        setVisible(true);
        initField();
    }
    
    void initField() {
        player = new JPanel();
        playerField = new Buttons[10][10];
        player.setLayout(new GridLayout(10, 10));
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                playerField[i][j] = new Buttons(i, j);
                player.add(playerField[i][j]);
                playerField[i][j].setEnabled(false);
            }
        }
        add(player, BorderLayout.CENTER);
    }
     
    void getCoord(Buttons[][] work) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                work[i][j] = new Buttons(i, j);
                player.add(work[i][j]);
            }
        }
    }
    
    public static void main(String[] args) {
        int objectsCount = 10;
        int index = 5;
        ArrayList<JButton> objects;
        objects = new ArrayList<>(Integer.MAX_VALUE);
//как я понимаю, все ссылки сейчас установлены в null. Я пытаюсь сделать следующее
//        objects.add(index, new JButton());
    }
}
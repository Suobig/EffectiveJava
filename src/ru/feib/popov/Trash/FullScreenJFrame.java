/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

/**
 *
 * @author popov
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   

public class FullScreenJFrame extends JFrame{

    private JPanel contentPane = new JPanel();
    private JButton fullscreenButton = new JButton("Fullscreen Mode");
    private boolean Am_I_In_FullScreen = false;
    private int PrevX,PrevY,PrevWidth,PrevHeight;

    public void run() {
         FullScreenJFrame frame = new FullScreenJFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(600,500);
         frame.setVisible(true);
    }

    public FullScreenJFrame(){
        super("My FullscreenJFrame");

        setContentPane(contentPane);
        //From Here starts the trick

        FullScreenEffect effect = new FullScreenEffect();

        fullscreenButton.addActionListener((ActionListener) effect);

        contentPane.add(fullscreenButton);
        fullscreenButton.setVisible(true);

    }

    private class FullScreenEffect implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
             // TODO Auto-generated method stub

                 if(Am_I_In_FullScreen == false){

                          PrevX = getX();
              PrevY = getY();
              PrevWidth = getWidth();
              PrevHeight = getHeight();

              dispose(); //Destroys the whole JFrame but keeps organized every Component                               
                          //Needed if you want to use Undecorated JFrame
                          //dispose() is the reason that this trick doesn't work with videos
                          setUndecorated(true);

                  setBounds(-10,-100,getToolkit().getScreenSize().width+30,getToolkit().getScreenSize().height+110);
                setVisible(true);
                                Am_I_In_FullScreen = true;
                  }
                   else{
                        setVisible(true);

                        setBounds(PrevX, PrevY, PrevWidth, PrevHeight);
                        dispose();
            setUndecorated(false);
            setVisible(true);
                        Am_I_In_FullScreen = false;
                   }
        }
    }
    
    public static void main(String agrs[]) { 
        System.out.println("I'm main too!");
    }
}
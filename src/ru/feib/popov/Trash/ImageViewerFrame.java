/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
   A frame with a label to show an image.
*/
/*
 * frame with text marker for output image
 * */
 
class ImageViewerFrame extends JFrame {
 
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;
 
    public ImageViewerFrame() {
       setTitle("ImageViewer");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
 
      // use a label to display the images
      label = new JLabel();
//      Container contentPane = getContentPane();
      add(label);
 
      // set up the file chooser
      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
 
      // set up the menu bar
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
 
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent event) {
                // TODO Auto-generated method stub
 
                //show dialog windows
                int result = chooser.showOpenDialog(null);
 
                //if enjoy file, created it icon for label
                if (result == JFileChooser.APPROVE_OPTION) {
 
                    String name = chooser.getSelectedFile().getPath();
                    label.setIcon(new ImageIcon(name));
                }
            }
        });
// 
//      JMenuItem exitItem = new JMenuItem("Exit");
//      menu.add(exitItem);
//      exitItem.addActionListener(new
//         ActionListener()
//         {
//            public void actionPerformed(ActionEvent event)
//            {
//               System.exit(0);
//            }
//         });
    }
}

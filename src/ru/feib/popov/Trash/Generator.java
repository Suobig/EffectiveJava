/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
 
public class Generator implements ActionListener, Runnable {
        private static int[] _ccNumber = new int[16];
        private static JTextField CCin;
        private static JTextField NumOfCCin;
        private static JTextArea resultText;
        private JFrame frm;
        private JButton btngen;
        private static char[] CCcharArr;
 
        /**
         * @wbp.parser.entryPoint
         */
        @Override
        public void run() {
                initComponents();
                initEvents();
                frm.setVisible(true);
        }
 
        public void initComponents() {
                frm = new JFrame();
                frm.setResizable(false);
                frm.setTitle("Lunah Generator");
                frm.setSize(new Dimension(330, 375));
                frm.getContentPane().setLayout(null);
                JPanel configpanel = new JPanel();
                configpanel.setBorder(new TitledBorder(null, "Input up to 15 numbers",
                                TitledBorder.LEADING, TitledBorder.TOP, null, null));
                configpanel.setBounds(10, 11, 307, 154);
                frm.getContentPane().add(configpanel);
                configpanel.setLayout(null);
 
                JLabel lblNewLabel = new JLabel("Input up to 15 nums");
                lblNewLabel.setBounds(10, 62, 172, 14);
                configpanel.add(lblNewLabel);
 
                JLabel label = new JLabel("Input nums of CC generate");
                label.setBounds(10, 31, 172, 14);
                configpanel.add(label);
 
                CCin = new JTextField();
                CCin.setBounds(163, 28, 133, 20);
                configpanel.add(CCin);
 
                NumOfCCin = new JTextField();
                NumOfCCin.setColumns(10);
                NumOfCCin.setBounds(210, 56, 86, 20);
                configpanel.add(NumOfCCin);
 
                btngen = new JButton("generate");
                btngen.setBounds(10, 87, 89, 23);
                configpanel.add(btngen);
 
                JPanel textpanel = new JPanel();
                textpanel.setBorder(new TitledBorder(null, "Valid CC",
                                TitledBorder.LEADING, TitledBorder.TOP, null, null));
                textpanel.setBounds(10, 176, 307, 159);
                frm.getContentPane().add(textpanel);
                textpanel.setLayout(new BorderLayout(0, 0));
 
                resultText = new JTextArea();
                resultText.setRows(8);
                resultText.setLineWrap(true);
                JScrollPane scroll = new JScrollPane(resultText);
                textpanel.add(scroll);
 
        }
 
        public void initEvents() {
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                btngen.addActionListener(this);
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
                // Код, который нужно выполнить при нажатии
                convertToArray();
                operations();
        }
 
        public void convertToArray() {
                CCcharArr = CCin.getText().toCharArray();// convert string to char array
                for (int j = 0; j < CCcharArr.length; j++) {
                        int y = Integer.parseInt(String.valueOf(CCcharArr[j]));// convert
                                                                                                                                        // char to
                                                                                                                                        // int
                        _ccNumber[j] = y; // write in int array
                }
        }
 
        public static void operations() {
 
                // заполняем пустіе ячейки массива рандомно до 15го числа
                for (int i = CCcharArr.length; i < _ccNumber.length - 1; i++) {
                        _ccNumber[i] = (int) (Math.random() * 10);// write random to int
                                                                                                                // array from min to max
                }
 
                // начало вычисление контрольного числа
                int[] validcc = _ccNumber.clone();
                int temp;
                int temp2 = 0;
                for (int i = 0; i < validcc.length; i++) {
                        temp = validcc[i] * 2;
                        if (i % 2 == 0) {
                                if (temp > 9) {
                                        temp = temp - 9;
                                        validcc[i] = temp;
                                } else {
                                        validcc[i] = temp;
                                }
                        }
                }
                for (int a = 0; a < validcc.length; a++) {
                        temp2 = temp2 + validcc[a];
                }
                if (temp2 % 10 != 0) {
                        _ccNumber[15] = (int) (10 - temp2 % 10);// записываем контрольное
                                                                                                        // число в 16 ячейку массива
                }
                // конец вычисления контрольного числа
                resultText.setText("");
                // выводим все сгенерированое в текстерей
                for (int i = 1; i < Integer.parseInt(NumOfCCin.getText()) + 1; i++) {//цікл віполняем н раз NumOfCCin
                        resultText.append("� " + i + "  ");
                        for (int j = 0; j < _ccNumber.length; j++) {//дописываем в текстареа
                                if (j == _ccNumber.length - 1) {
                                        resultText.append(_ccNumber[j] + "\r\n");
                                } else {
                                        resultText.append("" + _ccNumber[j]);
                                }
 
                        }
                }
 
        }
 
        public static void main(String[] args) {
                Generator gen = new Generator();
                SwingUtilities.invokeLater(gen); // поехали
        }
}
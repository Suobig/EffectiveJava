/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch10.questions;

/**
 *
 * @author popov
 */
public class NumericQuestion extends Question {
    private double mAccuracy;
    private double mAnswer;
    
    public NumericQuestion(String questionText, double accuracy) {
        super(questionText);
        mAccuracy = accuracy;
    }
    
    public void setAnswer(double answer)  {
        mAnswer = answer;   
    }
    
    @Override
    public boolean checkAnswer(String response) {      
        double responseValue = Double.parseDouble(response);
        return (Math.abs(mAnswer - responseValue) < mAccuracy);
    }
}
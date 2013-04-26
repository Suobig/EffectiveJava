package ru.feib.popov.bigjava.lessons.ch09.p2;

/**
 * Measure QuizGrades
 * @author popov
 */
public class QuizGradesMeasurer implements Measurer{
    @Override
    public double measure(Object x) {
        QuizGrade quiz = (QuizGrade)x;
        
        return quiz.getScore();
    }
}

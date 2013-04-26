package ru.feib.popov.bigjava.lessons.ch09.p2;

/**
 * Fill DataSet with quiz grades and get average score and max grade and score
 * @author popov
 */
public class QuizGradesSet {
    public void buildSet() {
        Measurer quizMeasurer = new QuizGradesMeasurer();
        
        DataSet quizSet = new DataSet(quizMeasurer);
        
        //Let's hardcode some data into our set!
//        quizSet.add(new QuizGrade(5.5, "A+"));
        quizSet.add(new QuizGrade(5, "A"));
        quizSet.add(new QuizGrade(4.8, "A-"));
        quizSet.add(new QuizGrade(4.5, "B+"));
        quizSet.add(new QuizGrade(4, "B"));
        quizSet.add(new QuizGrade(3.8, "B-"));
        quizSet.add(new QuizGrade(3.5, "C+"));
        quizSet.add(new QuizGrade(3, "C"));
        quizSet.add(new QuizGrade(2.8, "C-"));
        quizSet.add(new QuizGrade(2, "D"));
        quizSet.add(new QuizGrade(1, "F"));
        
        QuizGrade maxGrade = (QuizGrade)quizSet.getMaximum();
        QuizGrade minGrade = (QuizGrade)quizSet.getMinimum();
        
        System.out.print("Average score: " + quizSet.getAverage() + "\n" +
                "Max score: " + maxGrade.getScore() + "\n" +
                "Max grade: "  + maxGrade.getGrade() + "\n" + 
                "Min score: " + minGrade.getScore() + "\n" + 
                "Min grade: " + minGrade.getGrade() +"\n");
    }
}

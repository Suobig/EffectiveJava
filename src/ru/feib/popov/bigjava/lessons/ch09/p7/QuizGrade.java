package ru.feib.popov.bigjava.lessons.ch09.p7;


/**
 * Class representing quiz result, containing grade name and grade score
 * with setter and getters for them.
 * @author popov
 */
public class QuizGrade implements Measurable {
    private double mScore;
    private String mGrade;
    
    /**
     * Build quiz result with all parameters
     * @param score grade value
     * @param grade grade name
     */
    public QuizGrade(double score, String grade) {
        mScore = score;
        mGrade = grade;
    }
    
    /**
     * Build quiz result with empty parameters
     */
    public QuizGrade() {
        mScore = 0.;
        mGrade = "";
    }
    
    /** 
     * grade score setter
     * @param score  score value
     */
    public void setScore(double score) {
        mScore = score;
    }
    
    /**
     * Grade name setter
     * @param grade grade name
     */
    public void setGrade(String grade) {
        mGrade = grade;
    }
    
    /**
     * Score getter
     * @return score value
     */
    public double getScore() {
        return mScore;
    }
    
    /**
     * Grade getter
     * @return  grade name 
     */
    public String getGrade() {
        return mGrade;
    }
    
    public double getMeasure() {
        return mScore;
    }
}

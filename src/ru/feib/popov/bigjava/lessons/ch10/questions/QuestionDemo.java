package ru.feib.popov.bigjava.lessons.ch10.questions;

import java.util.Scanner;

public class QuestionDemo
{
    public void runDemo()
    {
        final double QUESTION_ACCURACY = 0.01;
        Question[] quiz = new Question[3];

        quiz[0] = new Question("Who was the inventor of Java?");
        quiz[0].setAnswer("James Gosling");      

        ChoiceQuestion question = new ChoiceQuestion(
            "In which country was the inventor of Java born?");
        question.addChoice("Australia", false);
        question.addChoice("Canada", true);
        question.addChoice("Denmark", false);
        question.addChoice("United States", false);
        quiz[1] = question;
        NumericQuestion numQuestion = new NumericQuestion(
                "What does e equals?", QUESTION_ACCURACY);
        numQuestion.setAnswer(Math.E);
        quiz[2] = numQuestion;

        Scanner in = new Scanner(System.in);
        for (Question q : quiz)
        {
            q.display();
            System.out.print("Your answer: ");
            String response = in.nextLine();
            System.out.println(q.checkAnswer(response));      
        }
    }
}


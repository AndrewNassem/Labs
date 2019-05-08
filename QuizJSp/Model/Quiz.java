/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Andy
 */
public class Quiz {

    private String[] questions;

    private int[] answer;

    public Quiz() {
        this.questions = new String[]{"3,1,4,5", "1,1,2,3,5", "1,4,9,16,25", "2,3,5,7,11", "1,2,4,8,16"};
        this.answer = new int[]{9, 8, 36, 13, 32};
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

}

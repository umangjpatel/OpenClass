package io.github.umangjpatel.openclass.models;

public class Question {

    private String mKey, mQuestion, mOption1, mOption2, mOption3, mOption4;
    private int mAnswer;

    public Question() {
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getOption1() {
        return mOption1;
    }

    public void setOption1(String option1) {
        mOption1 = option1;
    }

    public String getOption2() {
        return mOption2;
    }

    public void setOption2(String option2) {
        mOption2 = option2;
    }

    public String getOption3() {
        return mOption3;
    }

    public void setOption3(String option3) {
        mOption3 = option3;
    }

    public String getOption4() {
        return mOption4;
    }

    public void setOption4(String option4) {
        mOption4 = option4;
    }

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }
}

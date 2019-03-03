package com.fehr.onlinerealtimedatabase;

public class WordNumber {

    private String word;
    private int number;

    public WordNumber() {

    }

    public WordNumber(String word, int number) {
        this.word = word;
        this.number = number;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

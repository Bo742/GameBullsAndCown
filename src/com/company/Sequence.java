package com.company;

public class Sequence {
    private String sequence="";
    private int[] numberSequence;
    private int length;

    public Sequence(int length) {
        this.length = length;
        numberSequence = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            numberSequence[i]=0;
        }
    }

    public void setSequence(String sequence){
        this.sequence=sequence;
    }

    public String getSequence(){
        return sequence;
    }

    public void setNumberSequence(int number, int j){
        numberSequence[j]=number;
        sequence="";
        for (int i = 0; i < length ; i++) {
            sequence += numberSequence[i];
        }
    }

    public int getNumberSequence(int j){
        return numberSequence[j];
    }
}

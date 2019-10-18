package com.company;

public class UserSequence {
    private String sequence="";
    private int[] numberSequence;

    public UserSequence(String sequence){
        this.sequence = sequence;
        int lengthSequence = this.sequence.length();
        numberSequence = new int[lengthSequence];
        for (int i = 0; i < lengthSequence ; i++) {
            numberSequence[i]=0;
        }
        parseSequence();
    }

    public void parseSequence(){
        String[] stringNumber = sequence.split("");
        for (int i = 0; i <stringNumber.length ; i++) {
            numberSequence[i] = Integer.parseInt(stringNumber[i]);
        }
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int[] getNumberSequence() {
        return numberSequence;
    }

    public void setNumberSequence(int[] numberSequence) {
        this.numberSequence = numberSequence;
    }

    public int getNumber(int i){
        return numberSequence[i];
    }

    public void setNumber(int i,int number){
        numberSequence[i]=number;
    }

    public int getLengthSequence(){
        return sequence.length();
    }

    public void getNewSequence(){
        int length = getLengthSequence();
        sequence="";
        for (int i = 0; i < length; i++) {
            numberSequence[i] +=4;
            sequence +=numberSequence[i];
        }
    }

    public void toSwap(int first,int second){
        int length = getLengthSequence();
        sequence="";
        int swap = numberSequence[first];
        numberSequence[first] = numberSequence[second];
        numberSequence[second] = swap;
        for (int i = 0; i < length ; i++) {
            sequence += numberSequence[i];
        }
    }
}

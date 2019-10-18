package com.company;

import java.util.Scanner;

public class Alg {
    private UserSequence userSequence;
    private boolean[] rightPosition;
    private String answer="";
    private Scanner in = new Scanner(System.in);


    public Alg(String sequence){
        userSequence = new UserSequence(sequence);
        for (int i = 0; i < userSequence.getLengthSequence() ; i++) {
            rightPosition[i]=false;
        }
        alg();
    }

    public void alg(){
        String tryDoAnswer = "";
        for (int i = 0; i < 4 ; i++) {
            tryDoAnswer += ""+i;
        }
        System.out.println(tryDoAnswer);
        System.out.print("Количество правильных символов в последовательности: ");
        int rightNumberSequence = in.nextInt();
        System.out.print("Количество правильных символов,которые стоят на своих местах: ");
        int rightNumberSequencePosition = in.nextInt();

        if(rightNumberSequencePosition==userSequence.getLengthSequence()){
            answer = tryDoAnswer;
        }
        else{
            recurseAlg(rightNumberSequence,rightNumberSequencePosition,tryDoAnswer);
        }
    }

    public void recurseAlg(int rightNumberSequenceOld,int rightNumberSequencePositionOld,String tryDoAnswerOld){
        UserSequence userSequenceTry = new UserSequence(tryDoAnswerOld);
        if(rightNumberSequenceOld == 0){
            userSequenceTry.getNewSequence();
            System.out.println(userSequenceTry.getSequence());
            System.out.print("Количество правильных символов в последовательности: ");
            int rightNumberSequence = in.nextInt();
            System.out.print("Количество правильных символов,которые стоят на своих местах: ");
            int rightNumberSequencePosition = in.nextInt();
            if(rightNumberSequencePosition == userSequenceTry.getLengthSequence()){
                answer=userSequenceTry.getSequence();
            }
            else{
                recurseAlg(rightNumberSequence,rightNumberSequencePosition,userSequenceTry.getSequence());
            }
        }
        else{
            
            if(rightNumberSequencePositionOld!=rightNumberSequenceOld){
                int rightNumberSequencePosition = rightNumberSequencePositionOld;
                int rightNumberSequence=rightNumberSequenceOld;
                int i=0;
                int j=i+1;
                while (rightNumberSequence != rightNumberSequencePosition){
                    if(j==userSequenceTry.getLengthSequence()-1){
                        i++;
                        j=i+1;
                    }
                    userSequenceTry.toSwap(i,j);
                    System.out.println(userSequenceTry.getSequence());
                    System.out.print("Количество правильных символов в последовательности: ");
                    int rNS = in.nextInt();
                    System.out.print("Количество правильных символов,которые стоят на своих местах: ");
                    int rNSP = in.nextInt();
                    if(rNSP<rightNumberSequencePosition){
                        userSequenceTry.toSwap(i,j);
                        i++;
                    }
                    else{
                        rightNumberSequencePosition = rNSP;
                        rightNumberSequence=rNS;
                    }
                    j++;
                }
                if(rightNumberSequencePosition==4){
                    answer=userSequenceTry.getSequence();
                }
            }
        }



    }

    public String getAnswer(){
        return answer;
    }
}
package com.company;

import java.util.Scanner;

public class Alg {
    private static Scanner in = new Scanner(System.in);
    private static Sequence tryFoundSequence;
    private static int lengthUserSequence = 0;
    private static int rightNumberInSequence = 0;
    private static int rightNumberInSequenceOnPosition = 0;
    private static int maxNumberInSequence=0;
    private static int currentPositionInSequence = 0;
    private static int numberCurrentPositionForBackUp = 0;
    private static String answer = "";
    private static boolean[] rightPosition ;

    public Alg(int length){
        lengthUserSequence=length;
        rightPosition = new boolean[lengthUserSequence];
        tryFoundSequence = new Sequence(lengthUserSequence);
        for (int i = 0; i < lengthUserSequence ; i++) {
            tryFoundSequence.setNumberSequence(i,i);
            maxNumberInSequence=i;
            rightPosition[i] = false;
        }

        firstIteration();
        otherIteration();
    }

    public void otherIteration(){
        if(rightNumberInSequenceOnPosition == lengthUserSequence){
            answer = tryFoundSequence.getSequence();
        }
        else{
            if(rightNumberInSequence != lengthUserSequence){
                numberCurrentPositionForBackUp = tryFoundSequence.getNumberSequence(currentPositionInSequence);
                int num = (maxNumberInSequence + 1)%10;
                tryFoundSequence.setNumberSequence(num,currentPositionInSequence);
                System.out.println(tryFoundSequence.getSequence());
                int rs = in.nextInt();
                int rsp = in.nextInt();
                if(rs>rightNumberInSequence){
                    maxNumberInSequence++;
                    rightNumberInSequence=rs;
                    if(rsp>rightNumberInSequenceOnPosition){
                        rightNumberInSequenceOnPosition = rsp;
                        rightPosition[currentPositionInSequence] = true;
                    }
                    currentPositionInSequence++;
                }
                else{
                    if(rs<rightNumberInSequence){
                        tryFoundSequence.setNumberSequence(numberCurrentPositionForBackUp,currentPositionInSequence);
                        maxNumberInSequence++;
                        if(rsp<rightNumberInSequenceOnPosition){
                            rightPosition[currentPositionInSequence] = true;
                        }
                        currentPositionInSequence++;
                    }
                    if(rs==rightNumberInSequence){
                        tryFoundSequence.setNumberSequence(numberCurrentPositionForBackUp,currentPositionInSequence);
                        maxNumberInSequence++;
                    }
                }
                otherIteration();
            }
            if(rightNumberInSequence==lengthUserSequence){
                int i=0;
                int j=i+1;
                while (rightNumberInSequence != rightNumberInSequenceOnPosition) {
                    if (rightPosition[i]) {
                        i++;
                    } else {
                        if (j == lengthUserSequence) {

                            i++;
                            if (rightPosition[i]) {
                                i++;
                            }
                            j = i + 1;
                        }
                        if (rightPosition[j]) {
                            j++;
                        } else {
                            if (i == j) {
                                j++;
                            } else {
                                toSwap(i, j);
                                System.out.println(tryFoundSequence.getSequence());
                                int rNS = in.nextInt();
                                int rNSP = in.nextInt();
                                if (rNSP < rightNumberInSequenceOnPosition) {
                                    toSwap(i, j);
                                    i++;
                                } else {
                                    rightNumberInSequenceOnPosition = rNSP;
                                }
                                j++;
                            }
                        }
                    }
                }
                otherIteration();
            }

        }
    }

    public void toSwap(int first,int second){
        int swap = tryFoundSequence.getNumberSequence(first);
        tryFoundSequence.setNumberSequence(tryFoundSequence.getNumberSequence(second),first);
        tryFoundSequence.setNumberSequence(swap,second);
    }

    public void createNewSequence(){
        int mx = maxNumberInSequence;
        for (int i = mx+1; i < lengthUserSequence+mx+1 ; i++) {
            tryFoundSequence.setNumberSequence(i,i-mx-1);
            maxNumberInSequence=i;
        }
        firstIteration();
    }

    public void firstIteration(){
        System.out.println(tryFoundSequence.getSequence());
        rightNumberInSequence = in.nextInt();
        rightNumberInSequenceOnPosition = in.nextInt();
        if(rightNumberInSequence==0){
            createNewSequence();
        }
    }

    public String getAnswer(){
        return answer;
    }

}
package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class Alg {
    private static Scanner in = new Scanner(System.in);
    private static Sequence tryFoundSequence;
    private static Sequence userSequence;
    private static int lengthUserSequence = 0;
    private static int rightNumberInSequenceOld = 0;
    private static int rightNumberInSequenceCurrent = 0;
    private static int rightNumberInSequenceOnPositionOld = 0;
    private static int rightNumberInSequenceOnPositionCurrent = 0;
    private static int maxNumberInSequence=0;
    private static int currentPositionInSequence = 0;
    private static int memoryNumber = 0;
    private static boolean[] rightPosition ;

    public Alg(int length, String sequence){
        lengthUserSequence = length;
        userSequence = new Sequence(length);
        userSequence.setSequence(sequence);
        tryFoundSequence = new Sequence(lengthUserSequence);
        rightPosition = new boolean[lengthUserSequence];
        for (int i = 0; i < lengthUserSequence ; i++) {
            rightPosition[i] = false;
        }
    }

    private void otherTry(){
        if(rightNumberInSequenceCurrent == lengthUserSequence){
            if(rightNumberInSequenceOnPositionCurrent != lengthUserSequence){
                sortSequence();
            }
        }
        else{
            changeNumberInSequence();
            iteration();
            if(rightNumberInSequenceCurrent > rightNumberInSequenceOld){
                rightNumberInSequenceOld = rightNumberInSequenceCurrent;
                if(rightNumberInSequenceOnPositionCurrent > rightNumberInSequenceOnPositionOld){
                    rightPosition[currentPositionInSequence] = true;
                    rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
                }
                currentPositionInSequence++;
            }
            else{
                if(rightNumberInSequenceOld == rightNumberInSequenceCurrent){
                    if(rightNumberInSequenceOnPositionCurrent > rightNumberInSequenceOnPositionOld){
                        rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
                        rightPosition[currentPositionInSequence] = true;
                        currentPositionInSequence++;
                        maxNumberInSequence = memoryNumber;
                    }
                    else{
                        if(rightNumberInSequenceOnPositionCurrent < rightNumberInSequenceOnPositionOld){
                            maxNumberInSequence = tryFoundSequence.getNumberSequence(currentPositionInSequence);
                            tryFoundSequence.setNumberSequence(memoryNumber,currentPositionInSequence);
                            currentPositionInSequence++;
                        }
                    }
                }
                else{
                    tryFoundSequence.setNumberSequence(memoryNumber,currentPositionInSequence);
                    currentPositionInSequence++;
                }
            }
            otherTry();
        }

    }

    private void sortSequence() {
        int i = 0;
        int j = 0;
        while (rightNumberInSequenceCurrent != rightNumberInSequenceOnPositionCurrent) {
            if(rightPosition[i]){
                i++;
                j=i+1;
            }
            else{
                if(j==lengthUserSequence){
                    i++;
                    j=i+1;
                }
                if(rightPosition[j]){
                    j++;
                }

                if(i<lengthUserSequence){
                    toSwap(i,j);
                    iteration();
                    if(rightNumberInSequenceOnPositionCurrent > rightNumberInSequenceOnPositionOld){
                        rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
                        j++;
                    }
                    else{
                        toSwap(i,j);
                        j++;
                    }
                }
            }

        }
    }

    private void toSwap(int first,int second){
        int swap = tryFoundSequence.getNumberSequence(first);
        tryFoundSequence.setNumberSequence(tryFoundSequence.getNumberSequence(second),first);
        tryFoundSequence.setNumberSequence(swap,second);
    }

    private void changeNumberInSequence(){
       memoryNumber = tryFoundSequence.getNumberSequence(currentPositionInSequence);
        maxNumberInSequence = maxNumberInSequence % 10;
        for (int i = 0; i < lengthUserSequence ; i++) {
            for (int j = 0; j < lengthUserSequence ; j++) {
                if(tryFoundSequence.getNumberSequence(j) == maxNumberInSequence){
                    maxNumberInSequence++;
                    maxNumberInSequence = maxNumberInSequence % 10;
                    continue;
                }
            }
        }
        tryFoundSequence.setNumberSequence(maxNumberInSequence, currentPositionInSequence);
        maxNumberInSequence++;


    }

    private void firstTry(){
        for (int i = 0; i < lengthUserSequence ; i++) {
            tryFoundSequence.setNumberSequence(i, i);
        }
        maxNumberInSequence = lengthUserSequence;
        iteration();
        if(rightNumberInSequenceCurrent==0){
            changeTrySequence();
        }
        else{
            rightNumberInSequenceOld = rightNumberInSequenceCurrent;
            rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
        }
    }

    private void changeTrySequence(){
        for (int i = maxNumberInSequence; i < lengthUserSequence + maxNumberInSequence ; i++) {
            tryFoundSequence.setNumberSequence(i % 10, i-maxNumberInSequence);
        }
        maxNumberInSequence += lengthUserSequence;
        iteration();
        if(rightNumberInSequenceCurrent==0){
            changeTrySequence();
        }
        else{
            rightNumberInSequenceOld = rightNumberInSequenceCurrent;
            rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
        }
    }

    private void iteration(){
        System.out.println(tryFoundSequence.getSequence());
        rightNumberInSequenceCurrent = 0;
        rightNumberInSequenceOnPositionCurrent = 0;
        for (int i = 0; i < lengthUserSequence ; i++) {
            for (int j = 0; j < lengthUserSequence; j++) {
                if(tryFoundSequence.getNumberSequence(j) == userSequence.getNumberSequence(i)){
                    rightNumberInSequenceCurrent++;
                    if(i==j){
                        rightNumberInSequenceOnPositionCurrent++;
                    }
                }
            }
        }
        System.out.println("Правильных цифр: " + rightNumberInSequenceCurrent);
        System.out.println("Правильных цифр на позициях: " + rightNumberInSequenceOnPositionCurrent);
    }

    public String getAnswer(){
        firstTry();
        otherTry();
        return tryFoundSequence.getSequence();
    }




}
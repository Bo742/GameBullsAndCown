package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Alg {
    private static Scanner in = new Scanner(System.in);
    private static Sequence tryFoundSequence;
    private static int lengthUserSequence = 0;
    private static int rightNumberInSequenceOld = 0;
    private static int rightNumberInSequenceCurrent = 0;
    private static int rightNumberInSequenceOnPositionOld = 0;
    private static int rightNumberInSequenceOnPositionCurrent = 0;
    private static int maxNumberInSequence=0;
    private static int currentPositionInSequence = 0;
    private static int memoryNumber = 0;
    private static ArrayList<Integer> memory = new ArrayList<>();
    private static boolean[] rightPosition ;

    public Alg(int length){
        lengthUserSequence = length;
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
                int k = memory.size();
                if(k>0){
                    for (int i = k-1; i >=0; i--) {
                        memory.remove(i);
                    }
                }
            }
            else{
                if(rightNumberInSequenceCurrent == rightNumberInSequenceOld){
                    if(rightNumberInSequenceOnPositionCurrent > rightNumberInSequenceOnPositionOld){
                        rightPosition[currentPositionInSequence] = true;
                        rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
                        currentPositionInSequence++;
                        for (int i = 0; i < memory.size() ; i++) {
                            tryFoundSequence.setNumberSequence(memory.get(i),currentPositionInSequence);
                            currentPositionInSequence++;
                        }
                        memory.remove(memory);
                    }
                    else{
                        if(rightNumberInSequenceOnPositionCurrent < rightNumberInSequenceOnPositionOld){
                            tryFoundSequence.setNumberSequence(memoryNumber, currentPositionInSequence);
                            currentPositionInSequence++;
                            for (int i = 0; i < memory.size() ; i++) {
                                tryFoundSequence.setNumberSequence(memory.get(i),currentPositionInSequence);
                                currentPositionInSequence++;
                            }
                            memory.remove(memory);
                        }
                        else{
                            memory.add(memoryNumber);
                        }
                    }
                }
                else{
                    memory.add(memoryNumber);
                    for (int i = 0; i < memory.size() ; i++) {
                        tryFoundSequence.setNumberSequence(memory.get(i),currentPositionInSequence);
                        currentPositionInSequence++;
                    }
                    memory.remove(memory);
                }
            }
            otherTry();
        }
    }

    private void sortSequence() {
        int i = 0;
        int j = i + 1;
        while (rightNumberInSequenceCurrent != rightNumberInSequenceOnPositionCurrent) {
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
                        iteration();
                        if (rightNumberInSequenceOnPositionCurrent < rightNumberInSequenceOnPositionOld) {
                            toSwap(i, j);
                            i++;
                            j = i + 1;
                        } else {
                            rightNumberInSequenceOnPositionOld = rightNumberInSequenceOnPositionCurrent;
                            j++;
                        }

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
        tryFoundSequence.setNumberSequence(maxNumberInSequence , currentPositionInSequence);
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
            tryFoundSequence.setNumberSequence(i, i-maxNumberInSequence);
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
        rightNumberInSequenceCurrent = in.nextInt();
        rightNumberInSequenceOnPositionCurrent = in.nextInt();
    }

    public String getAnswer(){
        firstTry();
        otherTry();
        return tryFoundSequence.getSequence();
    }




}
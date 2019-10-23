package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userSequence = in.nextLine();
        Alg alg = new Alg(userSequence.length(),userSequence);
        System.out.println(alg.getAnswer());





    }
}

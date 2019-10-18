package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sequence = in.nextLine();
        Alg alg = new Alg(sequence);
        System.out.println("Ответ: " + alg.getAnswer());

    }
}

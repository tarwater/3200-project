package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    Input input = new Input();
    input.collect();

    if(input.isBrute){

        double numPossibilities = Math.pow(input.characterSpace.length(), input.maxPasswordLength);
        System.out.println("Possible combinations: " + numPossibilities);

    }

    }
}

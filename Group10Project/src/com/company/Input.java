package com.company;

import java.util.Scanner;

public class Input {

    String url, username, usernameFilePath, dictionaryPath, characterSpace;
    boolean usernameKnown, isForm, isBrute;
    Scanner scanner;
    int maxPasswordLength;

    public Input() {
        username = "";
    }

    public void collect() {

        scanner = new Scanner(System.in);

        print("Enter the target website URL:");
        url = scanner.next();

        print("Is the username known? y/n");
        usernameKnown = validateYesNo(scanner.next());

        if (usernameKnown) {
            print("Enter the username");
            username = scanner.next();
        } else {
            print("Specify the path to a list of usernames:");
            usernameFilePath = scanner.next();
        }

        print("Form-based or Basic Auth access control? (Enter ‘form’ or ‘ba’)");
        isForm = isFormBased(scanner.next());

        print("Dictionary or brute-force attack?");
        isBrute = isBrute(scanner.next());

        if(!isBrute){
            print("Enter the path the the dictionary file:");
            dictionaryPath = scanner.next();
        } else {
            print("Enter the max password length:");
            maxPasswordLength = Integer.parseInt(scanner.next());

            characterSpace = characterSpaceSelector();
        }

    }

    public boolean validateYesNo(String s) {

        if (s.toLowerCase().equals("y")) {
            return true;
        } else if (s.toLowerCase().equals("n")) {
            return false;
        } else {
            print("Invalid input. Enter 'y' or 'n':");
            return validateYesNo(scanner.next());
        }
    }

    public boolean isFormBased(String s) {

        s = s.toLowerCase();

        if (s.equals("form")) {
            return true;
        } else if (s.equals("ba")) {
            return false;
        } else {
            print("Invalid. Enter ‘form’ or ‘ba’");
            return isFormBased(scanner.next());
        }
    }

    public boolean isBrute(String s){
        s = s.toLowerCase();

        if(s.equals("d")){
            return false;
        } else if (s.equals("b")){
            return true;
        } else {
            print("Enter 'd' or 'b':");
            return isBrute(scanner.next());
        }
    }

    public String characterSpaceSelector(){

        String space1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String space2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String space3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()+-_=";

        print("Please select a character space: (Enter a number) ");
        print("1. " + space1);
        print("2. " + space2);
        print("3. " + space3);
        print("4. I'll enter my own.");

        int choice = Integer.parseInt(scanner.next());

        switch (choice) {
            case 1:
                characterSpace = space1;
                break;
            case 2:
                characterSpace = space2;
                break;
            case 3:
                characterSpace = space3;
                break;
            case 4:
                print("Enter a character space:");
                characterSpace = scanner.next();
                break;
        }

        return null;
    }

    public void print(String s) {
        System.out.println(s);
    }
}

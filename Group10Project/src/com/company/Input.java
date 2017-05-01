package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public String url, username, usernameFilePath, dictionaryPath, characterSpace;
    boolean singleUsername, isForm, isBrute;
    Scanner scanner;
    int maxPasswordLength;

    BufferedReader br;
    FileReader fr;

    ArrayList<String> usernames;
    ArrayList<String> dictionaryWords;

    public Input() {
        username = "";
    }

    public void collect() {

        scanner = new Scanner(System.in);

        print("Enter the target website URL:");
        url = scanner.next();

        print("Use ONE username? (Enter 'y' on 'n')");
        singleUsername = validateYesNo(scanner.next());

        if (singleUsername) {
            print("Enter the username");
            username = scanner.next();
        } else {
            print("Specify the path to a list of usernames: (The less the better)");
            usernameFilePath = scanner.next();
            usernames = new ArrayList<String>();
            readUsernames(usernameFilePath);
        }

        print("Form-based or Basic Auth access control? (Enter ‘form’ or ‘ba’)");
        isForm = isFormBased(scanner.next());

        print("Dictionary or brute-force attack? (Enter 'd' or 'b)");
        isBrute = isBrute(scanner.next());

        if (!isBrute) {
            print("Enter the path of the dictionary file:");
            dictionaryPath = scanner.next();
            dictionaryWords = new ArrayList<String>();
            readWords(dictionaryPath);
//            showDictInfo();
        } else {
            print("Enter the max password length:");
            maxPasswordLength = Integer.parseInt(scanner.next());
            characterSpaceSelector();

            showBruteInfo();
        }
    }

    public void readWords(String path) {
        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                dictionaryWords.add(sCurrentLine);
            }

            print(dictionaryWords.toString());

        } catch (FileNotFoundException e) {
            System.out.println("No such file. Try again.");
            readWords(scanner.next());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void readUsernames(String path) {

        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                usernames.add(sCurrentLine);
            }

        } catch (FileNotFoundException e) {

            System.out.println("No such file. Try again.");
            readUsernames(scanner.next());
        } catch
                (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showBruteInfo() {
        double numPossibilities = Math.pow(characterSpace.length(), maxPasswordLength);
        System.out.println("Possible passwords: " + numPossibilities);
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

    public boolean isBrute(String s) {
        s = s.toLowerCase();

        if (s.equals("d")) {
            return false;
        } else if (s.equals("b")) {
            return true;
        } else {
            print("Enter 'd' or 'b':");
            return isBrute(scanner.next());
        }
    }

    public String characterSpaceSelector() {

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

    public String getCharacterSpace() {
        return characterSpace;
    }
}

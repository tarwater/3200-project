package com.company;

import java.util.Scanner;

/**
 * Created by clay on 3/28/17.
 */
public class Input {

    String url, username, usernameFilePath;
    boolean usernameKnown, isForm;
    Scanner scanner;

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

    public static void print(String s) {
        System.out.println(s);
    }
}

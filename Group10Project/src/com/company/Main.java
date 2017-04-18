package com.company;

import com.gargoylesoftware.htmlunit.WebClient;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {


    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

        Input input = new Input();
        input.collect();

        if (input.isForm) {
            FormLogin formLogin = new FormLogin(input.url, input.username, input.dictionaryWords);
            formLogin.setup();
            formLogin.login();
        }
    }

}

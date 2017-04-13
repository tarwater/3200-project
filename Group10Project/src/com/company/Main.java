package com.company;

import com.gargoylesoftware.htmlunit.WebClient;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Input input = new Input();
        input.collect();

        if (input.isForm) {
            FormLogin formLogin = new FormLogin(input.url, input.username, input.dictionaryWords);
            formLogin.setup();
            formLogin.login();
        }
    }

}

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

            if(input.singleUsername) {

                if(input.isBrute){
                    FormLogin formLogin = new FormLogin(input.url, input.username, input.characterSpace, input.maxPasswordLength);
                    formLogin.setup();
                    formLogin.login();
                } else {
                    FormLogin formLogin = new FormLogin(input.url, input.username, input.dictionaryWords);
                    formLogin.setup();
                    formLogin.login();
                }

            } else {
                if(input.isBrute){
                    FormLogin formLogin = new FormLogin(input.url, input.usernames, input.characterSpace, input.maxPasswordLength);
                    formLogin.setup();
                    formLogin.login();
                } else {
                    FormLogin formLogin = new FormLogin(input.url, input.dictionaryWords, input.usernames);
                    formLogin.setup();
                    formLogin.login();
                }
            }
        } else {
            if(input.singleUsername) {

                if(input.isBrute){
                    BALogin ba = new BALogin(input.url, input.username, input.characterSpace, input.maxPasswordLength);
                    ba.setup();
                } else {
                    BALogin ba = new BALogin(input.url, input.username, input.dictionaryWords);
                    ba.setup();
                }

            } else {
                if(input.isBrute){
                    BALogin ba = new BALogin(input.url, input.usernames, input.characterSpace, input.maxPasswordLength);
                    ba.setup();
                } else {
                    BALogin ba = new BALogin(input.url, input.usernames, input.dictionaryWords);
                    ba.setup();
                }
            }
        }

    }

}

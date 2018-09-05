package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private String userInput;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void show(String content) {
        System.out.println(content);
    }

    public void readUserInput() {
       userInput = scanner.nextLine();
    }

    public String getUserInput() {
        return userInput;
    }
}

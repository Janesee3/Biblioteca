package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {
	
	public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
	
    private Scanner scanner;

    public UserInterface() {
      
    }

    public void show(String content) {
        System.out.println(content);
    }

    public String readUserInput() {
    		scanner = new Scanner(System.in);
    		return scanner.nextLine();

    }

    public void showWelcomeSequence() {
    		show(WELCOME_MESSAGE);
    }
}

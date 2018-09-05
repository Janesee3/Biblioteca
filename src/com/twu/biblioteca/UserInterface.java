package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {
	
	public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
	
    private Scanner scanner;
    private String userInput;

    public UserInterface() {
      
    }

    public void show(String content) {
        System.out.println(content);
    }

    public void readUserInput() {
    		scanner = new Scanner(System.in);
    		userInput = scanner.nextLine();
    }

    public String getUserInput() {
        return userInput;
    }
    
    public void showWelcomeSequence() {
    		show(WELCOME_MESSAGE);
    }
}

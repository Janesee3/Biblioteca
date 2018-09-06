package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {
	
	public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
    public static final String MENU = "---- MAIN MENU ---- \n [1] List Books \n Please select by typing the corresponding number of the option you want.";
	
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

    public void showMenu() {
        show(MENU);
    }
}

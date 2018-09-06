package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	
	public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
    public static final String MENU = "---- MAIN MENU ---- \n[1] List Books\n[2] Quit\nPlease select by typing the corresponding number of the option you want.";

    public static final String BOOK_LIST_TITLE = "---- BOOK LIST ----\n";
    public static final String BOOK_LIST_TABLE_HEADING = String.format("%-5s | %-20s | %-20s | %-8s\n", "ID", "Title", "Author", "Year");
    public static final String BOOK_LIST_ITEM = "%-5d | %-20s | %-20s | %-8s\n";
    public static final String BOOK_LIST_MENU = "\n[b] Back To Menu     [checkout <book id>] Checkout book\n";


    private Scanner scanner;

    public UserInterface() {}

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

    public void showBookList(ArrayList<Book> list) {
        this.show(this.getBookListDisplayString(list));
    }

    public void showBookListMenu() {
        show(BOOK_LIST_MENU);
    }

    private String getBookListDisplayString(ArrayList<Book> list) {
        String displayString = BOOK_LIST_TITLE + BOOK_LIST_TABLE_HEADING;
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            String listItemString = String.format(BOOK_LIST_ITEM, i, book.getTitle(), book.getAuthor(), book.getYear());
            displayString += listItemString;
        }
        return displayString;
    }
}

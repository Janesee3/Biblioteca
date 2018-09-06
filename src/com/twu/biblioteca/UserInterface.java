package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";

    public static final String MENU_CHOICE_LIST_BOOKS = "1";
    public static final String MENU_CHOICE_QUIT = "2";
    public static final String MENU = String.format(
            "---- MAIN MENU ---- \n[%s] List Books\n[%s] Quit\nPlease select by typing the corresponding number of the option you want.",
            MENU_CHOICE_LIST_BOOKS,
            MENU_CHOICE_QUIT
    );
    public static final String INVALID_MENU_CHOICE = "Invalid selection! Please try again.";

    public static final String BOOK_LIST_CHOICE_BACK = "b";
    public static final String BOOK_LIST_CHOICE_CHECKOUT = "checkout";
    public static final String BOOK_LIST_TITLE = "---- BOOK LIST ----\n";
    public static final String BOOK_LIST_TABLE_HEADING = String.format("%-5s | %-20s | %-20s | %-8s\n", "ID", "Title", "Author", "Year");
    public static final String BOOK_LIST_ITEM = "%-5d | %-20s | %-20s | %-8s\n";
    public static final String BOOK_LIST_MENU = String.format(
            "\n[%s] Back To Menu     [%s <book id>] Checkout book\n",
            BOOK_LIST_CHOICE_BACK,
            BOOK_LIST_CHOICE_CHECKOUT
    );


    private Scanner scanner;

    public UserInterface() {
    }

    void show(String content) {
        System.out.println(content);
    }

    String readUserInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    void showWelcomeSequence() {
        show(WELCOME_MESSAGE);
    }

    void showMenu() {
        show(MENU);
    }

    void showBookList(ArrayList<Book> list) {
        this.show(this.getBookListDisplayString(list));
    }

    void showBookListMenu() {
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

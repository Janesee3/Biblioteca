package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!" + System.lineSeparator();
    public static final String QUIT_MESSAGE = "Goodbye!";
    
    // MAIN MENU
    public static final String MENU_CHOICE_LIST_BOOKS = "1";
    public static final String MENU_CHOICE_RETURN_BOOKS = "2";
    public static final String MENU_CHOICE_QUIT = "3";
    public static final String INVALID_MENU_CHOICE = "Invalid selection! Please try again.";
    public static final String MENU = String.format(
            "---- MAIN MENU ----\n" + "[%s] List Books\n" + "[%s] Return Books\n" + "[%s] Quit\n" + System.lineSeparator() + "Please select by typing the corresponding number of the option you want.",
            MENU_CHOICE_LIST_BOOKS,
            MENU_CHOICE_RETURN_BOOKS,
            MENU_CHOICE_QUIT
    );
    
    // BOOK LIST
    public static final String BOOK_LIST_CHOICE_BACK = "b";
    public static final String BOOK_LIST_CHOICE_CHECKOUT = "checkout";
    public static final String BOOK_LIST_CHOICE_INVALID = "Invalid choice, please try again!";
    public static final String BOOK_LIST_TITLE = "---- BOOK LIST ----";
    public static final String BOOK_LIST_TABLE_HEADING = String.format("%-5s | %-20s | %-20s | %-8s", "ID", "Title", "Author", "Year");
    public static final String BOOK_LIST_ITEM = "%-5d | %-20s | %-20s | %-8s";
    public static final String BOOK_LIST_MENU = String.format(
            "[%s] Back To Menu     [%s <book id>] Checkout book",
            BOOK_LIST_CHOICE_BACK,
            BOOK_LIST_CHOICE_CHECKOUT
    );
    public static final String BOOK_LIST_CHECKOUT_INVALID = "That book is not available." + System.lineSeparator();
    public static final String BOOK_LIST_CHECKOUT_SUCCESS = "Thank you! Enjoy the book!" + System.lineSeparator();
    
    
    // RETURN BOOKS
    public static final String RETURN_BOOKS_CHOICE_BACK = "b";
    public static final String RETURN_BOOKS_CHOICE_RETURN = "return";
    public static final String RETURN_BOOKS_CHOICE_INVALID = "Invalid choice, please try again!";
    public static final String RETURN_BOOKS_RETURN_INVALID = "That is not a valid book to return." + System.lineSeparator();
    public static final String RETURN_BOOKS_RETURN_SUCCESS = "Thank you for returning the book." + System.lineSeparator();
    
    public static final String RETURN_BOOKS_MENU = String.format(
            "[%s] Back To Menu     [%s <book id>] Return book",
            RETURN_BOOKS_CHOICE_BACK,
            RETURN_BOOKS_CHOICE_RETURN
    );
    
    
    private Scanner scanner;

    public UserInterface() {}

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

    void showReturnBooksMenu() {
    		show(RETURN_BOOKS_MENU);
    }
    
    private String getBookListDisplayString(ArrayList<Book> list) {
        String displayString = BOOK_LIST_TITLE + System.lineSeparator() + BOOK_LIST_TABLE_HEADING + System.lineSeparator();
        for (Book book: list) {
            String listItemString = String.format(BOOK_LIST_ITEM, book.getIndex(), book.getTitle(), book.getAuthor(), book.getYear());
            displayString += listItemString + System.lineSeparator();
        }
        return displayString;
    }
    
    
}

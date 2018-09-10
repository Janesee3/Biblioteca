package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Response;

public class UserInterface {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
    public static final String QUIT_MESSAGE = "Goodbye!";
    public static final String UNRECOGNISED_ACTION_MESSAGE = "This input cannot be parsed!";
    
    // MAIN MENU
    public static final String MENU_CHOICE_LIST_BOOKS = "1";
    public static final String MENU_CHOICE_RETURN_BOOKS = "2";
    public static final String MENU_CHOICE_LIST_MOVIES = "3";
    public static final String MENU_CHOICE_QUIT = "4";
    public static final String INVALID_MENU_CHOICE = "Invalid selection! Please try again.";
    public static final String MENU = String.format(
            "---- MAIN MENU ----\n" + "[%s] List Books\n" + "[%s] Return Books\n" + "[%s] List Movies\n" + "[%s] Quit\n" + System.lineSeparator() + "Please select by typing the corresponding number of the option you want.",
            MENU_CHOICE_LIST_BOOKS,
            MENU_CHOICE_RETURN_BOOKS,
            MENU_CHOICE_LIST_MOVIES,
            MENU_CHOICE_QUIT
    );
    
    // BOOK LIST
    public static final String BOOK_LIST_CHOICE_BACK = "b";
    public static final String BOOK_LIST_CHOICE_CHECKOUT = "checkout";
    public static final String BOOK_LIST_CHOICE_INVALID = "Invalid choice, please try again!";
    public static final String BOOK_LIST_TITLE = "---- AVAILABLE BOOKS ----";
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
    public static final String RETURN_BOOKS_TITLE = "---- BOOKS TO BE RETURNED ----";
    public static final String RETURN_BOOKS_MENU = String.format(
            "[%s] Back To Menu     [%s <book id>] Return book",
            RETURN_BOOKS_CHOICE_BACK,
            RETURN_BOOKS_CHOICE_RETURN
    );
    
    // MOVIE LIST
    public static final String MOVIE_LIST_CHOICE_BACK = "b";
    public static final String MOVIE_LIST_CHOICE_CHECKOUT = "checkout";
    public static final String MOVIE_LIST_CHOICE_INVALID = "Invalid choice, please try again!";
    public static final String MOVIE_LIST_TITLE = "---- AVAILABLE MOVIES ----";
    public static final String MOVIE_LIST_TABLE_HEADING = String.format("%-5s | %-25s | %-8s | %-20s | %-10s", "ID", "Name", "Year", "Director", "Rating");
    public static final String MOVIE_LIST_ITEM = "%-5d | %-25s | %-8s | %-20s | %-10s";
    public static final String MOVIE_LIST_MENU = String.format(
            "[%s] Back To Menu     [%s <movie id>] Checkout movie",
            MOVIE_LIST_CHOICE_BACK,
            MOVIE_LIST_CHOICE_CHECKOUT
    );
    public static final String MOVIE_LIST_CHECKOUT_INVALID = "That movie is not available." + System.lineSeparator();
    public static final String MOVIE_LIST_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie!" + System.lineSeparator();
    
    
    private Scanner scanner;

    public UserInterface() {}

    public String readUserInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void show(String content) {
    		System.out.println(content);
    }
    
    public void showWelcomeSequence() {
        show(WELCOME_MESSAGE + System.lineSeparator());
    }

    public void showMenu() {
        show(MENU);
    }
    
    public void displayResponse(Response res) {
    		if (!res.getFeedbackString().isEmpty()) {
    			show(res.getFeedbackString());    			
    		}
    		
    		if (!res.getDisplayContent().isEmpty()) {
    			show(res.getDisplayContent());
    		}
    }
  
    public static String getBooksListDisplayString(String listTitle, ArrayList<Book> list, String menu) {
        return String.format("%s\n%s\n\n%s", listTitle, getBooksTableDisplayString(list), menu);
    }
    
    private static String getBooksTableDisplayString(ArrayList<Book> list) {
    		String table = BOOK_LIST_TABLE_HEADING + System.lineSeparator();
        for (Book book: list) {
            String listItemString = String.format(BOOK_LIST_ITEM, book.getIndex(), book.getTitle(), book.getAuthor(), book.getYear());
            table += listItemString + System.lineSeparator();
        }
        return table;
    }
    
    
}

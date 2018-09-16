package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.Response;

public class UserInterface {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
    public static final String QUIT_MESSAGE = "Goodbye!";
    public static final String UNRECOGNISED_ACTION_MESSAGE = "This input cannot be parsed!" + System.lineSeparator();
    public static final String LOGIN_REQUIRED = "Please login from the main menu first!" + System.lineSeparator();
    public static final String USER_INFO = "---- USER INFORMATION ---- \nName: %s\nEmaiL: %s\nPhone Number: %s\n";

    // LOGIN RELATED
    public static final String LOGOUT_YES = "Y";
    public static final String LOGOUT_NO = "N";
    public static final String LOGIN_BACK = "b";
    public static final String LOGIN_PROMPT = "Please enter your library number and password in this format: '<library-number> <password>'\nType 'b' to return to menu.";
    public static final String LOGOUT_PROMPT = "Are you sure you want to logout? " + LOGOUT_YES + " / " + LOGOUT_NO;
    public static final String LOGIN_FAIL_MESSAGE = "Invalid credentials! Please try again" + System.lineSeparator();
    public static final String LOGIN_SUCCESS_MESSAGE = "Login Succeeded!" + System.lineSeparator();
    public static final String LOGOUT_SUCCESS_MESSAGE = "Successfully logged out!" + System.lineSeparator();
    
    // MAIN MENU
    public static final String MENU_TITLE = "---- MAIN MENU ----" + System.lineSeparator();
    public static final String MENU_PROMPT = "Please select by typing the corresponding number of the option you want.";
    public static final String MENU_CHOICE_AUTH = "1";
    public static final String MENU_CHOICE_LIST_BOOKS = "2";
    public static final String MENU_CHOICE_RETURN_BOOKS = "3";
    public static final String MENU_CHOICE_LIST_MOVIES = "4";
    public static final String MENU_CHOICE_RETURN_MOVIES = "5";
    public static final String MENU_CHOICE_VIEW_USER_INFO = "6";
    public static final String MENU_CHOICE_QUIT = "q";
    public static final String INVALID_MENU_CHOICE = "Invalid selection! Please try again.";
    public static final String MENU_LOGIN = "[%s] Login" + System.lineSeparator();
    public static final String MENU_LOGOUT = "[%s] Logout" + System.lineSeparator();
    public static final String MENU_LIST_BOOKS = "[%s] List Books" + System.lineSeparator();
    public static final String MENU_RETURN_BOOKS = "[%s] Return Books" + System.lineSeparator();
    public static final String MENU_LIST_MOVIES = "[%s] List Movies" + System.lineSeparator();
    public static final String MENU_RETURN_MOVIES = "[%s] Return Movies" + System.lineSeparator();
    public static final String MENU_VIEW_USER_INFO = "[%s] User Information" + System.lineSeparator();
    public static final String MENU_QUIT = "[%s] Quit" + System.lineSeparator();
    public static final String MENU_FORMAT_LOGGED_IN = MENU_TITLE
                                                    + MENU_LOGOUT
                                                    + MENU_LIST_BOOKS
                                                    + MENU_RETURN_BOOKS
                                                    + MENU_LIST_MOVIES
                                                    + MENU_RETURN_MOVIES
                                                    + MENU_VIEW_USER_INFO
                                                    + MENU_QUIT + System.lineSeparator()
                                                    + MENU_PROMPT;
   public static final String MENU_FORMAT_NOT_LOGGED_IN = MENU_TITLE
                                                        + MENU_LOGIN
                                                        + MENU_LIST_BOOKS
                                                        + MENU_RETURN_BOOKS
                                                        + MENU_LIST_MOVIES
                                                        + MENU_RETURN_MOVIES
                                                        + MENU_QUIT + System.lineSeparator()
                                                        + MENU_PROMPT;

    // BOOK LIST
    public static final String BOOK_LIST_CHOICE_BACK = "b";
    public static final String BOOK_LIST_CHOICE_CHECKOUT = "checkout";
    public static final String BOOK_LIST_CHOICE_INVALID = "Invalid choice, please try again!" + System.lineSeparator();
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


    // RETURN MOVIES
    public static final String RETURN_MOVIES_CHOICE_BACK = "b";
    public static final String RETURN_MOVIES_CHOICE_RETURN = "return";
    public static final String RETURN_MOVIES_CHOICE_INVALID = "Invalid choice, please try again!";
    public static final String RETURN_MOVIES_RETURN_INVALID = "That is not a valid movie to return." + System.lineSeparator();
    public static final String RETURN_MOVIES_RETURN_SUCCESS = "Thank you for returning the movie." + System.lineSeparator();
    public static final String RETURN_MOVIES_TITLE = "---- MOVIES TO BE RETURNED ----";
    public static final String RETURN_MOVIES_MENU = String.format(
            "[%s] Back To Menu     [%s <movie id>] Return movie",
            RETURN_MOVIES_CHOICE_BACK,
            RETURN_MOVIES_CHOICE_RETURN
    );


    private Scanner scanner;

    public UserInterface() {}

    public String readUserInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void show(String content) {
    		System.out.println(content);
    }

    public void showStartupSequence() {
        show(WELCOME_MESSAGE + System.lineSeparator());
        show(getMenuDisplayString(false));
    }

    public void displayResponse(Response res) {
    		if (!res.getFeedbackString().isEmpty()) {
    			show(res.getFeedbackString());    			
    		}
    		
    		if (!res.getDisplayContent().isEmpty()) {
    			show(res.getDisplayContent());
    		}
    }

    public static String getMenuDisplayString(boolean isLoggedIn) {
        if (isLoggedIn) {
            return String.format(
                    MENU_FORMAT_LOGGED_IN,
                    MENU_CHOICE_AUTH,
                    MENU_CHOICE_LIST_BOOKS,
                    MENU_CHOICE_RETURN_BOOKS,
                    MENU_CHOICE_LIST_MOVIES,
                    MENU_CHOICE_RETURN_MOVIES,
                    MENU_CHOICE_VIEW_USER_INFO,
                    MENU_CHOICE_QUIT
            );
        } else {
            return String.format(
                    MENU_FORMAT_NOT_LOGGED_IN,
                    MENU_CHOICE_AUTH,
                    MENU_CHOICE_LIST_BOOKS,
                    MENU_CHOICE_RETURN_BOOKS,
                    MENU_CHOICE_LIST_MOVIES,
                    MENU_CHOICE_RETURN_MOVIES,
                    MENU_CHOICE_QUIT
            );

        }
    }

    public static String getBooksListDisplayString(String listTitle, ArrayList<Book> list, String menu) {
        return String.format("%s\n%s\n\n%s", listTitle, getBooksTableDisplayString(list), menu);
    }

    public static String getMoviesListDisplayString(String listTitle, ArrayList<Movie> list, String menu) {
        return String.format("%s\n%s\n\n%s", listTitle, getMoviesTableDisplayString(list), menu);
    }

    private static String getBooksTableDisplayString(ArrayList<Book> list) {
    		String table = BOOK_LIST_TABLE_HEADING + System.lineSeparator();
        for (Book book: list) {
            String listItemString = String.format(BOOK_LIST_ITEM, book.getIndex(), book.getTitle(), book.getAuthor(), book.getYear());
            table += listItemString + System.lineSeparator();
        }
        return table;
    }


    private static String getMoviesTableDisplayString(ArrayList<Movie> list) {
    		String table = MOVIE_LIST_TABLE_HEADING + System.lineSeparator();
        for (Movie movie: list) {
            String listItemString = String.format(MOVIE_LIST_ITEM, movie.getIndex(), movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating().toString());
            table += listItemString + System.lineSeparator();
        }
        return table;
    }


}

package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    private UserInterface ui;
    private AppState state;
    private Store store;

    boolean hasQuit;
    
    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.state = AppState.STARTUP;
        this.store = new Store();
        this.hasQuit = false;
    }

    public BibliotecaApp(AppState state) {
        this.state = state;
    }

    public void run() {
    		// this.ui show welcome sequence;
    		// this.ui show main memu
    	
        while (!this.hasQuit) {
       
        	/*
        	 *  	String input = waitforinput();
        	 *  String output = parser.parse(input, state);
        	 * Paser.parse( input,  state) 
        	 * => Parse.match(enum: state==MAIN_MENU, input==LISTBOOKS) 
        	 * => ResponseObj (content In String Form, new State) = logic.execute(ACTION_TYPE) (call Ui Static prettify method)
        	 * => Set the new State.
        	 * => if empty string, dont show, else ui.show(content);
        	 */
        	
            this.processState();
        }
        
        this.ui.show(UserInterface.QUIT_MESSAGE);
    }

    public AppState getState() {
        return this.state;
    }

    public void setState(AppState state) {
        this.state = state;
    }

    private void processState() {
        switch (this.state) {
            case STARTUP:
                this.runStartupSequence();
                break;
            case MAIN_MENU:
                this.runMenuSequence();
                break;
            case LIST_BOOKS:
                this.runListBooksSequence();
                break;
            case RETURN_BOOKS:
            		this.runReturnBooksSequence();
            		break;
        }
    }

    void runStartupSequence() {
        this.ui.showWelcomeSequence();
        this.setState(AppState.MAIN_MENU);
    }

    void runMenuSequence() {
        this.ui.showMenu();
        
        String menuChoice = this.ui.readUserInput();

        if (menuChoice.equals(UserInterface.MENU_CHOICE_LIST_BOOKS)) {
            this.setState(AppState.LIST_BOOKS);
            return;
        }
        
        if (menuChoice.equals(UserInterface.MENU_CHOICE_RETURN_BOOKS)) {
            this.setState(AppState.RETURN_BOOKS);
            return;
        }

        if (menuChoice.equals(UserInterface.MENU_CHOICE_QUIT)) {
            this.hasQuit = true;
            return;
        }

        this.ui.show(UserInterface.INVALID_MENU_CHOICE);
    }

    void runListBooksSequence() {
    		ArrayList<Book> bookList = store.getBooks();
    		
        this.ui.showBookList(bookList);
        this.ui.showBookListMenu();
        
        String menuChoice = this.ui.readUserInput();

        if (menuChoice.equals(UserInterface.BOOK_LIST_CHOICE_BACK)) {
            this.setState(AppState.MAIN_MENU);
            return;
        }

        if (menuChoice.contains(UserInterface.BOOK_LIST_CHOICE_CHECKOUT)) {
            int bookId = getIdFromCheckoutStatement(menuChoice);
            if (bookId < 0) {
                this.ui.show(UserInterface.BOOK_LIST_CHECKOUT_INVALID);
            } else {
                store.checkoutBook(bookId);
                this.ui.show(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS);
            }
            return;
        }
        this.ui.show(UserInterface.BOOK_LIST_CHOICE_INVALID);
    }
    
    void runReturnBooksSequence() {
    	
    }
    
    
    // Parsing related
    
    int getIdFromCheckoutStatement(String statement) {
        String[] wordsArray = statement.split(" ");
        if (wordsArray.length <= 1) {
            return -1;
        }
        try {
            return Integer.parseInt(wordsArray[1]);
        } catch (Exception e) {
            return -1;
        }
    }

}

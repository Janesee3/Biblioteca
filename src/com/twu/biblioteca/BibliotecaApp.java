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
        while (!this.hasQuit) {
            this.processState();
        }
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

        if (menuChoice.equals(UserInterface.MENU_CHOICE_QUIT)) {
            this.hasQuit = true;
            return;
        }

        this.ui.show(UserInterface.INVALID_MENU_CHOICE);
    }

    void runListBooksSequence() {
        this.ui.showBookList(store.getBooks());
        this.ui.showBookListMenu();
        String menuChoice = this.ui.readUserInput();

        if (menuChoice.equals(UserInterface.BOOK_LIST_CHOICE_BACK)) {
            this.setState(AppState.MAIN_MENU);
        }

        if (menuChoice.contains(UserInterface.BOOK_LIST_CHOICE_CHECKOUT)) {
            int bookId = getIdFromCheckoutStatement(menuChoice);
            if (bookId < 0) {
                // Handle invalid case
            } else {
                store.checkoutBook(bookId);
            }
        }

    }

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

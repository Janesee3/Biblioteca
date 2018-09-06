package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    private UserInterface ui;
    private AppState state;
    private Store store;

    private boolean hasQuit;

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
        while (!hasQuit) {
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
            case MAIN_MENU:
                this.runMenuSequence();
            case LIST_BOOKS:
                this.runListBooksSequence();
        }
    }

    private void runStartupSequence() {
        this.ui.showWelcomeSequence();
        this.setState(AppState.MAIN_MENU);
    }

    private void runMenuSequence() {
        this.ui.showMenu();
        String menuChoice = this.ui.readUserInput();

        if (menuChoice.equals("1")) {
            this.setState(AppState.LIST_BOOKS);
            return;
        }

        this.ui.show("Invalid selection! Please try again.");
        runMenuSequence();
    }

    private void runListBooksSequence() {
        this.ui.showBookList(store.getBooks());
        this.ui.readUserInput();
    }


}

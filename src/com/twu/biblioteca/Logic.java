package com.twu.biblioteca;

import java.util.ArrayList;

public class Logic {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";

    private Store store;
    private String displayContent;
    private AppState state;

    public Logic() {
        store = new Store();
        displayContent = WELCOME_MESSAGE;
        state = AppState.STARTUP;
    }

    public ArrayList<Book> getBookList() {
        return store.getBooks();
    }

    public String getDisplayContent() {
        return this.displayContent;
    }

    public void processInput(String input) {
        switch (this.state) {
            case STARTUP:
                displayContent = WELCOME_MESSAGE;
                break;
            default:
                displayContent = "MEOW!";
                break;
        }
    }


}

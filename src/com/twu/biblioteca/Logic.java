package com.twu.biblioteca;

import java.util.ArrayList;

public class Logic {

    private Store store;

    public Logic() {}

    public String[] getBookList() {
        return store.getBooks();
    }

    public String processInput(String input) {
        return "Echoing " + input;
    }




}

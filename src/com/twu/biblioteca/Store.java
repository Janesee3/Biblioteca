package com.twu.biblioteca;

import java.util.ArrayList;

public class Store {

    private String[] books;

    public Store() {
    }

    public void seedBooks(String[] seed) {
        this.books = seed;
    }

    public String[] getBooks() {
        return this.books;
    }
}

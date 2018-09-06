package com.twu.biblioteca;

import java.util.ArrayList;

public class Store {

    private ArrayList<Book> books;

    public Store() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }
}

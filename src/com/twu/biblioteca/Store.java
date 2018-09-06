package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Store {

    private ArrayList<Book> books;

    public Store() {
        books = new ArrayList<Book>(BookSeeder.getSeedData());
    }

    public void seedBooksData(ArrayList<Book> seed) {
        this.books = seed;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>(this.books.stream()
                .filter(book -> !book.getCheckoutStatus()).collect(Collectors.toList()));

        return availableBooks;
    }
}

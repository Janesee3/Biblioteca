package com.twu.biblioteca.Models;

import java.util.ArrayList;

public class User {

    private String libraryNumber;
    private String password;
    private ArrayList<Book> booksBorrowed;

    public User(String libraryNumber, String password) {
        this.password = password;
        this.libraryNumber = libraryNumber;
        this.booksBorrowed = new ArrayList<Book>();
    }

    public String getLibraryNumber() {
        return this.libraryNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Book> getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public void borrowBook(Book book) {
        this.booksBorrowed.add(book);
    }

    public void returnBook(Book book) {
        int bookPosition = this.booksBorrowed.indexOf(book);
        if (bookPosition < 0) {
            return;
        } else {
            this.booksBorrowed.remove(bookPosition);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User thatUser = (User) o;

        return this.libraryNumber == thatUser.libraryNumber && this.password == thatUser.password;
    }
}


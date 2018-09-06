package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private String year;
    private boolean isCheckedOut;


    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public boolean getCheckoutStatus() {
        return this.isCheckedOut;
    }

    public void markAsCheckedOut() {
        this.isCheckedOut = true;
    }

    public void markAsNotCheckedOut() {
        this.isCheckedOut = false;
    }
}

package com.twu.biblioteca.Models;

public class Book {
	
	private int index;
    private String title;
    private String author;
    private String year;
    private boolean isCheckedOut;


    public Book(int index, String title, String author, String year) {
    		this.index = index;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isCheckedOut = false;
    }
    
    public int getIndex() {
    		return this.index;
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

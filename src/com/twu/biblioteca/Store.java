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
    
    public ArrayList<Book> getAllBooks() {
    		return this.books;
    }
    
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>(this.books.stream()
                .filter(book -> !book.getCheckoutStatus()).collect(Collectors.toList()));

        return availableBooks;
    }
    
    public ArrayList<Book> getReturnableBooks() {
        ArrayList<Book> returnableBooks = new ArrayList<Book>(this.books.stream()
                .filter(book -> book.getCheckoutStatus()).collect(Collectors.toList()));

        return returnableBooks;
    }

    public void checkoutBook(int bookId) {
        Book book = this.findBookById(bookId);
        if (book != null) {
        		book.markAsCheckedOut();
        }
    }
    
    public void returnBook(int bookId) {
	    	Book book = this.findBookById(bookId);
	    if (book != null) {
	        	book.markAsNotCheckedOut();
	    }
    }
    
    Book findBookById(int bookId) {
    		for (Book book: this.books) {
    			if (book.getIndex() == bookId) {
    				return book;
    			}
    		}
    		return null;
    }
}

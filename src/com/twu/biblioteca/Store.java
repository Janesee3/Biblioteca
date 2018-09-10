package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;

public class Store {

    private ArrayList<Book> books;
    private ArrayList<Movie> movies;
    
    public Store() {
        books = new ArrayList<Book>(Seeder.getBookSeedData());
        movies = new ArrayList<Movie>(Seeder.getMovieSeedData());
    }

    public void seedBooksData(ArrayList<Book> seed) {
        this.books = seed;
    }
    
    public void seedMoviesData(ArrayList<Movie> seed) {
        this.movies = seed;
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

    public void checkoutBook(int bookId) throws Exception {
        Book book = this.findBookById(bookId);
        if (book != null) {
        		book.markAsCheckedOut();
        } else {
        		throw new Exception("Unable to find book.");
        }
    }
    
    public void returnBook(int bookId) throws Exception {
	    	Book book = this.findBookById(bookId);
	    if (book != null) {
	        	book.markAsNotCheckedOut();
	    } else {
	    		throw new Exception("Unable to find book.");
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

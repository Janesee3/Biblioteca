package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.User;

public class Store {

    private ArrayList<Book> books;
    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    
    public Store() {
        books = new ArrayList<Book>(Seeder.getBookSeedData());
        movies = new ArrayList<Movie>(Seeder.getMovieSeedData());
        users = new ArrayList<User>(Seeder.getUserSeedData());
    }

    public void seedBooksData(ArrayList<Book> seed) {
        this.books = seed;
    }
    
    public void seedMoviesData(ArrayList<Movie> seed) {
        this.movies = seed;
    }

    public void seedUsersData(ArrayList<User> seed) { this.users = seed; }
    
    
    // Books

    
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

    // TODO: Change this to take in User arg as well
    public void checkoutBook(int bookId) throws Exception {
        Book book = this.findBookById(bookId);
        if (book != null) {
        		book.markAsCheckedOut();
        } else {
        		throw new Exception("Unable to find book.");
        }
    }

    // TODO: Change this to take in User arg
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
    
    // Movies
    
    Movie findMovieById(int movieId) {
		for (Movie movie: this.movies) {
			if (movie.getIndex() == movieId) {
				return movie;
			}
		}
			return null;
    		}
    
    public ArrayList<Movie> getAvailableMovies() {
    		ArrayList<Movie> availableMovies = new ArrayList<Movie>(this.movies.stream()
                .filter(movie -> !movie.getCheckoutStatus()).collect(Collectors.toList()));

        return availableMovies;
    }
    
    public ArrayList<Movie> getReturnableMovies() {
        ArrayList<Movie> returnableMovies = new ArrayList<Movie>(this.movies.stream()
                .filter(movie -> movie.getCheckoutStatus()).collect(Collectors.toList()));

        return returnableMovies;
    }
    
    public void checkoutMovie(int movieId) throws Exception {
        Movie movie = this.findMovieById(movieId);
        if (movie != null) {
        		movie.markAsCheckedOut();
        } else {
        		throw new Exception("Unable to find movie.");
        }
    }

    // Users

    User findUserByCredentials(String libNum, String pw) {
        for (User user: users) {
            if (user.getLibraryNumber().equals(libNum) && user.getPassword().equals(pw)) {
                return user;
            }
        }
        return null;
    }


    
    
}

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
    
    
    // Users
    public ArrayList<User> getAllUsers() {
        return this.users;
    }

    // Books

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>(this.books.stream()
                .filter(book -> !book.getCheckoutStatus()).collect(Collectors.toList()));

        return availableBooks;
    }
    
    public ArrayList<Book> getReturnableBooks(String libNum) {
        User matchedUser = findUserByUserNumber(libNum);
        ArrayList<Book> returnableBooks = new ArrayList<Book>(matchedUser.getBooksBorrowed().stream()
                .filter(book -> book.getCheckoutStatus())
                .collect(Collectors.toList()));

        return returnableBooks;
    }

    public void checkoutBook(int bookId, String libNum) throws Exception {
        User matchedUser = findUserByUserNumber(libNum);
        Book book = this.findBookById(bookId);
        if (book != null && matchedUser != null) {
            book.markAsCheckedOut();
            matchedUser.borrowBook(book);
        } else {
            throw new Exception("Unable to find book/user.");
        }
    }

    public void returnBook(int bookId, String libNum) throws Exception {
        User matchedUser = findUserByUserNumber(libNum);
        Book book = this.findBookById(bookId);
	    if (book != null && matchedUser != null) {
            book.markAsNotCheckedOut();
            matchedUser.returnBook(book);
	    } else {
            throw new Exception("Unable to find book/user.");
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
        for (User user: this.users) {
            if (user.getLibraryNumber().equals(libNum) && user.getPassword().equals(pw)) {
                return user;
            }
        }
        return null;
    }

    User findUserByUserNumber(String libNum) {
        for (User user: this.users) {
            if (user.getLibraryNumber().equals(libNum)) {
                return user;
            }
        }
        return null;
    }


    
    
}

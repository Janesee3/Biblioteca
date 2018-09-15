package com.twu.biblioteca.Models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class User {

    private String libraryNumber;
    private String password;
    private ArrayList<Book> booksBorrowed;
    private ArrayList<Movie> moviesBorrowed;

    public User(String libraryNumber, String password) {
        this.password = password;
        this.libraryNumber = libraryNumber;
        this.booksBorrowed = new ArrayList<Book>();
        this.moviesBorrowed = new ArrayList<Movie>();
    }

    public User(User user) {
        this.password = user.password;
        this.libraryNumber = user.libraryNumber;

        this.booksBorrowed = new ArrayList<Book>(user.getBooksBorrowed()
                .stream()
                .map(book -> new Book(book))
                .collect(Collectors.toList()));

        this.moviesBorrowed = new ArrayList<Movie>(user.getMoviesBorrowed()
                .stream()
                .map(movie -> new Movie(movie))
                .collect(Collectors.toList()));

    }

    public String getLibraryNumber() {
        return this.libraryNumber;
    }

    public String getPassword() {
        return this.password;
    }

    // Books related
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

    // Movies related
    public ArrayList<Movie> getMoviesBorrowed() {
        return this.moviesBorrowed;
    }

    public void borrowMovie(Movie movie) {
        this.moviesBorrowed.add(movie);
    }

    public void returnMovie(Movie movie) {
        int moviePosition = this.moviesBorrowed.indexOf(movie);
        if (moviePosition < 0) {
            return;
        } else {
            this.moviesBorrowed.remove(moviePosition);
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

    private ArrayList<Book> cloneBookList(ArrayList<Book> list) {
        return new ArrayList<Book>(list.stream()
                .map(book -> new Book(book))
                .collect(Collectors.toList()));
    }
}


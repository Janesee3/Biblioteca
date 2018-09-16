package com.twu.biblioteca;

import com.twu.biblioteca.Models.User;
import org.junit.Before;
import org.junit.Test;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StoreTest {

    private ArrayList<Book> booksSeed;
    private ArrayList<Movie> moviesSeed;
    private ArrayList<User> usersSeed;
    private Store store;

    @Before
    public void init() {
        this.booksSeed = Seeder.getBookSeedData();
        this.moviesSeed = Seeder.getMovieSeedData();
        this.usersSeed = Seeder.getUserSeedData();
        this.store = new Store();
        this.store.seedBooksData(this.booksSeed);
        this.store.seedMoviesData(this.moviesSeed);
        this.store.seedUsersData(this.usersSeed);
    }

    // Tests for Retrieving Books

    @Test
    public void getAvailableBooksShouldReturnListOfNotCheckedOutBooks() {
        Book toBeBorrowed = booksSeed.get(0);
        toBeBorrowed.markAsCheckedOut();

        ArrayList<Book> books = store.getAvailableBooks();

        assertEquals(booksSeed.size() - 1, books.size());
    }

    @Test
    public void getReturnableBooksShouldReturnListOfCheckedOutBooks() throws Exception {
        String borrowerId = Seeder.TEST_USER_1.getLibraryNumber();
        store.checkoutBook(Seeder.TEST_BOOK_1.getIndex(), borrowerId);

        ArrayList<Book> books = store.getReturnableBooks(borrowerId);

        assertEquals(1, books.size());
    }

    // Tests for checkout books
    
    @Test
    public void shouldCorrectlyCheckoutBookWhenGivenValidId() throws Exception {
        int bookIndex = Seeder.TEST_BOOK_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();
        store.checkoutBook(bookIndex, userNumber);
        assertTrue(store.findBookById(bookIndex).getCheckoutStatus());
        assertEquals(1, store.findUserByUserNumber(userNumber).getBooksBorrowed().size());
    }

    @Test
    public void shouldThrowExceptionWhenCheckoutInvalidBook() {
    		int bookIndex = 9901;

    		try {
                store.checkoutBook(bookIndex, usersSeed.get(0).getLibraryNumber());
                fail("Exception should be thrown.");
            } catch (Exception e) {
                assert (true);
            }
    }

    // Tests for return books

    @Test
    public void shouldCorrectlyReturnBookWhenGivenValidId() throws Exception {
        int bookIndex = Seeder.TEST_BOOK_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();

        store.checkoutBook(bookIndex, userNumber);
        store.returnBook(bookIndex, userNumber);

        User user = store.findUserByUserNumber(userNumber);
        ArrayList<Book> borrowedBooks = user.getBooksBorrowed();
        assertFalse(store.findBookById(bookIndex).getCheckoutStatus());
        assertEquals(0, borrowedBooks.size());
    }

    @Test
    public void shouldThrowExceptionWhenReturnInvalidBook() {
    		int bookIndex = -1;
        try {
            store.returnBook(bookIndex, usersSeed.get(0).getLibraryNumber());
            fail("Exception should be thrown.");
        } catch (Exception e) {
            assert (true);
        }
    }

    // Tests for retrieving movies

    @Test
    public void getAvailableMoviesShouldReturnListOfNotCheckedOutMovies() {
        Movie toBeBorrowed = moviesSeed.get(0);
        toBeBorrowed.markAsCheckedOut();

        ArrayList<Movie> movies = store.getAvailableMovies();

        assertEquals(moviesSeed.size() - 1, movies.size());
    }

    @Test
    public void getReturnableMoviesShouldReturnListOfCheckedOutMovies() throws Exception {
        String borrowerId = Seeder.TEST_USER_1.getLibraryNumber();
        store.checkoutMovie(Seeder.TEST_MOVIE_1.getIndex(), borrowerId);

        ArrayList<Movie> movies = store.getReturnableMovies(borrowerId);

        assertEquals(1, movies.size());
    }

    // Tests for checking out movie

    @Test
    public void shouldCorrectlyCheckoutMovieWhenGivenValidId() throws Exception {
        int movieIndex = Seeder.TEST_MOVIE_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();
        store.checkoutMovie(movieIndex, userNumber);
        assertTrue(store.findMovieById(movieIndex).getCheckoutStatus());
        assertEquals(1, store.findUserByUserNumber(userNumber).getMoviesBorrowed().size());
    }

    @Test
    public void shouldThrowExceptionWhenCheckoutInvalidMovie() {
        int movieIndex = 9901;

        try {
            store.checkoutMovie(movieIndex, usersSeed.get(0).getLibraryNumber());
            fail("Exception should be thrown.");
        } catch (Exception e) {
            assert (true);
        }
    }

    // Tests for returning movie

    @Test
    public void shouldCorrectlyReturnMovieWhenGivenValidId() throws Exception {
        int movieIndex = Seeder.TEST_MOVIE_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();

        store.checkoutMovie(movieIndex, userNumber);
        store.returnMovie(movieIndex, userNumber);

        User user = store.findUserByUserNumber(userNumber);
        ArrayList<Movie> borrowedMovies = user.getMoviesBorrowed();
        assertFalse(store.findMovieById(movieIndex).getCheckoutStatus());
        assertEquals(0, borrowedMovies.size());
    }

    @Test
    public void shouldThrowExceptionWhenReturnInvalidMovie() {
        int bookIndex = -1;
        try {
            store.returnMovie(bookIndex, usersSeed.get(0).getLibraryNumber());
            fail("Exception should be thrown.");
        } catch (Exception e) {
            assert (true);
        }
    }

    // Tests for retrieving Users

    @Test
    public void shouldReturnUserWhenGivenValidCredentials() {
        String libNum = Seeder.TEST_USER_1.getLibraryNumber();
        String pw = Seeder.TEST_USER_1.getPassword();
        User expectedUser = new User(Seeder.TEST_USER_1);
        User user = store.findUserByCredentials(libNum, pw);
        assertEquals(expectedUser, user);
    }

    @Test
    public void shouldReturnNullWhenGivenInvalidCredentials() {
        String libNum = "123123";
        String pw = "zzzasdds";
        User expectedUser = null;
        User user = store.findUserByCredentials(libNum, pw);
        assertEquals(expectedUser, user);
    }
}


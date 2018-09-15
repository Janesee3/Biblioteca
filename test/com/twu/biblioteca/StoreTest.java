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

    @Test
    public void getAvailableBooksShouldReturnListOfNotCheckedOutBooks() {
        Book toBeBorrowed = booksSeed.get(0);
        toBeBorrowed.markAsCheckedOut();
        this.store.seedBooksData(booksSeed);

        ArrayList<Book> books = this.store.getAvailableBooks();

        assertEquals(booksSeed.size() - 1, books.size());
    }

    @Test
    public void getReturnableBooksShouldReturnListOfCheckedOutBooks() throws Exception {
        String borrowerId = Seeder.TEST_USER_1.getLibraryNumber();
        this.store.checkoutBook(Seeder.TEST_BOOK_1.getIndex(), borrowerId);
        ArrayList<Book> books = this.store.getReturnableBooks(borrowerId);

        assertEquals(1, books.size());
    }
    
    
    @Test
    public void shouldCorrectlyCheckoutBookWhenGivenValidId() throws Exception {
        int bookIndex = Seeder.TEST_BOOK_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();
        this.store.checkoutBook(bookIndex, userNumber);
        assertTrue(this.store.findBookById(bookIndex).getCheckoutStatus());
        assertEquals(1, this.store.findUserByUserNumber(userNumber).getBooksBorrowed().size());
    }

    @Test
    public void shouldCorrectlyReturnBookWhenGivenValidId() throws Exception {
        int bookIndex = Seeder.TEST_BOOK_1.getIndex();
        String userNumber = Seeder.TEST_USER_1.getLibraryNumber();

        this.store.checkoutBook(bookIndex, userNumber);
        this.store.returnBook(bookIndex, userNumber);

        User user = this.store.findUserByUserNumber(userNumber);
        ArrayList<Book> borrowedBooks = user.getBooksBorrowed();
        assertFalse(this.store.findBookById(bookIndex).getCheckoutStatus());
        assertEquals(0, borrowedBooks.size());
    }

    @Test
    public void shouldThrowExceptionWhenCheckoutInvalidBook() {
    		int bookIndex = 9901;

    		try {
                this.store.checkoutBook(bookIndex, usersSeed.get(0).getLibraryNumber());
                fail("Exception should be thrown.");
            } catch (Exception e) {
                assert (true);
            }
    }

    @Test
    public void shouldThrowExceptionWhenReturnInvalidBook() {
    		int bookIndex = -1;
        try {
            this.store.returnBook(bookIndex, usersSeed.get(0).getLibraryNumber());
            fail("Exception should be thrown.");
        } catch (Exception e) {
            assert (true);
        }
    }

    @Test
    public void getAvailableMoviesShouldReturnListOfNotCheckedOutMovies() {
        moviesSeed.get(0).markAsCheckedOut();
        this.store.seedMoviesData(moviesSeed);

        ArrayList<Movie> movies = this.store.getAvailableMovies();

        assertEquals(moviesSeed.size() - 1, movies.size());
    }

    @Test
    public void getReturnableMoviesShouldReturnListOfCheckedOutMovies() {
        moviesSeed.get(0).markAsCheckedOut();
        this.store.seedMoviesData(moviesSeed);

        ArrayList<Movie> movies = this.store.getReturnableMovies();

        assertEquals(1, movies.size());
    }


    @Test
    public void shouldCorrectlyCheckoutMovieWhenGivenValidId() throws Exception {
    		int movieIndex = Seeder.TEST_MOVIE_1.getIndex();
    		this.store.checkoutMovie(movieIndex);
    		assertTrue(this.store.findMovieById(movieIndex).getCheckoutStatus());
    }

    @Test
    public void shouldReturnUserWhenGivenValidCredentials() {
        String libNum = Seeder.TEST_USER_1.getLibraryNumber();
        String pw = Seeder.TEST_USER_1.getPassword();
        User expectedUser = new User(libNum, pw);
        User user = this.store.findUserByCredentials(libNum, pw);
        assertEquals(expectedUser, user);
    }

    @Test
    public void shouldReturnNullWhenGivenInvalidCredentials() {
        String libNum = "123123";
        String pw = "zzzasdds";
        User expectedUser = null;
        User user = this.store.findUserByCredentials(libNum, pw);
        assertEquals(expectedUser, user);
    }
}


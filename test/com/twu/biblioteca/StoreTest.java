package com.twu.biblioteca;

import com.twu.biblioteca.Models.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;

import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StoreTest {

    private ArrayList<Book> booksSeed;
    private ArrayList<Movie> moviesSeed;
    private Store store;

    @Before
    public void init() {
        booksSeed = Seeder.getBookSeedData();
        moviesSeed = Seeder.getMovieSeedData();
        store = new Store();
        store.seedBooksData(booksSeed);
        store.seedMoviesData(moviesSeed);
    }

    @Test
    public void getAvailableBooksShouldReturnListOfNotCheckedOutBooks() {
        booksSeed.get(0).markAsCheckedOut();
        store.seedBooksData(booksSeed);

        ArrayList<Book> books = store.getAvailableBooks();

        assertEquals(booksSeed.size() - 1, books.size());
    }
    
    @Test
    public void getReturnableBooksShouldReturnListOfCheckedOutBooks() {
        booksSeed.get(0).markAsCheckedOut();
        store.seedBooksData(booksSeed);

        ArrayList<Book> books = store.getReturnableBooks();

        assertEquals(1, books.size());
    }
    
    
    @Test
    public void shouldCorrectlyCheckoutBookWhenGivenValidId() throws Exception {
    		int bookIndex = Seeder.TEST_BOOK_1.getIndex();
    		store.checkoutBook(bookIndex);
    		assertTrue(store.findBookById(bookIndex).getCheckoutStatus());
    }
    
    @Test
    public void shouldCorrectlyReturnBookWhenGivenValidId() throws Exception {
    		int bookIndex = Seeder.TEST_BOOK_1.getIndex();
    		store.checkoutBook(bookIndex);
    		store.returnBook(bookIndex);
    		assertFalse(store.findBookById(bookIndex).getCheckoutStatus());
    }
    
    @Test
    public void shouldThrowExceptionWhenCheckoutInvalidBook() {
    		int bookIndex = 9901;

    		try {
                store.checkoutBook(bookIndex);
                fail("Exception should be thrown.");
            } catch (Exception e) {
                assert (true);
            }
    }

    @Test
    public void shouldThrowExceptionWhenReturnInvalidBook() {
    		int bookIndex = -1;
        try {
            store.returnBook(bookIndex);
            fail("Exception should be thrown.");
        } catch (Exception e) {
            assert (true);
        }
    }
    
    @Test
    public void getAvailableMoviesShouldReturnListOfNotCheckedOutMovies() {
        moviesSeed.get(0).markAsCheckedOut();
        store.seedMoviesData(moviesSeed);

        ArrayList<Movie> movies = store.getAvailableMovies();

        assertEquals(moviesSeed.size() - 1, movies.size());
    }
    
    @Test
    public void getReturnableMoviesShouldReturnListOfCheckedOutMovies() {
        moviesSeed.get(0).markAsCheckedOut();
        store.seedMoviesData(moviesSeed);

        ArrayList<Movie> movies = store.getReturnableMovies();

        assertEquals(1, movies.size());
    }
    
    
    @Test
    public void shouldCorrectlyCheckoutMovieWhenGivenValidId() throws Exception {
    		int movieIndex = Seeder.TEST_MOVIE_1.getIndex();
    		store.checkoutMovie(movieIndex);
    		assertTrue(store.findMovieById(movieIndex).getCheckoutStatus());
    }

    @Test
    public void shouldReturnUserWhenGivenValidCredentials() {
        String libNum = Seeder.TEST_USER_1.getLibraryNumber();
        String pw = Seeder.TEST_USER_1.getPassword();
        User expectedUser = new User(libNum, pw);
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


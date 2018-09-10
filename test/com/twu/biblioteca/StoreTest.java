package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.twu.biblioteca.Models.Book;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StoreTest {

    private ArrayList<Book> booksSeed;
    private Store store;

    @Before
    public void init() {
        booksSeed = Seeder.getBookSeedData();
        store = new Store();
        store.seedBooksData(booksSeed);
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
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
}


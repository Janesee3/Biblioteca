package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import com.twu.biblioteca.Models.Book;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreTest {

    private ArrayList<Book> booksSeed;
    private Store store;

    @Before
    public void init() {
        booksSeed = BookSeeder.getSeedData();
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
    		int bookIndex = BookSeeder.TEST_BOOK_1.getIndex();
    		store.checkoutBook(bookIndex);
    		assertTrue(store.findBookById(bookIndex).getCheckoutStatus());
    }
    
    @Test
    public void shouldCorrectlyReturnBookWhenGivenValidId() throws Exception {
    		int bookIndex = BookSeeder.TEST_BOOK_1.getIndex();
    		store.checkoutBook(bookIndex);
    		store.returnBook(bookIndex);
    		assertFalse(store.findBookById(bookIndex).getCheckoutStatus());
    }
    
//    @Test
//    public void shouldNotDoAnythingWhenCheckoutInvalidBook() {
//    		int bookIndex = 9901;
//    		store.checkoutBook(bookIndex);
//    		assert(true);
//    }
//    
//    @Test
//    public void shouldNotDoAnythingWhenReturnInvalidBook() {
//    		int bookIndex = -1;
//    		store.checkoutBook(bookIndex);
//    		assert(true);
//    }
}


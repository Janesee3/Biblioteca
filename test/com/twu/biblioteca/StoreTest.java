package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

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
    public void getBooksShouldReturnListOfNotCheckedOutBooks() {
        booksSeed.get(0).markAsCheckedOut();
        store.seedBooksData(booksSeed);

        ArrayList<Book> books = store.getBooks();

        assertEquals(booksSeed.size() - 1, books.size());
    }
}

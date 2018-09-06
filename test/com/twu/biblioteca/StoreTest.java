package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StoreTest {

    private final String[] BOOKS = {"BOOK 1", "BOOK 2"};
    private Store store;

    @Before
    public void init() {
        store = new Store();
        store.seedBooks(BOOKS);
    }

    @Test
    public void getBooksShouldReturnListOfAllBooks() {
        String[] books = store.getBooks();
        assertEquals(books, BOOKS);
    }
}

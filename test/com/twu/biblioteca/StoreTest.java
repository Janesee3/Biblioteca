package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StoreTest {

    private final Book TEST_BOOK_1 = new Book("Book1");
    private final Book TEST_BOOK_2 = new Book("Book2");
    private final Book TEST_BOOK_3 = new Book("Book3");
    private ArrayList<Book> booksSeed;

    private Store store;

    @Before
    public void init() {
        store = new Store();
        setupFixtures();
    }

    private void setupFixtures() {
        booksSeed = new ArrayList<Book>();
        booksSeed.add(TEST_BOOK_1);
        booksSeed.add(TEST_BOOK_2);
        booksSeed.add(TEST_BOOK_3);

        store.addBook(TEST_BOOK_1);
        store.addBook(TEST_BOOK_2);
        store.addBook(TEST_BOOK_3);
    }

    @Test
    public void getBooksShouldReturnListOfAllBooks() {
        ArrayList<Book> books = store.getBooks();
        assertEquals(this.booksSeed.size(), books.size());
    }
}

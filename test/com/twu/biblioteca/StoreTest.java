package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StoreTest {

    private static ArrayList<Book> booksSeed;

    private Store store;

    @BeforeClass
    public static void setupFixtures() {
        booksSeed = BookSeeder.getSeedData();
    }

    @Before
    public void init() {
        store = new Store();
    }


    @Test
    public void getBooksShouldReturnListOfAllBooks() {
        ArrayList<Book> books = store.getBooks();
        assertEquals(this.booksSeed.size(), books.size());
    }
}

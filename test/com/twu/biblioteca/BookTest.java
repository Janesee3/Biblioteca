package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookTest {

    @Test
    public void creatingBookShouldCorrectlyPassAttributes() {
        String title = "Test title";
        String author = "meow";
        String year = "1995";
        Book book = new Book(title, author, year);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getYear());
    }
}

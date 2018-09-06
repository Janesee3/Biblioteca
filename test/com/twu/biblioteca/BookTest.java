package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookTest {

    @Test
    public void creatingBookWithTitleShouldPersist() {
        String title = "Test TItle";
        Book book = new Book(title);
        assertEquals(title, book.getTitle());
    }
}

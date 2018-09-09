package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookTest {
	
	int index = 10;
    String title = "Test title";
    String author = "meow";
    String year = "1995";
    Book book;

    @Before
    public void init() {
        book = new Book(index, title, author, year);
    }

    @Test
    public void creatingBookShouldCorrectlyPassAttributes() {
	    	assertEquals(this.index, this.book.getIndex());    
	    	assertEquals(this.title, this.book.getTitle());
        assertEquals(this.author, this.book.getAuthor());
        assertEquals(this.year, this.book.getYear());
        assertEquals(false, this.book.getCheckoutStatus());
    }

    @Test
    public void checkOutBookShouldUpdateCorrectly() {
        this.book.markAsCheckedOut();
        assertEquals(true, this.book.getCheckoutStatus());
    }

    @Test
    public void unCheckOutBookShouldUpdateCorrectly() {
        this.book.markAsCheckedOut();
        this.book.markAsNotCheckedOut();
        assertEquals(false, this.book.getCheckoutStatus());
    }
}

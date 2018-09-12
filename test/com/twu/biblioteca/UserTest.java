package com.twu.biblioteca;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    public User user;
    public String libNum = "123-1234";
    public String pw = "123";
    public Book MOCK_BOOK = new Book(0, "test title", "test author", "1900");


    @Before
    public void init() {
        user = new User(libNum, pw);
    }

    @Test
    public void shouldCreateUserWithCorrectAttributes() {
        assertEquals(libNum, user.getLibraryNumber());
        assertEquals(pw, user.getPassword());
    }

    @Test
    public void testForEqualsMethod() {
        User expectedUser = new User(libNum, pw);
        assertTrue(user.equals(expectedUser));
    }

    @Test
    public void testForBorrowBook() {
        user.borrowBook(MOCK_BOOK);
        assertEquals(1, user.getBooksBorrowed().size());
    }

    @Test
    public void testForReturnBook() {
        user.borrowBook(MOCK_BOOK);
        user.returnBook(MOCK_BOOK);
        assertEquals(0, user.getBooksBorrowed().size());
    }
}

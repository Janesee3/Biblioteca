package com.twu.biblioteca;

import com.twu.biblioteca.EnumTypes.Rating;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
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
    public Movie MOCK_MOVIE = new Movie(0, "test name", "test director", "2000", Rating.FIVE);


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
    public void shouldCloneUserCorrectly() {
        Book originalBook = new Book(
                MOCK_BOOK.getIndex(),
                MOCK_BOOK.getTitle(),
                MOCK_BOOK.getAuthor(),
                MOCK_BOOK.getYear()
        );
        Movie originalMovie = new Movie(
                MOCK_MOVIE.getIndex(),
                MOCK_MOVIE.getName(),
                MOCK_MOVIE.getDirector(),
                MOCK_MOVIE.getYear(),
                MOCK_MOVIE.getRating()
        );

        user.borrowBook(originalBook);
        user.borrowMovie(originalMovie);

        User clonedUser = new User(user);

        assertEquals(user.getLibraryNumber(), clonedUser.getLibraryNumber());
        assertEquals(user.getPassword(), clonedUser.getPassword());
        assertEquals(1, clonedUser.getBooksBorrowed().size());
        assertEquals(1, clonedUser.getMoviesBorrowed().size());
    }

    @Test
    public void testForEqualsMethod() {
        User user2 = new User(libNum, pw);
        assertTrue(user.equals(user2));
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

    @Test
    public void testForBorrowMovie() {
        user.borrowMovie(MOCK_MOVIE);
        assertEquals(1, user.getMoviesBorrowed().size());
    }

    @Test
    public void testForReturnMovie() {
        user.borrowMovie(MOCK_MOVIE);
        user.returnMovie(MOCK_MOVIE);
        assertEquals(0, user.getMoviesBorrowed().size());
    }
}

package com.twu.biblioteca;

import java.util.ArrayList;

import com.twu.biblioteca.EnumTypes.Rating;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.User;

public class Seeder {

    public static final Book TEST_BOOK_1 = new Book(1, "Harry Potter", "JK Rowling", "1998");
    public static final Book TEST_BOOK_2 = new Book(2, "Game Of Thrones", "G R R Martin", "2004");
    public static final Book TEST_BOOK_3 = new Book(3, "Chi Sweet Home", "Mr MEow", "2010");

    public static final Movie TEST_MOVIE_1 = new Movie(1, "Harry Potter", "JK Rowling", "1998", Rating.FIVE);
    public static final Movie TEST_MOVIE_2 = new Movie(2, "Game Of Thrones", "G R R Martin", "2004", Rating.ONE);
    public static final Movie TEST_MOVIE_3 = new Movie(3, "Chi Sweet Home", "Mr MEow", "2010", Rating.UNRATED);

    public static final User TEST_USER_1 = new User("123-1234", "asd");
    public static final User TEST_USER_2 = new User("111-1111", "pwee");
    public static final User TEST_USER_3 = new User("111-0000", "zzz");

    public static ArrayList<Book> getBookSeedData() {
        ArrayList<Book> seed = new ArrayList<Book>();
        seed.add(new Book(TEST_BOOK_1));
        seed.add(new Book(TEST_BOOK_2));
        seed.add(new Book(TEST_BOOK_3));
        return seed;
    }
    

    public static ArrayList<Movie> getMovieSeedData() {
        ArrayList<Movie> seed = new ArrayList<Movie>();
        seed.add(new Movie(TEST_MOVIE_1));
        seed.add(new Movie(TEST_MOVIE_2));
        seed.add(new Movie(TEST_MOVIE_3));
        return seed;
    }

    public static ArrayList<User> getUserSeedData() {
        ArrayList<User> seed = new ArrayList<User>();
        seed.add(new User(TEST_USER_1));
        seed.add(new User(TEST_USER_2));
        seed.add(new User(TEST_USER_3));
        return seed;
    }


}

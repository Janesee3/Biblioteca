package com.twu.biblioteca;

import java.util.ArrayList;

import com.twu.biblioteca.Models.Book;

public class BookSeeder {

    public static final Book TEST_BOOK_1 = new Book(1, "Harry Potter", "JK Rowling", "1998");
    public static final Book TEST_BOOK_2 = new Book(2, "Game Of Thrones", "G R R Martin", "2004");
    public static final Book TEST_BOOK_3 = new Book(3, "Chi Sweet Home", "Mr MEow", "2010");

    public static ArrayList<Book> getSeedData() {
        ArrayList<Book> seed = new ArrayList<Book>();
        seed.add(TEST_BOOK_1);
        seed.add(TEST_BOOK_2);
        seed.add(TEST_BOOK_3);
        return seed;
    }
}

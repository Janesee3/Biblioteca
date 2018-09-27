package com.twu.biblioteca;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.twu.biblioteca.EnumTypes.Rating;
import com.twu.biblioteca.Models.Movie;

public class MovieTest {
	
	Movie movie;
	
	int index = 10;
    String name = "Test name";
    String director = "meow";
    String year = "1995";
    Rating rating = Rating.THREE;

//    TODO: could be improved - indentation can be more consistent
	 @Before
		public void init() {
			movie = new Movie(index, name, director, year, rating);
		}

		@Test
		public void creatingMovieShouldCorrectlyPassAttributes() {
				assertEquals(this.index, this.movie.getIndex());
				assertEquals(this.name, this.movie.getName());
			assertEquals(this.director, this.movie.getDirector());
			assertEquals(this.year, this.movie.getYear());
			assertEquals(this.rating, this.movie.getRating());
			assertEquals(false, this.movie.getCheckoutStatus());
		}

		@Test
		public void checkOutMovieShouldUpdateCorrectly() {
			this.movie.markAsCheckedOut();
			assertEquals(true, this.movie.getCheckoutStatus());
		}

		@Test
		public void unCheckOutMovieShouldUpdateCorrectly() {
			this.movie.markAsCheckedOut();
			this.movie.markAsNotCheckedOut();
			assertEquals(false, this.movie.getCheckoutStatus());
		}

}

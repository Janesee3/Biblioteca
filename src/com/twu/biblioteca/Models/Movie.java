package com.twu.biblioteca.Models;

import com.twu.biblioteca.EnumTypes.Rating;

public class Movie {

    private int index;
	private String name;
	private String director;
	private String year;
	private Rating rating;
	private boolean isCheckedOut;
	
	public Movie(int index, String name, String director, String year, Rating rating) {
		this.index = index;
		this.name = name;
		this.director = director;
		this.year = year;
		this.rating = rating;
		this.isCheckedOut = false;
	}

	public Movie(Movie movie) {
		this.index = movie.index;
		this.name = movie.name;
		this.director = movie.director;
		this.year = movie.year;
		this.rating = movie.rating;
		this.isCheckedOut = movie.isCheckedOut;
	}
	
	 public int getIndex() {
 		return this.index;
	 }
	 
	 public String getName() {
	     return this.name;
	 }
	
	 public String getDirector() {
	     return this.director;
	 }
	
	 public String getYear() {
	     return this.year;
	 }
	 
	 public Rating getRating() {
		 return this.rating;
	 }
	
	 public boolean getCheckoutStatus() {
	     return this.isCheckedOut;
	 }
	
	 public void markAsCheckedOut() {
	     this.isCheckedOut = true;
	 }
	
	 public void markAsNotCheckedOut() {
	     this.isCheckedOut = false;
	 }
}

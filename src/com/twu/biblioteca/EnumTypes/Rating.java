package com.twu.biblioteca.EnumTypes;

public enum Rating {
	ONE(1, "1"), 
	TWO(2, "2"), 
	THREE(3, "3"), 
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	UNRATED(-1, "Unrated");
	
	private final String TOTAL_SCORE = " / 10";
	private final int ratingNum;
	private final String displayName;
	
	Rating(int ratingNum, String displayName) {
		this.ratingNum = ratingNum;
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		if (this == Rating.UNRATED) { return this.displayName; }
		return this.displayName + TOTAL_SCORE;
	}
}

package com.twu.biblioteca.EnumTypes;

public enum ActionType {

	// NAVIGATION
	GOTO_LIST_BOOKS, 
	GOTO_RETURN_BOOKS, 
	GOTO_LIST_MOVIES,
	GOTO_RETURN_MOVIES,
	BACK_TO_MAIN_MENU,
	GOTO_AUTH,
	QUIT,

	// INVALID
	INVALID_MENU_CHOICE,
	INVALID_LIST_BOOK_MENU_CHOICE,
	INVALID_RETURN_BOOK_MENU_CHOICE,
	INVALID_LIST_MOVIE_MENU_CHOICE,
	INVALID_RETURN_MOVIE_MENU_CHOICE,
	UNRECOGNISED_ACTION,
	INVALID_LOGIN_INPUT,
	INVALID_LOGOUT_INPUT,

	// USER ACTIONS
	CHECKOUT_BOOK,
	RETURN_BOOK,
	CHECKOUT_MOVIE,
	RETURN_MOVIE,
	LOGIN,
	LOGOUT
}

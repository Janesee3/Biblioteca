package com.twu.biblioteca;

import java.util.ArrayList;

public class Logic {
	
	private Store store;
	
	public Logic() {
		this.store = new Store();
	}
	
	public Logic(Store store) {
		this.store = store;
	}
	
	public Response execute(Action action) {
		switch (action.type) {
		case GOTO_LIST_BOOKS:
			return new Response("", getListBooksDisplayContent(), AppState.LIST_BOOKS);
		case GOTO_RETURN_BOOKS:
			return new Response("", getReturnBooksDisplayContent(), AppState.RETURN_BOOKS);
		case QUIT:
			return new Response("", getQuitDisplayContent(), AppState.QUIT);
		case BACK_TO_MAIN_MENU:
			return new Response("", getMainMenuDisplayContent(), AppState.MAIN_MENU);
		case CHECKOUT_BOOK:
			return handleCheckoutBookAction(action.args);
		case RETURN_BOOK:
			return handleReturnBookAction(action.args);
		default:
			return null;
		}
	}
	
	
	Response handleCheckoutBookAction(ArrayList<Object> args) {
		Response invalidRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_INVALID, 
											getListBooksDisplayContent(),
											AppState.LIST_BOOKS);	
		try {
			Integer bookId = (Integer) args.get(0);
			this.store.checkoutBook(bookId);
			return new Response(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS, 
								getListBooksDisplayContent(),
								AppState.LIST_BOOKS);
		} catch (Exception e) {
			return invalidRes;
		}
	}
	
	private Response handleReturnBookAction(ArrayList<Object> args) {
		Response invalidRes = new Response(UserInterface.RETURN_BOOKS_RETURN_INVALID, 
											getReturnBooksDisplayContent(),
											AppState.RETURN_BOOKS);	
		try {
			Integer bookId = (Integer) args.get(0);
			this.store.returnBook(bookId);
			return new Response(UserInterface.RETURN_BOOKS_RETURN_SUCCESS, 
								getReturnBooksDisplayContent(), 
								AppState.RETURN_BOOKS);
		} catch (Exception e) {
			return invalidRes;
		}
	}
	
	// Methods to retrieve Display Content from UserInterface 
	
	String getListBooksDisplayContent() {
		return UserInterface.getBooksListDisplayString(UserInterface.BOOK_LIST_TITLE, 
				this.store.getAvailableBooks(), UserInterface.BOOK_LIST_MENU);
	}
	
	String getReturnBooksDisplayContent() {
		return UserInterface.getBooksListDisplayString(UserInterface.RETURN_BOOKS_TITLE, 
				this.store.getReturnableBooks(), UserInterface.RETURN_BOOKS_MENU);
	}
	
	String getQuitDisplayContent() {
		return UserInterface.QUIT_MESSAGE;
	}
	
	String getMainMenuDisplayContent() {
		return UserInterface.MENU;
	}
	
	

}

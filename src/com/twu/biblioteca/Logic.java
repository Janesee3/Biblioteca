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
			return new Response("", AppState.LIST_BOOKS);
		case GOTO_RETURN_BOOKS:
			return new Response("", AppState.RETURN_BOOKS);
		case QUIT:
			return new Response("", AppState.QUIT);
		case BACK_TO_MAIN_MENU:
			return new Response("", AppState.MAIN_MENU);
		case CHECKOUT_BOOK:
			return handleCheckoutBookAction(action.args);
		case RETURN_BOOK:
			return handleReturnBookAction(action.args);
		default:
			return null;
		}
	}
	
	private Response handleCheckoutBookAction(ArrayList<Object> args) {
		Response invalidRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_INVALID, AppState.LIST_BOOKS);	
		try {
			Integer bookId = (Integer) args.get(0);
			this.store.checkoutBook(bookId);
			return new Response(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS, AppState.LIST_BOOKS);
		} catch (Exception e) {
			return invalidRes;
		}
	}
	
	private Response handleReturnBookAction(ArrayList<Object> args) {
		Response invalidRes = new Response(UserInterface.RETURN_BOOKS_RETURN_INVALID, AppState.RETURN_BOOKS);	
		try {
			Integer bookId = (Integer) args.get(0);
			this.store.returnBook(bookId);
			return new Response(UserInterface.RETURN_BOOKS_RETURN_SUCCESS, AppState.RETURN_BOOKS);
		} catch (Exception e) {
			return invalidRes;
		}
	}
	
	
	

}

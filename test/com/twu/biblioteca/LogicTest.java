package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	
	/* 
	INVALID_MENU_CHOICE,
	INVALID_LIST_BOOK_MENU_CHOICE,
	INVALID_RETURN_BOOK_MENU_CHOICE,
	UNRECOGNISED_ACTION
	 */
	
	private Logic logic;
	private Store store;
	private ArrayList<Book> bookSeed = BookSeeder.getSeedData();
	
	@Before
	public void setup() {
		this.store = new Store();
		this.logic = new Logic(this.store);
		store.seedBooksData(bookSeed);
	}
	
	
	private String getListBooksDisplayContent() {
		return this.logic.getListBooksDisplayContent();
	}
	
	private String getReturnBooksDisplayContent() {
		return this.logic.getReturnBooksDisplayContent();
	}
	
	@Test
	public void testExecuteGoToListBooksAction() {
		Action action = new Action(ActionType.GOTO_LIST_BOOKS);
		Response expectedRes = new Response("", getListBooksDisplayContent(), AppState.LIST_BOOKS);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteGoToReturnBooksAction() {
		Action action = new Action(ActionType.GOTO_RETURN_BOOKS);
		Response expectedRes = new Response("", getReturnBooksDisplayContent(), AppState.RETURN_BOOKS);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteBackToMenuAction() {
		Action action = new Action(ActionType.BACK_TO_MAIN_MENU);
		Response expectedRes = new Response("", this.logic.getMainMenuDisplayContent(), AppState.MAIN_MENU);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteQuitAction() {
		Action action = new Action(ActionType.QUIT);
		Response expectedRes = new Response("", this.logic.getQuitDisplayContent(), AppState.QUIT);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		Integer bookId = this.bookSeed.get(0).getIndex();
		Action action = new Action(ActionType.CHECKOUT_BOOK, bookId);
		
		Response res = logic.execute(action);
		Response expectedRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS, 
				this.logic.getListBooksDisplayContent(), 
				AppState.LIST_BOOKS);
		
		assertEquals(expectedRes, res);
		assertEquals(1, store.getReturnableBooks().size());
	}
	
	@Test
	public void testExecuteInvalidCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		
		Action action = new Action(ActionType.CHECKOUT_BOOK, 1231);
		Response expectedRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_INVALID,
											this.logic.getListBooksDisplayContent(), 
											AppState.LIST_BOOKS);
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.CHECKOUT_BOOK, "asdas");
		res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.CHECKOUT_BOOK);
		res = logic.execute(action);
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteReturnBookAction() throws Exception {
		Integer bookId = this.bookSeed.get(0).getIndex();
		store.seedBooksData(this.bookSeed);
		store.checkoutBook(bookId);
		
		Action action = new Action(ActionType.RETURN_BOOK, bookId);
		
		Response res = logic.execute(action);
		Response expectedRes = new Response(UserInterface.RETURN_BOOKS_RETURN_SUCCESS, 
				this.logic.getReturnBooksDisplayContent(), 
				AppState.RETURN_BOOKS);
		
		assertEquals(expectedRes, res);
		assertEquals(this.bookSeed.size(), store.getAvailableBooks().size());
	}
	
	@Test
	public void testExecuteInvalidReturnBookAction() {
		store.seedBooksData(this.bookSeed);
		
		Action action = new Action(ActionType.RETURN_BOOK, 1231);
		Response expectedRes = new Response(UserInterface.RETURN_BOOKS_RETURN_INVALID, 
											this.logic.getReturnBooksDisplayContent(),
											AppState.RETURN_BOOKS);	
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.RETURN_BOOK, "asdas");
		res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.RETURN_BOOK);
		res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

}

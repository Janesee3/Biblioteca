package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	
	/* 
	INVALID_MENU_CHOICE,
	CHECKOUT_BOOK,
	INVALID_LIST_BOOK_MENU_CHOICE,
	RETURN_BOOK,
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
	}
	
	@Test
	public void testExecuteGoToListBooksAction() {
		Action action = new Action(ActionType.GOTO_LIST_BOOKS);
		Response expectedRes = new Response("", AppState.LIST_BOOKS);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteGoToReturnBooksAction() {
		Action action = new Action(ActionType.GOTO_RETURN_BOOKS);
		Response expectedRes = new Response("", AppState.RETURN_BOOKS);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteBackToMenuAction() {
		Action action = new Action(ActionType.BACK_TO_MAIN_MENU);
		Response expectedRes = new Response("", AppState.MAIN_MENU);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteQuitAction() {
		Action action = new Action(ActionType.QUIT);
		Response expectedRes = new Response("", AppState.QUIT);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		Integer bookId = this.bookSeed.get(0).getIndex();
		Action action = new Action(ActionType.CHECKOUT_BOOK, bookId);
		Response expectedRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS, AppState.LIST_BOOKS);
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
		assertEquals(this.bookSeed.size() - 1, store.getAvailableBooks().size());
	}
	
	@Test
	public void testExecuteInvalidCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		
		Action action = new Action(ActionType.CHECKOUT_BOOK, 1231);
		Response expectedRes = new Response(UserInterface.BOOK_LIST_CHECKOUT_INVALID, AppState.LIST_BOOKS);	
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.CHECKOUT_BOOK, "asdas");
		res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.CHECKOUT_BOOK);
		res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

}

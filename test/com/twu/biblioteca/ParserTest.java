package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserTest {
	
	// Test for parsing main menu state input
	
	@Test
	public void parseListBookSelectionReturnsCorrectAction() {
		String input = UserInterface.MENU_CHOICE_LIST_BOOKS;
		Action expectedAction = new Action(ActionType.GOTO_LIST_BOOKS);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseReturnBooksSelectionReturnsCorrectAction() {
		String input = UserInterface.MENU_CHOICE_RETURN_BOOKS;
		Action expectedAction = new Action(ActionType.GOTO_RETURN_BOOKS);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseQuitSelectionReturnsCorrectAction() {
		String input = UserInterface.MENU_CHOICE_QUIT;
		Action expectedAction = new Action(ActionType.QUIT);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseInvalidMenuSelectionReturnsCorrectAction() {
		String input = "12312321";
		Action expectedAction = new Action(ActionType.INVALID_MENU_CHOICE);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}
	
	// Test for parsing list book menu input
	
	@Test
	public void parseBackFromListBookReturnsCorrectAction() {
		String input = UserInterface.BOOK_LIST_CHOICE_BACK;
		Action expectedAction = new Action(ActionType.BACK_TO_MAIN_MENU);
		Action action = Parser.parse(AppState.LIST_BOOKS, input);
		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseCheckoutBookReturnsCorrectActionWithArg() {
		Integer bookId = 1;
		String input = UserInterface.BOOK_LIST_CHOICE_CHECKOUT + " " + bookId;
		Action expectedAction = new Action(ActionType.CHECKOUT_BOOK);
		expectedAction.addArg(bookId);
		
		Action action = Parser.parse(AppState.LIST_BOOKS, input);

		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseCheckoutInvalidBookReturnsCorrectActionWithArg() {
		String input = UserInterface.BOOK_LIST_CHOICE_CHECKOUT + " " + "absdad";
		Action expectedAction = new Action(ActionType.CHECKOUT_BOOK);
		expectedAction.addArg(Parser.PARSED_INVALID_ID);
		
		Action action = Parser.parse(AppState.LIST_BOOKS, input);

		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseListBookInvalidSelectionReturnsCorrectAction() {
		String input = "asdsad";
		Action expectedAction = new Action(ActionType.INVALID_LIST_BOOK_MENU_CHOICE);
		Action action = Parser.parse(AppState.LIST_BOOKS, input);
		assertEquals(expectedAction, action);
	}
	

}

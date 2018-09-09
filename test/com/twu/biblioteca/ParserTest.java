package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserTest {
	
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
	public void parseListQuitSelectionReturnsCorrectAction() {
		String input = UserInterface.MENU_CHOICE_QUIT;
		Action expectedAction = new Action(ActionType.QUIT);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}
	
	@Test
	public void parseListInvalidMenuSelectionReturnsCorrectAction() {
		String input = "12312321";
		Action expectedAction = new Action(ActionType.INVALID_MENU_CHOICE);
		Action action = Parser.parse(AppState.MAIN_MENU, input);
		assertEquals(expectedAction, action);
	}

}

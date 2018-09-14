package com.twu.biblioteca;

import com.twu.biblioteca.EnumTypes.ActionType;
import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;

public class Parser {
	
	public static Integer PARSED_INVALID_ID = -1;
	
	public static Action parse(AppState state, String input) {

		switch (state) {
			case MAIN_MENU:
				return Parser.parseMainMenuSelection(input);
			case LIST_BOOKS:
				return Parser.parseListBookMenuSelection(input);
			case LIST_MOVIES:
				return Parser.parseListMovieMenuSelection(input);
			case RETURN_BOOKS:
				return Parser.parseReturnBookMenuSelection(input);
			case LOGIN:
				return Parser.parseLogin(input);
			default:
				return new Action(ActionType.UNRECOGNISED_ACTION);
		}
	}
	
	// Example of action statement: "<action> <id>"
	public static int getIdFromActionStatement(String statement) {
        String[] wordsArray = statement.split(" ");
        if (wordsArray.length <= 1) { return PARSED_INVALID_ID; }
        try {
            return Integer.parseInt(wordsArray[1]);
        } catch (Exception e) {
            return PARSED_INVALID_ID;
        }
    }
	
	private static Action parseMainMenuSelection(String input) {
		switch (input) {
			case UserInterface.MENU_CHOICE_AUTH:
				return new Action(ActionType.LOGIN);
			case UserInterface.MENU_CHOICE_LIST_BOOKS:
				return new Action(ActionType.GOTO_LIST_BOOKS);
			case UserInterface.MENU_CHOICE_LIST_MOVIES:
				return  new Action(ActionType.GOTO_LIST_MOVIES);
			case UserInterface.MENU_CHOICE_QUIT:
				return new Action(ActionType.QUIT);
			case UserInterface.MENU_CHOICE_RETURN_BOOKS:
				return new Action(ActionType.GOTO_RETURN_BOOKS);
			default:
				return new Action(ActionType.INVALID_MENU_CHOICE);
		}
	}
	
	private static Action parseListBookMenuSelection(String input) {
		if (input.equals(UserInterface.BOOK_LIST_CHOICE_BACK)) {
			return new Action(ActionType.BACK_TO_MAIN_MENU);
		}
		
		if (input.contains(UserInterface.BOOK_LIST_CHOICE_CHECKOUT)) {
			return Parser.createActionFromCommandWithId(ActionType.CHECKOUT_BOOK, input);
		}
		return new Action(ActionType.INVALID_LIST_BOOK_MENU_CHOICE);
	}
	
	private static Action parseListMovieMenuSelection(String input) {
		if (input.equals(UserInterface.MOVIE_LIST_CHOICE_BACK)) {
			return new Action(ActionType.BACK_TO_MAIN_MENU);
		}
		
		if (input.contains(UserInterface.MOVIE_LIST_CHOICE_CHECKOUT)) {
			return Parser.createActionFromCommandWithId(ActionType.CHECKOUT_MOVIE, input);
		}
		return new Action(ActionType.INVALID_LIST_MOVIE_MENU_CHOICE);
	}
	
	private static Action parseReturnBookMenuSelection(String input) {
		if (input.equals(UserInterface.RETURN_BOOKS_CHOICE_BACK)) {
			return new Action(ActionType.BACK_TO_MAIN_MENU);
		}
		
		if (input.contains(UserInterface.RETURN_BOOKS_CHOICE_RETURN)) {
			return Parser.createActionFromCommandWithId(ActionType.RETURN_BOOK, input);
		}
		return new Action(ActionType.INVALID_RETURN_BOOK_MENU_CHOICE);
	}
	
	private static Action createActionFromCommandWithId(ActionType type, String input) {
		Integer id = Parser.getIdFromActionStatement(input);
		return new Action(type, id);
	}

	private static Action parseLogin(String input) {
		if (input.equals(UserInterface.LOGIN_BACK)) {
			return new Action(ActionType.BACK_TO_MAIN_MENU);
		}
		String[] parsedCredentials = input.split(" ");
		if (parsedCredentials.length == 2) {
			return new Action(ActionType.LOGIN, parsedCredentials[0], parsedCredentials[1]);
		}
		return new Action(ActionType.INVALID_LOGIN_INPUT);
	}
}

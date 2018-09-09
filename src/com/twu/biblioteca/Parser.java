package com.twu.biblioteca;

public class Parser {
	
	public static Integer PARSED_INVALID_ID = -1;
	
	public static Action parse(AppState state, String input) {
		
		if (state == AppState.MAIN_MENU) {
			return Parser.parseMainMenuSelection(input);
		}
		
		if (state == AppState.LIST_BOOKS) {
			return Parser.parseListBookMenuSelection(input);
		}
		
		return null;
	}
	
	// Example of action statement: "<action> <id>"
	public static int getIdFromActionStatement(String statement) {
        String[] wordsArray = statement.split(" ");
        if (wordsArray.length <= 1) {
            return PARSED_INVALID_ID;
        }
        try {
            return Integer.parseInt(wordsArray[1]);
        } catch (Exception e) {
            return PARSED_INVALID_ID;
        }
    }
	
	private static Action parseMainMenuSelection(String input) {
		if (input.equals(UserInterface.MENU_CHOICE_LIST_BOOKS)) {
			return new Action(ActionType.GOTO_LIST_BOOKS);
		}
		
		if (input.equals(UserInterface.MENU_CHOICE_QUIT)) {
			return new Action(ActionType.QUIT);
		}
		
		if (input.equals(UserInterface.MENU_CHOICE_RETURN_BOOKS)) {
			return new Action(ActionType.GOTO_RETURN_BOOKS);
		}
		return new Action(ActionType.INVALID_MENU_CHOICE);	
	}
	
	private static Action parseListBookMenuSelection(String input) {
		if (input.equals(UserInterface.BOOK_LIST_CHOICE_BACK)) {
			return new Action(ActionType.BACK_TO_MAIN_MENU);
		}
		
		if (input.contains(UserInterface.BOOK_LIST_CHOICE_CHECKOUT)) {
			Integer id = Parser.getIdFromActionStatement(input);
			Action action = new Action(ActionType.CHECKOUT_BOOK);
			action.addArg(id);
			return action;
		}
		
		return new Action(ActionType.INVALID_LIST_BOOK_MENU_CHOICE);
	}
}

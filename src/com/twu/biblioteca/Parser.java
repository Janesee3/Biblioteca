package com.twu.biblioteca;

public class Parser {
	
	public static Action parse(AppState state, String input) {
		
		if (state == AppState.MAIN_MENU) {
			return Parser.parseMainMenuSelection(input);
		}
		
		return null;
	}
	
	// Example of action statement: "<action> <id>"
	public static int getIdFromActionStatement(String statement) {
        String[] wordsArray = statement.split(" ");
        if (wordsArray.length <= 1) {
            return -1;
        }
        try {
            return Integer.parseInt(wordsArray[1]);
        } catch (Exception e) {
            return -1;
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
}

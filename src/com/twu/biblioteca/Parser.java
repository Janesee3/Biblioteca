package com.twu.biblioteca;

public class Parser {
	
	public static Action parse(AppState state, String input) {
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

}

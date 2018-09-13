package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.twu.biblioteca.EnumTypes.ActionType;
import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;

public class ParserTest {
	
	// Test for parsing main menu state input
	
//	@Test
//	public void parseLoginSelectionReturnsCorrectAction() {
//		String input = UserInterface.MENU_CHOICE_LIST_BOOKS;
//		Action expectedAction = new Action(ActionType.GOTO_LIST_BOOKS);
//		Action action = Parser.parse(AppState.MAIN_MENU, input);
//		assertEquals(expectedAction, action);
//	}
	
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
	public void parseListMovieSelectionReturnsCorrectAction() {
		String input = UserInterface.MENU_CHOICE_LIST_MOVIES;
		Action expectedAction = new Action(ActionType.GOTO_LIST_MOVIES);
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
	
	
	// Test for parsing return book menu input
	
		@Test
		public void parseBackFromReturnBookReturnsCorrectAction() {
			String input = UserInterface.RETURN_BOOKS_CHOICE_BACK;
			Action expectedAction = new Action(ActionType.BACK_TO_MAIN_MENU);
			Action action = Parser.parse(AppState.RETURN_BOOKS, input);
			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseReturnBookGivesCorrectActionWithArg() {
			Integer bookId = 1;
			String input = UserInterface.RETURN_BOOKS_CHOICE_RETURN + " " + bookId;
			Action expectedAction = new Action(ActionType.RETURN_BOOK);
			expectedAction.addArg(bookId);
			
			Action action = Parser.parse(AppState.RETURN_BOOKS, input);

			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseReturnInvalidBookGivesCorrectActionWithArg() {
			String input = UserInterface.RETURN_BOOKS_CHOICE_RETURN + " " + "absdad";
			Action expectedAction = new Action(ActionType.RETURN_BOOK);
			expectedAction.addArg(Parser.PARSED_INVALID_ID);
			
			Action action = Parser.parse(AppState.RETURN_BOOKS, input);

			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseReturnBookInvalidSelectionGivesCorrectAction() {
			String input = "asdsad";
			Action expectedAction = new Action(ActionType.INVALID_RETURN_BOOK_MENU_CHOICE);
			Action action = Parser.parse(AppState.RETURN_BOOKS, input);
			assertEquals(expectedAction, action);
		}
		
		// Test for parsing list movie menu input
		
		@Test
		public void parseBackFromListMoviesReturnsCorrectAction() {
			String input = UserInterface.MOVIE_LIST_CHOICE_BACK;
			Action expectedAction = new Action(ActionType.BACK_TO_MAIN_MENU);
			Action action = Parser.parse(AppState.LIST_MOVIES, input);
			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseCheckoutMovieReturnsCorrectActionWithArg() {
			Integer movieId = 1;
			String input = UserInterface.MOVIE_LIST_CHOICE_CHECKOUT + " " + movieId;
			Action expectedAction = new Action(ActionType.CHECKOUT_MOVIE);
			expectedAction.addArg(movieId);
			
			Action action = Parser.parse(AppState.LIST_MOVIES, input);

			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseCheckoutInvalidMovieReturnsCorrectActionWithArg() {
			String input = UserInterface.MOVIE_LIST_CHOICE_CHECKOUT + " " + "absdad";
			Action expectedAction = new Action(ActionType.CHECKOUT_MOVIE);
			expectedAction.addArg(Parser.PARSED_INVALID_ID);
			
			Action action = Parser.parse(AppState.LIST_MOVIES, input);

			assertEquals(expectedAction, action);
		}
		
		@Test
		public void parseListMovieInvalidSelectionReturnsCorrectAction() {
			String input = "asdsad";
			Action expectedAction = new Action(ActionType.INVALID_LIST_MOVIE_MENU_CHOICE);
			Action action = Parser.parse(AppState.LIST_MOVIES, input);
			assertEquals(expectedAction, action);
		}
		
		/*** Test for Parsing Action Statement ***/

	    @Test
	    public void shouldParseIdFromCheckoutStatement() {
	        int id = 10;
	        String input = "checkout " + id;
	        assertEquals(id, Parser.getIdFromActionStatement(input));
	    }

	    @Test
	    public void shouldReturnNegativeIfInvalidCheckoutStatement() {
	        int id = -1;
	        String input = "checkout " + "dasdad";
	        assertEquals(id, Parser.getIdFromActionStatement(input));

	        input = "checkout";
	        assertEquals(id, Parser.getIdFromActionStatement(input));
	    }
	    
	    @Test
	    public void shouldParseIdFromReturnStatement() {
	        int id = 10;
	        String input = "return " + id;
	        assertEquals(id, Parser.getIdFromActionStatement(input));
	    }

	    @Test
	    public void shouldReturnNegativeIfInvalidReturnStatement() {
	        int id = -1;
	        String input = "return " + "dasdad";
	        assertEquals(id, Parser.getIdFromActionStatement(input));

	        input = "return";
	        assertEquals(id, Parser.getIdFromActionStatement(input));
	    }
		

}

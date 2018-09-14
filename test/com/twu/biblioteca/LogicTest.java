package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.twu.biblioteca.EnumTypes.ActionType;
import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.Response;
import com.twu.biblioteca.Models.User;

public class LogicTest implements UserDelegate {
	
	private Logic logic;
	private Store store;
	private ArrayList<Book> bookSeed = Seeder.getBookSeedData();
	private ArrayList<Movie> movieSeed = Seeder.getMovieSeedData();
	private ArrayList<User> userSeed = Seeder.getUserSeedData();
	
	private final String USER_NUM = userSeed.get(0).getLibraryNumber();
	private final String USER_PW = userSeed.get(0).getPassword();

	// Implementing mock userDelegate
    private UserDelegate userDelegate;
	private User currentUser;
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void logUserIn(User user) { currentUser = user; }
    public void logUserOut() { currentUser = null; }
	
	@Before
	public void setup() {
		store = new Store();
		logic = new Logic(store);
		userDelegate = this;
		logic.setUserDelegate(userDelegate);

		currentUser = new User(USER_NUM, USER_PW);

		store.seedBooksData(bookSeed);
		store.seedMoviesData(movieSeed);
		store.seedUsersData(userSeed);
	}
	
	
	private String getListBooksDisplayContent() {
		return this.logic.getListBooksDisplayContent();
	}
	
	private String getReturnBooksDisplayContent() {
		return this.logic.getReturnBooksDisplayContent();
	}
	
	private String getListMoviesDisplayContent() {
		return this.logic.getListMoviesDisplayContent();
	}


	// Test for Navigation Actions

    @Test
    public void testExecuteGoToAuthActionWhenNotLoggedIn() {
	    currentUser = null;

        Action action = new Action(ActionType.GOTO_AUTH);
        Response expectedRes = new Response("", logic.getLoginDisplayContent(), AppState.LOGIN);

        Response res = logic.execute(action);

        assertEquals(expectedRes, res);
    }

    @Test
    public void testExecuteGoToAuthActionWhenLoggedIn() {
        Action action = new Action(ActionType.GOTO_AUTH);
        Response expectedRes = new Response("", logic.getLogoutDisplayContent(), AppState.LOGOUT);

        Response res = logic.execute(action);

        assertEquals(expectedRes, res);
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
	public void testExecuteGoToReturnBooksWhenNotLoggedIn() {
		this.currentUser = null;
		
		Action action = new Action(ActionType.GOTO_RETURN_BOOKS);
		Response expectedRes = new Response(
		        UserInterface.LOGIN_REQUIRED,
				this.logic.getMainMenuDisplayContent(false),
				AppState.MAIN_MENU
        );
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteGoToListMoviesAction() {
		Action action = new Action(ActionType.GOTO_LIST_MOVIES);
		Response expectedRes = new Response(
		        "",
                getListMoviesDisplayContent(),
                AppState.LIST_MOVIES
        );
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	

	@Test
	public void testExecuteInvalideMenuAction() {
		Action action = new Action(ActionType.INVALID_MENU_CHOICE);
		Response expectedRes = new Response(
		        UserInterface.INVALID_MENU_CHOICE,
				logic.getMainMenuDisplayContent(userDelegate.isLoggedIn()),
				AppState.MAIN_MENU
        );
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteBackToMenuAction() {
		Action action = new Action(ActionType.BACK_TO_MAIN_MENU);
		Response expectedRes = new Response(
		        "",
                logic.getMainMenuDisplayContent(userDelegate.isLoggedIn()),
                AppState.MAIN_MENU
        );
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteQuitAction() {
		Action action = new Action(ActionType.QUIT);
		Response expectedRes = new Response(
		        "",
                this.logic.getQuitDisplayContent(),
                AppState.QUIT
        );
		
		Response res = logic.execute(action);
		
		assertEquals(expectedRes, res);
	}

    // Tests for Checkout Book Action
	
	@Test
	public void testExecuteCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		Integer bookId = this.bookSeed.get(0).getIndex();
		Action action = new Action(ActionType.CHECKOUT_BOOK, bookId);
		
		Response res = logic.execute(action);
		Response expectedRes = new Response(
		        UserInterface.BOOK_LIST_CHECKOUT_SUCCESS,
				this.logic.getListBooksDisplayContent(), 
				AppState.LIST_BOOKS
        );
		
		assertEquals(expectedRes, res);
		assertEquals(1, store.getReturnableBooks().size());
	}
	
	@Test
	public void testExecuteIllegalCheckoutBookAction() {
		store.seedBooksData(this.bookSeed);
		
		Action action = new Action(ActionType.CHECKOUT_BOOK, 1231);
		Response expectedRes = new Response(
		        UserInterface.BOOK_LIST_CHECKOUT_INVALID,
                logic.getListBooksDisplayContent(),
                AppState.LIST_BOOKS
        );
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
	public void testExecuteInvalidCheckoutBookAction() {
		Action action = new Action(ActionType.INVALID_LIST_BOOK_MENU_CHOICE);
		Response expectedRes = new Response(
		        UserInterface.BOOK_LIST_CHOICE_INVALID,
                logic.getListBooksDisplayContent(),
                AppState.LIST_BOOKS
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testCheckoutBookWhenNotLoggedIn() {
		this.currentUser = null;
		Action action = new Action(ActionType.CHECKOUT_BOOK);
		Response expectedRes = new Response(
		        UserInterface.LOGIN_REQUIRED,
                logic.getListBooksDisplayContent(),
                AppState.LIST_BOOKS
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

	// Tests for Return book action
	
	@Test
	public void testExecuteReturnBookAction() throws Exception {
		Integer bookId = this.bookSeed.get(0).getIndex();
		store.seedBooksData(this.bookSeed);
		store.checkoutBook(bookId);
		
		Action action = new Action(ActionType.RETURN_BOOK, bookId);
		
		Response res = logic.execute(action);
		Response expectedRes = new Response(
		        UserInterface.RETURN_BOOKS_RETURN_SUCCESS,
				logic.getReturnBooksDisplayContent(),
				AppState.RETURN_BOOKS
        );
		
		assertEquals(expectedRes, res);
		assertEquals(this.bookSeed.size(), store.getAvailableBooks().size());
	}
	
	@Test
	public void testExecuteIllegalReturnBookAction() {
		store.seedBooksData(this.bookSeed);
		
		Action action = new Action(ActionType.RETURN_BOOK, 1231);
		Response expectedRes = new Response(
		        UserInterface.RETURN_BOOKS_RETURN_INVALID,
                this.logic.getReturnBooksDisplayContent(),
                AppState.RETURN_BOOKS
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.RETURN_BOOK, "asdas");
		res = logic.execute(action);
		assertEquals(expectedRes, res);
		
		action = new Action(ActionType.RETURN_BOOK);
		res = logic.execute(action);
		assertEquals(expectedRes, res);
	}
	
	@Test
	public void testExecuteInvalidReturnBookAction() {
		Action action = new Action(ActionType.INVALID_RETURN_BOOK_MENU_CHOICE);
		Response expectedRes = new Response(
		        UserInterface.RETURN_BOOKS_CHOICE_INVALID,
                this.logic.getReturnBooksDisplayContent(),
                AppState.RETURN_BOOKS
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}



	// Tests for checkout movie action

	@Test
	public void testExecuteCheckoutMovieAction() {
		store.seedMoviesData(this.movieSeed);
		Integer movieId = this.movieSeed.get(0).getIndex();
		Action action = new Action(ActionType.CHECKOUT_MOVIE, movieId);

		Response res = logic.execute(action);
		Response expectedRes = new Response(
		        UserInterface.MOVIE_LIST_CHECKOUT_SUCCESS,
				this.logic.getListMoviesDisplayContent(),
				AppState.LIST_MOVIES
        );

		assertEquals(expectedRes, res);
		assertEquals(1, store.getReturnableMovies().size());
	}

	@Test
	public void testExecuteIllegalCheckoutMovieAction() {
		store.seedMoviesData(this.movieSeed);

		Action action = new Action(ActionType.CHECKOUT_MOVIE, 1233);
		Response expectedRes = new Response(
		        UserInterface.MOVIE_LIST_CHECKOUT_INVALID,
				this.logic.getListMoviesDisplayContent(),
				AppState.LIST_MOVIES
        );

		Response res = logic.execute(action);
		assertEquals(expectedRes, res);

		action = new Action(ActionType.CHECKOUT_MOVIE, "asdas");
		res = logic.execute(action);
		assertEquals(expectedRes, res);

		action = new Action(ActionType.CHECKOUT_MOVIE);
		res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

	@Test
	public void testExecuteInvalidCheckoutMovieAction() {
		Action action = new Action(ActionType.INVALID_LIST_MOVIE_MENU_CHOICE);
		Response expectedRes = new Response(
		        UserInterface.MOVIE_LIST_CHOICE_INVALID,
                this.logic.getListMoviesDisplayContent(),
                AppState.LIST_MOVIES
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

	@Test
	public void testCheckoutMovieWhenNotLoggedIn() {
		this.currentUser = null;
		Action action = new Action(ActionType.CHECKOUT_MOVIE);
		Response expectedRes = new Response(
		        UserInterface.LOGIN_REQUIRED,
                this.logic.getListMoviesDisplayContent(),
                AppState.LIST_MOVIES
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

	//  Test for Auth related actions

	@Test
	public void testExecuteLoginActionWithValidCredentials() {
		this.currentUser = null;
		Action action = new Action(ActionType.LOGIN, USER_NUM, USER_PW);

		Response expectedRes = new Response(
		        UserInterface.LOGIN_SUCCESS_MESSAGE,
                logic.getMainMenuDisplayContent(true),
                AppState.MAIN_MENU
        );
		Response res = logic.execute(action);
		assertEquals(expectedRes, res);
	}

    @Test
    public void testExecuteLoginActionWithInvalidCredentials() {
        this.currentUser = null;
        Action action = new Action(ActionType.LOGIN, "aaa", USER_PW);

        Response expectedRes = new Response(
                UserInterface.LOGIN_FAIL_MESSAGE,
                logic.getLoginDisplayContent(),
                AppState.LOGIN
        );
        Response res = logic.execute(action);
        assertEquals(expectedRes, res);
    }

    @Test
    public void testExecuteInvalidLoginInput() {
        Action action = new Action(ActionType.INVALID_LOGIN_INPUT);

        Response expectedRes = new Response(
                UserInterface.UNRECOGNISED_ACTION_MESSAGE,
                logic.getLoginDisplayContent(),
                AppState.LOGIN
        );
        Response res = logic.execute(action);
        assertEquals(expectedRes, res);
    }

    @Test
    public void testExecuteLogoutAction() {
        Action action = new Action(ActionType.LOGOUT);

        Response expectedRes = new Response(
                UserInterface.LOGOUT_SUCCESS_MESSAGE,
                logic.getMainMenuDisplayContent(false),
                AppState.MAIN_MENU
        );
        Response res = logic.execute(action);
        assertEquals(expectedRes, res);
    }

    @Test
    public void testExecuteInvalidLogoutInput() {
        Action action = new Action(ActionType.INVALID_LOGOUT_INPUT);

        Response expectedRes = new Response(
                UserInterface.UNRECOGNISED_ACTION_MESSAGE,
                logic.getLogoutDisplayContent(),
                AppState.LOGOUT
        );
        Response res = logic.execute(action);
        assertEquals(expectedRes, res);
    }


	// Test for unrecognised action

    @Test
    public void testExecuteUnrecognisedAction() {
        Action action = new Action(ActionType.UNRECOGNISED_ACTION);
        Response expectedRes = new Response(
                UserInterface.UNRECOGNISED_ACTION_MESSAGE,
                this.logic.getMainMenuDisplayContent(userDelegate.isLoggedIn()),
                AppState.MAIN_MENU
        );
        Response res = logic.execute(action);
        assertEquals(expectedRes, res);
    }
}

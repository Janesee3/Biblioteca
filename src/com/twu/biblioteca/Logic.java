package com.twu.biblioteca;

import java.util.ArrayList;

import com.twu.biblioteca.EnumTypes.ActionType;
import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;
import com.twu.biblioteca.Models.Response;
import com.twu.biblioteca.Models.User;

public class Logic {
	
	private Store store;
	private UserDelegate userDelegate;
	
	public Logic() {
		this.store = new Store();
	}
	
	public Logic(Store store) {
		this.store = store;
	}
	
	public void setUserDelegate(UserDelegate delegate) {
		this.userDelegate = delegate;
	}
	
	public Response execute(Action action) {
		switch (action.type) {

			// Navigation
		case GOTO_AUTH:
			return handleGoToAuth();
		case GOTO_LIST_BOOKS:
			return new Response("", getListBooksDisplayContent(), AppState.LIST_BOOKS);
		case GOTO_RETURN_BOOKS:
			return handleGoToReturnBooksAction();
		case GOTO_LIST_MOVIES:
			return new Response("", getListMoviesDisplayContent(), AppState.LIST_MOVIES);
        case GOTO_RETURN_MOVIES:
            return handleGoToReturnMoviesAction();
		case BACK_TO_MAIN_MENU:
			return new Response("", getMainMenuDisplayContent(userDelegate.isLoggedIn()), AppState.MAIN_MENU);
		case QUIT:
			return new Response("", getQuitDisplayContent(), AppState.QUIT);

			// User Actions
		case LOGIN:
			return handleLogin(action.args);
        case LOGOUT:
            return handleLogout();
		case CHECKOUT_BOOK:
			return handleCheckoutBookAction(action.args);
		case RETURN_BOOK:
			return handleReturnBookAction(action.args);
		case CHECKOUT_MOVIE:
			return handleCheckoutMovieAction(action.args);
        case RETURN_MOVIE:
            return handleReturnMovieAction(action.args);
        case SHOW_USER_INFORMATION:
            return handleShowUserInfoAction();

			// Invalid
		case INVALID_LOGIN_INPUT:
			return new Response(UserInterface.UNRECOGNISED_ACTION_MESSAGE, getLoginDisplayContent(), AppState.LOGIN);
        case INVALID_LOGOUT_INPUT:
            return new Response(UserInterface.UNRECOGNISED_ACTION_MESSAGE, getLogoutDisplayContent(), AppState.LOGOUT);
		case INVALID_MENU_CHOICE:
			return new Response(UserInterface.INVALID_MENU_CHOICE, getMainMenuDisplayContent(userDelegate.isLoggedIn()), AppState.MAIN_MENU);
		case INVALID_LIST_BOOK_MENU_CHOICE:
			return new Response(UserInterface.BOOK_LIST_CHOICE_INVALID, getListBooksDisplayContent(), AppState.LIST_BOOKS);
		case INVALID_RETURN_BOOK_MENU_CHOICE:
			return new Response(UserInterface.RETURN_BOOKS_CHOICE_INVALID, getReturnBooksDisplayContent(), AppState.RETURN_BOOKS);
		case INVALID_LIST_MOVIE_MENU_CHOICE:
			return new Response(UserInterface.MOVIE_LIST_CHOICE_INVALID, getListMoviesDisplayContent(), AppState.LIST_MOVIES);
        case INVALID_RETURN_MOVIE_MENU_CHOICE:
            return new Response(UserInterface.RETURN_MOVIES_CHOICE_INVALID, getReturnMoviesDisplayContent(), AppState.RETURN_MOVIES);
        default:
            return new Response(UserInterface.UNRECOGNISED_ACTION_MESSAGE, getMainMenuDisplayContent(userDelegate.isLoggedIn()), AppState.MAIN_MENU);
		}
	}

    private Response handleGoToAuth() {
        if (!userDelegate.isLoggedIn()) {
            return new Response("", getLoginDisplayContent(), AppState.LOGIN);
        } else {
            return new Response("", getLogoutDisplayContent(), AppState.LOGOUT);
        }
    }

	private Response handleGoToReturnBooksAction() {
		if (!userDelegate.isLoggedIn()) {
			return getLoginRequiredResponse(AppState.MAIN_MENU);
		}
		return new Response("", getReturnBooksDisplayContent(), AppState.RETURN_BOOKS);
	}

	private Response handleGoToReturnMoviesAction() {
	    if (!userDelegate.isLoggedIn()) {
	        return getLoginRequiredResponse(AppState.MAIN_MENU);
        }
        return new Response("", getReturnMoviesDisplayContent(), AppState.RETURN_MOVIES);
    }

	private Response handleLogin(ArrayList<Object> args) {
		String libNum = (String) args.get(0);
		String password = (String) args.get(1);
		User user = store.findUserByCredentials(libNum, password);

		if (user == null) {
			return new Response(UserInterface.LOGIN_FAIL_MESSAGE, getLoginDisplayContent(), AppState.LOGIN);
		}

		userDelegate.logUserIn(user);
		return new Response(
		        UserInterface.LOGIN_SUCCESS_MESSAGE,
                getMainMenuDisplayContent(userDelegate.isLoggedIn()),
                AppState.MAIN_MENU
        );
	}

	private Response handleLogout() {
	    userDelegate.logUserOut();
	    return new Response(
	            UserInterface.LOGOUT_SUCCESS_MESSAGE,
                getMainMenuDisplayContent(userDelegate.isLoggedIn()),
                AppState.MAIN_MENU
        );
    }

	private Response handleCheckoutBookAction(ArrayList<Object> args) {
		if (!userDelegate.isLoggedIn()) {
			return getLoginRequiredResponse(AppState.LIST_BOOKS);
		}

		try {
			Integer bookId = (Integer) args.get(0);
			this.store.checkoutBook(bookId, userDelegate.getCurrentUser().getLibraryNumber());
			return getSuccessActionResponse(ActionType.CHECKOUT_BOOK, AppState.LIST_BOOKS);
		} catch (Exception e) {
			return getInvalidActionResponse(ActionType.CHECKOUT_BOOK, AppState.LIST_BOOKS);
		}
	}

	private Response handleCheckoutMovieAction(ArrayList<Object> args) {
		if (!userDelegate.isLoggedIn()) {
			return getLoginRequiredResponse(AppState.LIST_MOVIES);
		}

		try {
			Integer movieId = (Integer) args.get(0);
			this.store.checkoutMovie(movieId, userDelegate.getCurrentUser().getLibraryNumber());
			return getSuccessActionResponse(ActionType.CHECKOUT_MOVIE, AppState.LIST_MOVIES);
		} catch (Exception e) {
			return getInvalidActionResponse(ActionType.CHECKOUT_MOVIE, AppState.LIST_MOVIES);
		}
	}


	private Response handleReturnBookAction(ArrayList<Object> args) {
		try {
			Integer bookId = (Integer) args.get(0);
			this.store.returnBook(bookId, userDelegate.getCurrentUser().getLibraryNumber());
			return getSuccessActionResponse(ActionType.RETURN_BOOK, AppState.RETURN_BOOKS);
		} catch (Exception e) {
			return getInvalidActionResponse(ActionType.RETURN_BOOK, AppState.RETURN_BOOKS);
		}
	}

	private Response handleReturnMovieAction(ArrayList<Object> args) {
        try {
            Integer movieId = (Integer) args.get(0);
            this.store.returnMovie(movieId, userDelegate.getCurrentUser().getLibraryNumber());
            return getSuccessActionResponse(ActionType.RETURN_MOVIE, AppState.RETURN_MOVIES);
        } catch (Exception e) {
            return getInvalidActionResponse(ActionType.RETURN_MOVIE, AppState.RETURN_MOVIES);
        }
    }

    private Response handleShowUserInfoAction() {
	    String feedbackToShow = userDelegate.isLoggedIn() ? getUserInformation() : UserInterface.INVALID_MENU_CHOICE;
        return new Response(feedbackToShow, getMainMenuDisplayContent(userDelegate.isLoggedIn()), AppState.MAIN_MENU);
    }


	// Methods to package response according to state or action
	
	private Response getLoginRequiredResponse(AppState stateToReturn) {
		return new Response(UserInterface.LOGIN_REQUIRED, getDisplayContentForState(stateToReturn), stateToReturn);
	}
	
	private Response getInvalidActionResponse(ActionType action, AppState stateToReturn) {
		String displayContent = getDisplayContentForState(stateToReturn);
		String feedbackContent = "";
		
		switch (action) {
			case CHECKOUT_BOOK:
				feedbackContent = UserInterface.BOOK_LIST_CHECKOUT_INVALID;
				break;
			case CHECKOUT_MOVIE:
				feedbackContent = UserInterface.MOVIE_LIST_CHECKOUT_INVALID;
				break;
			case RETURN_BOOK:
				feedbackContent = UserInterface.RETURN_BOOKS_RETURN_INVALID;
				break;
            case RETURN_MOVIE:
                feedbackContent = UserInterface.RETURN_MOVIES_RETURN_INVALID;
				break;
		}
		
		return new Response(feedbackContent, displayContent, stateToReturn);
	}
	
	private Response getSuccessActionResponse(ActionType action, AppState stateToReturn) {
		String displayContent = getDisplayContentForState(stateToReturn);
		String feedbackContent = "";
		
		switch (action) {
			case CHECKOUT_BOOK:
				feedbackContent = UserInterface.BOOK_LIST_CHECKOUT_SUCCESS;
				break;
			case CHECKOUT_MOVIE:
				feedbackContent = UserInterface.MOVIE_LIST_CHECKOUT_SUCCESS;
				break;
			case RETURN_BOOK:
				feedbackContent = UserInterface.RETURN_BOOKS_RETURN_SUCCESS;
                break;
            case RETURN_MOVIE:
                feedbackContent = UserInterface.RETURN_MOVIES_RETURN_SUCCESS;
				break;
		}
		
		return new Response(feedbackContent, displayContent, stateToReturn);
	}
	
	// Methods to retrieve Display Content from UserInterface 
	
	String getDisplayContentForState(AppState stateToReturn) {
		switch (stateToReturn) {
		case LIST_BOOKS:
			return getListBooksDisplayContent();
		case RETURN_BOOKS:
			return getReturnBooksDisplayContent();
		case MAIN_MENU:
			return getMainMenuDisplayContent(userDelegate.isLoggedIn());
		case LIST_MOVIES:
			return getListMoviesDisplayContent();
        case RETURN_MOVIES:
            return getReturnMoviesDisplayContent();
		case QUIT:
			return getQuitDisplayContent();
		default:
			return "";
		}
	}

	String getListBooksDisplayContent() {
		return UserInterface.getBooksListDisplayString(
				UserInterface.BOOK_LIST_TITLE,
				this.store.getAvailableBooks(),
				UserInterface.BOOK_LIST_MENU
		);
	}

	String getReturnBooksDisplayContent() {
		return UserInterface.getBooksListDisplayString(
				UserInterface.RETURN_BOOKS_TITLE,
				this.store.getReturnableBooks(userDelegate.getCurrentUser().getLibraryNumber()),
				UserInterface.RETURN_BOOKS_MENU
		);
	}

	String getListMoviesDisplayContent() {
		return UserInterface.getMoviesListDisplayString(
				UserInterface.MOVIE_LIST_TITLE,
				this.store.getAvailableMovies(),
				UserInterface.MOVIE_LIST_MENU
		);
	}

	String getReturnMoviesDisplayContent() {
        return UserInterface.getMoviesListDisplayString(
                UserInterface.RETURN_MOVIES_TITLE,
                this.store.getReturnableMovies(userDelegate.getCurrentUser().getLibraryNumber()),
                UserInterface.RETURN_MOVIES_MENU
        );
    }

	String getQuitDisplayContent() {
		return UserInterface.QUIT_MESSAGE;
	}

	String getMainMenuDisplayContent(boolean isUserLoggedIn) {
		return UserInterface.getMenuDisplayString(isUserLoggedIn);
	}

	String getLoginDisplayContent() {
		return UserInterface.LOGIN_PROMPT;
	}

    String getLogoutDisplayContent() {
	    return UserInterface.LOGOUT_PROMPT;
    }

    String getUserInformation() {
	    User currUser = userDelegate.getCurrentUser();
	    return String.format(UserInterface.USER_INFO, currUser.getName(), currUser.getEmail(), currUser.getPhoneNum());
    }
	
	

}

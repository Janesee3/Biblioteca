package com.twu.biblioteca;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private BibliotecaApp app;

    @Before
    public void init() {
        app = new BibliotecaApp();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void injectInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    private String getOutputFromStream() {
        return outContent.toString();
    }
    
    /*** Test for Startup **/
	
    @Test
    public void shouldBeInStartupStateWhenInitialised() {
        assertEquals(AppState.STARTUP, app.getState());
    }

    @Test
    public void startupSequenceShouldPrintMessageAndChangeState() {
        app.runStartupSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
        assertTrue(getOutputFromStream().contains(UserInterface.WELCOME_MESSAGE));
    }

    /*** Test for Menu Sequence ***/
    
    @Test
    public void menuSequenceShouldPrintMenu() {
        String dummyInput = "meow";
        injectInput(dummyInput);
        app.runMenuSequence();
        assertTrue(getOutputFromStream().contains(UserInterface.MENU));
    }

    @Test
    public void menuChoiceListBookShouldChangeStateCorrectly() {
        injectInput(UserInterface.MENU_CHOICE_LIST_BOOKS);
        app.runMenuSequence();
        assertEquals(AppState.LIST_BOOKS, app.getState());
    }

    @Test
    public void menuChoiceQuitShouldChangeStateCorrectly() {
        injectInput(UserInterface.MENU_CHOICE_QUIT);
        app.runMenuSequence();
        assertTrue(app.hasQuit);
    }
    
    @Test
    public void menuChoiceReturnBooksShouldChangeStateCorrectly() {
	    	injectInput(UserInterface.MENU_CHOICE_RETURN_BOOKS);
	    	app.runMenuSequence();
	    	assertEquals(AppState.RETURN_BOOKS, app.getState());
    }
    
    @Test
    public void menuChoiceInvalidShouldNotChangeState() {
	    	app.setState(AppState.MAIN_MENU);    	
	    	injectInput("asdadas");
	    	
	    	app.runMenuSequence();
	    	
	    	assertEquals(AppState.MAIN_MENU, app.getState());
	    	assertTrue(getOutputFromStream().contains(UserInterface.INVALID_MENU_CHOICE));
    }
    
    /*** Test for List Books Sequence ***/

    @Test
    public void bookListMenuBackShouldChangeStateCorrectly() {
        injectInput(UserInterface.BOOK_LIST_CHOICE_BACK);
        app.runListBooksSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
    }
    
    @Test
    public void bookListSequenceShouldShowCorrectMenu() {
    		String dummyInput = "asd";
    		injectInput(dummyInput);
    		app.runListBooksSequence();
    		assertTrue(getOutputFromStream().contains(UserInterface.BOOK_LIST_MENU));
    }
    
    @Test
    public void shouldPrintInvalidMsgWhenCheckoutInvalidBook() {
    		String input = UserInterface.BOOK_LIST_CHOICE_CHECKOUT + " " + "dasdad";
        injectInput(input);
        app.runListBooksSequence();
        assertTrue(getOutputFromStream().contains(UserInterface.BOOK_LIST_CHECKOUT_INVALID));
    }
    
    @Test
    public void shouldPrintSuccessMsgAndCheckoutBook() {
    		String input = "checkout 0";
        injectInput(input);
        app.runListBooksSequence();
        assertTrue(getOutputFromStream().contains(UserInterface.BOOK_LIST_CHECKOUT_SUCCESS));
    }
    
    @Test
    public void invalidBookListChoiceShouldPrintInvalidMsg() {
    		String input = "dasdasd";
        injectInput(input);
        
        app.runListBooksSequence();
        
        assertTrue(getOutputFromStream().contains(UserInterface.BOOK_LIST_CHOICE_INVALID));
    }
    
    /*** Test for Return Books Sequence ***/
    
    @Test
    public void returnBooksSequenceShouldShowCorrectMenu() {
    		String dummyInput = "asd";
    		injectInput(dummyInput);
    		app.runReturnBooksSequence();
    		assertTrue(getOutputFromStream().contains(UserInterface.RETURN_BOOKS_MENU));
    }
    
    @Test
    public void returnBooksMenuBackShouldChangeStateCorrectly() {
        injectInput(UserInterface.RETURN_BOOKS_CHOICE_BACK);
        app.runReturnBooksSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
    }
    

    @Test
    public void shouldPrintInvalidMsgWhenReturnInvalidBook() {
    		String input = UserInterface.RETURN_BOOKS_CHOICE_RETURN + " " + "dasdad";
        injectInput(input);
        app.runReturnBooksSequence();
        assertTrue(getOutputFromStream().contains(UserInterface.RETURN_BOOKS_RETURN_INVALID));
    }
    
    @Test
    public void shouldPrintSuccessMsgWhenReturnBook() {
    		// TODO: Allow injection of store data when initialising app
    }
    
    
    @Test
    public void invalidReturnBooksChoiceShouldPrintInvalidMsg() {
    		String input = "dasdasd";
        injectInput(input);
        
        app.runReturnBooksSequence();
        
        assertTrue(getOutputFromStream().contains(UserInterface.RETURN_BOOKS_CHOICE_INVALID));
    }
    

    
    

   
}

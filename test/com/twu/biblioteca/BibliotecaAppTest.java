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
    
    /*** Test for List Books Sequence ***/

    @Test
    public void bookListMenuBackShouldChangeStateCorrectly() {
        injectInput(UserInterface.BOOK_LIST_CHOICE_BACK);
        app.runListBooksSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
    }
    
    @Test
    public void shouldPrintInvalidMessageWhenCheckoutInvalidBook() {
    		String input = "checkout " + "dasdad";
        injectInput(input);
        app.runListBooksSequence();
        String output = getOutputFromStream();
        assertTrue(output.contains(UserInterface.BOOK_LIST_CHECKOUT_INVALID));
    }
    
    /*** Test for Return Books Sequence ***/
    
    
    
    /*** Test for Parsing Checkout Statement ***/

    @Test
    public void shouldParseIdFromCheckoutStatement() {
        int id = 10;
        String input = "checkout " + id;
        assertEquals(id, app.getIdFromCheckoutStatement(input));
    }

    @Test
    public void shouldReturnNegativeIfInvalidCheckoutStatement() {
        int id = -1;
        String input = "checkout " + "dasdad";
        assertEquals(id, app.getIdFromCheckoutStatement(input));

        input = "checkout";
        assertEquals(id, app.getIdFromCheckoutStatement(input));
    }

   
}

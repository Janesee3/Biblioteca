package com.twu.biblioteca;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        return outContent.toString().trim();
    }
	
    @Test
    public void shouldBeInStartupStateWhenInitialised() {
        assertEquals(AppState.STARTUP, app.getState());
    }

    @Test
    public void startupSequenceShouldPrintMessageAndChangeState() {
        app.runStartupSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
        assertEquals(UserInterface.WELCOME_MESSAGE, getOutputFromStream());
    }

    @Test
    public void menuSequenceShouldPrintMenu() {
        String dummyInput = "meow";
        injectInput(dummyInput);

        app.runMenuSequence();

        assertEquals(UserInterface.MENU + System.lineSeparator() + UserInterface.INVALID_MENU_CHOICE, getOutputFromStream());
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
    public void bookListMenuBackShouldChangeStateCorrectly() {
        injectInput(UserInterface.BOOK_LIST_CHOICE_BACK);
        app.runListBooksSequence();
        assertEquals(AppState.MAIN_MENU, app.getState());
    }

}

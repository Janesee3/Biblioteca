package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInterfaceTest {
	
	private UserInterface ui;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;
	
	
	@Before
	public void init() {
		ui = new UserInterface();
		setupStreams();
	}
	
	@After
	public void tearDown() {
		ui = null;
		restoreStreams();
	}
	
	private void setupStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	private void restoreStreams() {
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
    public void showShouldPrintInputString() {
		String expected = "test string";
		ui.show(expected);
		assertEquals(expected, getOutputFromStream());
    }
	
	@Test
    public void readUserInputShouldCorrectlyRecordInput() {
		String expected = "test input";
		injectInput(expected);
		
		String result = ui.readUserInput();
		
		assertEquals(expected, result);
    }
	
	@Test
    public void showStartupSequenceShouldPrintWelcomeMessageAndMenu() {
		ui.showStartupSequence();
		assertTrue(getOutputFromStream().contains(UserInterface.WELCOME_MESSAGE));
		assertTrue(getOutputFromStream().contains(UserInterface.getMenuDisplayString(false)));
    }

    private String MENU_LOGIN = String.format(UserInterface.MENU_LOGIN, UserInterface.MENU_CHOICE_AUTH);
	private String MENU_LOGOUT = String.format(UserInterface.MENU_LOGOUT, UserInterface.MENU_CHOICE_AUTH);

    @Test
	public void testGetMenuDisplayStringWhenLoggedIn() {
		String menu = UserInterface.getMenuDisplayString(true);
		assertTrue(menu.contains(MENU_LOGOUT));
		assertFalse(menu.contains(MENU_LOGIN));
	}

	@Test
	public void testGetMenuDisplayStringWhenLoggedOut() {
		String menu = UserInterface.getMenuDisplayString(false);
		assertTrue(menu.contains(MENU_LOGIN));
		assertFalse(menu.contains(MENU_LOGOUT));
	}



	
	
}

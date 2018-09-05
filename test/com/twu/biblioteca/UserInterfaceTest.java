package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

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
	public void teardown() {
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
		
		ui.readUserInput();
		
		assertEquals(expected, ui.getUserInput());
    }
	
	@Test
    public void showWelcomeSequenceShouldPrintWelcomeMessage() {
		ui.showWelcomeSequence(); 
		assertEquals(UserInterface.WELCOME_MESSAGE, getOutputFromStream());
    }
	
	
	
	
	
	
	
	
	
}

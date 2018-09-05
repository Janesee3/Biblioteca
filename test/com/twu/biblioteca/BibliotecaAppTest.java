package com.twu.biblioteca;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class BibliotecaAppTest {
	
	private BibliotecaApp app;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void init() {
		app = new BibliotecaApp();
		setupStreams();
	}
	
	@After
	public void teardown() {
		app = null;
		restoreStreams();
	}
	
	private void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	
	private void setupStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
    public void test() {

    }

}

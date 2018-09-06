package com.twu.biblioteca;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void init() {
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
        BibliotecaApp app = new BibliotecaApp();
        assertEquals(AppState.STARTUP, app.getState());
    }

}

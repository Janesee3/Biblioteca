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

//    TODO: could be improved - there are no tests in BibliotecaAppTest? is this intended to be a smoke test?

//    TODO: could be improved - unused methods should be removed
    private void injectInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    private String getOutputFromStream() {
        return outContent.toString();
    }
    

    
    

    
    

   
}

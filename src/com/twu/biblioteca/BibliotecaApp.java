package com.twu.biblioteca;

public class BibliotecaApp {

    private UserInterface ui;
    private Logic logic;

    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.logic = new Logic();
    }

    public void run() {
        this.ui.showWelcomeSequence();

    }

}

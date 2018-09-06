package com.twu.biblioteca;

public class BibliotecaApp {

    private UserInterface ui;
    private Controller controller;

    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.controller = new Controller();
    }

    public void run() {
        this.ui.showWelcomeSequence();
    }

}

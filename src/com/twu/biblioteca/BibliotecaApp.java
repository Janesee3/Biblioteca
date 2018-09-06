package com.twu.biblioteca;

public class BibliotecaApp {

    private UserInterface ui;
    private AppState state;
    private boolean hasQuit;

    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.state = AppState.STARTUP;
        this.hasQuit = false;
    }

    public void run() {
        while (!hasQuit) {
            this.processState();
        }
    }

    private void processState() {
        switch (this.state) {
            case STARTUP:
                this.runStartupSequence();
            case MAIN_MENU:
                this.runMenuSequence();
        }
    }

    private void runStartupSequence() {
        this.ui.showWelcomeSequence();
        this.state = AppState.MAIN_MENU;
    }

    private void runMenuSequence() {
        this.ui.showMenu();
        String menuChoice = this.ui.readUserInput();
        // DO something
    }


}

package com.twu.biblioteca;

public class BibliotecaApp {

    private UserInterface ui;
    private Controller controller;

    public BibliotecaApp() {
        ui = new UserInterface();
        controller = new Controller();
    }

    public void run() {
       ui.show("Welcome!");

       while (true) {
           ui.readUserInput();
           String result = this.controller.processInput(ui.getUserInput());
           ui.show(result);
       }
    }

}

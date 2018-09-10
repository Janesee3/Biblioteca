package com.twu.biblioteca;

import java.util.ArrayList;

import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;
import com.twu.biblioteca.Models.Response;

public class BibliotecaApp {

    private UserInterface ui;
    private AppState state;
    private Logic logic;
    
    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.state = AppState.MAIN_MENU;
        this.logic = new Logic(new Store());
    }
    
    public BibliotecaApp(Store store) {
        this.ui = new UserInterface();
        this.state = AppState.MAIN_MENU;
        this.logic = new Logic(store);
    }

	
    public void run() {
    		
    		this.ui.showWelcomeSequence();
    		this.ui.showMenu();
    	
        while (this.getState() != AppState.QUIT) {
        		String input = this.ui.readUserInput();
        		Action action = Parser.parse(this.getState(), input);
        		Response res = logic.execute(action);
        		this.setState(res.getNewState());
        		this.ui.displayResponse(res);
        }
    }

    public AppState getState() {
        return this.state;
    }

    public void setState(AppState state) {
        this.state = state;
    }

   
}

package com.twu.biblioteca;

import java.util.ArrayList;

import com.twu.biblioteca.EnumTypes.AppState;
import com.twu.biblioteca.Models.Action;
import com.twu.biblioteca.Models.Response;
import com.twu.biblioteca.Models.User;

public class BibliotecaApp implements UserDelegate {

    private UserInterface ui;
    private AppState state;
    private Logic logic;
    
    public BibliotecaApp() {
        this.ui = new UserInterface();
        this.state = AppState.MAIN_MENU;
        this.logic = new Logic(new Store());
        this.logic.setUserDelegate(this);
    }
    
    public BibliotecaApp(Store store) {
        this.ui = new UserInterface();
        this.state = AppState.MAIN_MENU;
        this.logic = new Logic(store);
    }

	
    public void run() {
    		
        this.ui.showStartupSequence();

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
    
    /** UserDelegate **/
    
    private User currentUser = null; 
    
    public boolean isLoggedIn() {
    		return this.currentUser != null;
    }
    
    public User getCurrentUser() {
    	return this.currentUser;
    }

    public void logUserIn(User user) {
        this.currentUser = user;
    }

    public void logUserOut() {
        this.currentUser = null;
    }

   
}

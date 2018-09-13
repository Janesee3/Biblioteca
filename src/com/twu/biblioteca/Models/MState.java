package com.twu.biblioteca.Models;

import com.twu.biblioteca.EnumTypes.AppState;

public class MState {
	
	private AppState appState;
	private User currentUser;
	
	public MState(AppState state) {
		this.appState = state;
		this.currentUser = null;
	}
	
	public AppState getAppState() {
		return this.appState;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public void removeCurrentUser() {
		this.currentUser = null;
	}
	
	public void setState(AppState state) {
		this.appState = state;
	}
	
	public boolean isUserLoggedIn() {
		return this.currentUser != null;
	}
	
	
}

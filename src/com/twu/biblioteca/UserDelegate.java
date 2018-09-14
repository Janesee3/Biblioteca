package com.twu.biblioteca;

import com.twu.biblioteca.Models.User;

public interface UserDelegate {
	
	public boolean isLoggedIn();
	public User getCurrentUser();
	public void logUserIn(User user);
	public void logUserOut();
}

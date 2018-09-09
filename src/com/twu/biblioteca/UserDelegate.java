package com.twu.biblioteca;

public interface UserDelegate {
	
	public boolean isLoggedIn();
	public String getCurrentUser();
	public void login(String username);
	public void logout();
}

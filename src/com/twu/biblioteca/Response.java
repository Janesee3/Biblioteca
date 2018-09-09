package com.twu.biblioteca;

public class Response {
	private String displayContent;
	private AppState newState;
	
	public Response(String displayContent, AppState state) {
		this.displayContent = displayContent;
		this.newState = state;
	}
	
	public String getDisplayContent() {
		return this.displayContent;
	}
	
	public AppState getNewState() {
		return this.newState;
	}
	
}

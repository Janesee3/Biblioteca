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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Response)) { 
            return false; 
        } 
		Response castedObject = (Response) o;
        if (!this.displayContent.equals(castedObject.displayContent)) {
        		return false;
        }
        if (this.newState != castedObject.newState) {
        		return false;
        }
        return true;
	}
	
	@Override
	public String toString() {
		return String.format("[%s\n%s]", this.displayContent, this.newState.name());
	}
}

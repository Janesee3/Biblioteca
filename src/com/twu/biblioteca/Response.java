package com.twu.biblioteca;

public class Response {
	private String feedbackString;
	private AppState newState;
	private String displayContent;
	
	public Response(String feedbackString, String displayContent, AppState state) {
		this.feedbackString = feedbackString;
		this.displayContent = displayContent;
		this.newState = state;
	}
	
	public String getFeedbackString() {
		return this.feedbackString;
	}
	
	public AppState getNewState() {
		return this.newState;
	}
	
	public String getDisplayContent() {
		return this.displayContent;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Response)) { 
            return false; 
        } 
		Response castedObject = (Response) o;
        if (!this.feedbackString.equals(castedObject.feedbackString)) {
        		return false;
        }
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
		return String.format("[%s\n%s]", this.feedbackString, this.displayContent, this.newState.name());
	}
}

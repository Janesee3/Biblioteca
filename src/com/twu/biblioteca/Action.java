package com.twu.biblioteca;

import java.util.ArrayList;

public class Action {
	
	public ActionType type;
	public ArrayList<Object> args;
	
	public Action(ActionType type, ArrayList<Object> args) {
		this.type = type;
		this.args = args;
	}
	
	public Action(ActionType type) {
		this.type = type;
		this.args = new ArrayList<Object>();
	}
	
	public void addArg(Object arg) {
		this.args.add(arg);
	}
	
}

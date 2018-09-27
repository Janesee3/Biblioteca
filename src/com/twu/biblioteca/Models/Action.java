package com.twu.biblioteca.Models;

import java.util.ArrayList;

import com.twu.biblioteca.EnumTypes.ActionType;

public class Action {
	
	public ActionType type;
	public ArrayList<Object> args;
	
	public Action(ActionType type) {
		this.type = type;
		this.args = new ArrayList<Object>();
	}
	
	public Action(ActionType type, Object... args) {
		this.type = type;
		this.args = new ArrayList<Object>();
		for (Object o: args) {
			this.args.add(o);
		}
	}
	
	public void addArg(Object arg) {
		this.args.add(arg);
	}
	
	@Override
	public boolean equals(Object o) {   
//		TODO: good - use of guard clauses
		if (!(o instanceof Action)) { 
            return false; 
        } 
		
		Action castedObject = (Action) o;
		
        if (this.type != castedObject.type) {
        		return false;
        }
        
        if (this.args.size() != castedObject.args.size()) {
        		return false;
        }
        
        for (int i = 0; i < this.args.size(); i++) {
        		Object thisArg = this.args.get(i);
        		Object thatArg = castedObject.args.get(i);
        		if (!thisArg.equals(thatArg)) {
        			return false;
        		}
        }
        
        return true;
	}
	
}

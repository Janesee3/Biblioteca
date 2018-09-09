package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActionTest {
	
	@Test
	public void constructorShouldInstantiateCorrectly() {
		ActionType type = ActionType.BACK_TO_MAIN_MENU;
		Action action = new Action(type);
		assertEquals(type, action.type);
	}
	
	@Test
	public void shouldAddArgsCorrectly() {
		String arg1 = "hello";
		Integer arg2 = 1;
		ActionType type = ActionType.BACK_TO_MAIN_MENU;
		Action action = new Action(type);		
		
		action.addArg(arg1);
		action.addArg(arg2);
		
		assertTrue(arg1.equals(action.args.get(0)));
		assertTrue(arg2.equals(action.args.get(1)));
	}

}
